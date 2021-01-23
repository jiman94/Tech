Spring WebFlux Session with Redis


```xml 
<!-- Spring -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.session</groupId>
    <artifactId>spring-session-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
</dependency>
<!--Common Pool -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
    <version>2.8.0</version>
</dependency>
```

### application.yaml 
* Redis Connection 정보와 Spring Session 타입을 지정해 줍니다.
```yaml 
spring:
  redis:
    host: localhost
    port: 16379
    password: {REDIS_PASSWORD}
    lettuce:
      pool:
        min-idle: 2
        max-idle: 5
        max-active: 10
  session:
    store-type: redis
    redis:
        flush-mode: immediate
```

* lettuce를 통한 Connection Pool을 구성하고 Session Storage를 Redis로 지정하여 Redis Session을 사용하도록 설정합니다.
* Session 관련 나머지 설정은 기본값으로 두어도 상관없지만 spring.session.redis.flush-mode 값을 immediate 로 설정하면 가능한 빨리 Session을 Redis Storage에 저장하게 됩니다.

### Application 설정
* Annotation 을 통하여 Session과 Redis를 활성화 시키고 Session Object를 저장하기 위해 RedisTemplate을 재정의 합니다.
* @EnableRedisWebSession 을 통해 Redis Session을 설정하고 maxInactiveIntervalInSeconds 에 Session Timeout 값(초)를 지정합니다.
* @EnableRedisRepositories 를 통해 Redis Repository를 활성화 하고 RedisTemplate을 정의합니다.
* REST API 서비스 같은 경우에는 UI가 없기 때문에 Session 정보를 Header에 담아 전달하는 방식을 선호하게 됩니다. 
* WebSessionIdResolver 를 재정의해서 “X-AUTH-HEADER” 해더를 Session ID로 인식하게 합니다.

```java
@SpringBootApplication
@EnableWebFlux
@EnableRedisRepositories
@EnableRedisWebSession(maxInactiveIntervalInSeconds = 60*60*2)
public class SessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionApplication.class, args);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {

        RedisSerializationContext<String, Object> serializationContext =
            RedisSerializationContext.<String, Object>newSerializationContext(new StringRedisSerializer())
                                     .hashKey(new StringRedisSerializer())
                                     .hashValue(new GenericJackson2JsonRedisSerializer())
                                     .build();

        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

    @Bean
    public WebSessionIdResolver webSessionIdResolver() {
        HeaderWebSessionIdResolver sessionIdResolver = new HeaderWebSessionIdResolver();
        sessionIdResolver.setHeaderName("X-AUTH-TOKEN");
        return sessionIdResolver;
    }
}
```
### Session Filter 설정
* Session 체크를 위해 Spring WebFlux 에 WebFilter 를 통해 SessionFilter를 정의해 줍니다.

```java
@Slf4j
@Component
public class SessionFilter implements WebFilter {

    private PathPattern basePattern;

    private List<PathPattern> excludePatterns;

    public SessionFilter() {
        basePattern = new PathPatternParser()
                            .parse("/api/**");
        excludePatterns = new ArrayList<>();
        excludePatterns.add(new PathPatternParser().parse("/api/auth/sign*"));
        excludePatterns.add(new PathPatternParser().parse("/api/ping/**"));
    }

    @Override
    public Mono<Void> filter(final ServerWebExchange serverWebExchange, final WebFilterChain webFilterChain) {


        ServerHttpRequest request = serverWebExchange.getRequest();
        log.info("{} : {} {}", request.getHeaders().getFirst("X-Forwarded-For") == null ? request.getRemoteAddress() : request.getHeaders().getFirst("X-Forwarded-For"), request.getMethodValue(), request.getURI().toString());

        if (basePattern.matches(request.getPath().pathWithinApplication())
            && !excludePatterns.stream()
                               .anyMatch(pathPattern -> pathPattern.matches(request.getPath().pathWithinApplication()))
           ) {
            return serverWebExchange.getSession()
                                    .doOnNext(session -> Optional.ofNullable(session.getAttribute("user"))
                                                                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not found session, Please Login again."))
                                    )
                                    .then(webFilterChain.filter(serverWebExchange));
        } else {
            return webFilterChain.filter(serverWebExchange);
        }
    }
}
```
### REST 사용법
* SignIn 후 SessionID 전달

* 정상적으로 로그인이 되었을 경우 ResponseEntity 객체를 통해 SessionID를 Header로 전달해 줍니다.

* @RestController 에서 Session 객체 사용

* @RestController 에서는 WebSession 파라미터를 통해 Session 객체에 바로 접근할 수도 있으며
* @SessionAttribute 를 통해 Session에 저장된 객체를 바로 파라미터로 받아 사용할 수 도 있습니다.


```java

@RestController
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping(path = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<UserInfo>> signIn(@RequestBody @Valid UserParam userParam, WebSession session) {

        return
            Mono.defer(() -> userRepository.findByUserId(userParam.getUserId()).map(Mono::just).orElseGet(Mono::empty))
                .subscribeOn(Schedulers.elastic())
                .filter(user -> userParam.getPassword().equals(user.getPassword()))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found user info or invalid password")))
                .map(user -> UserInfo.builder()
                                     .userId(user.getUserId())
                                     .build()
                )
                .doOnNext(userInfo -> session.getAttributes().put("user", userInfo))
                .map(userInfo -> ResponseEntity.ok()
                                               .header("X-AUTH-TOKEN", session.getId())     //Add Header for Session
                                               .contentType(MediaType.APPLICATION_JSON)
                                               .body(userInfo)
                )
            ;
    }

    @PutMapping(path = "/logout")
    public Mono<Void> logout(WebSession session) {

        return
            Mono.just(session)
                .flatMap(WebSession::invalidate)
            ;
    }
}
```

```java
@RestController
@RequestMapping(path = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class TestController {

    @GetMapping(path = "/me")
    public Mono<UserInfo> testWithWebSession(WebSession session) {

        return
            Mono.justOrEmpty((UserInfo)session.getAttribute("user"))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have permission!")))
                .cast(UserInfo.class)
            ;
    }

    @GetMapping(path = "/me2")
    public Mono<UserInfo> testWithAttribute(@SessionAttribute("user") UserInfo user) {

        return
            Mono.justOrEmpty(user)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You don't have permission!")))
                .cast(UserInfo.class)
            ;
    }
}

```


### Redis Session 확인

Redis CLI를 통해 Redis에 접속해 보면 다음과 같이 Redis Session이 저장되어 있는것을 확인 할 수 있습니다.
127.0.0.1:6379> keys *


### CORS

```java
package moon.odyssey.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class CorsFilter implements WebFilter {

    @Override
    public Mono<Void> filter(final ServerWebExchange serverWebExchange, final WebFilterChain webFilterChain) {
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "DNT,X-AUTH-TOKEN,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
        if (serverWebExchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
            serverWebExchange.getResponse().getHeaders().add("Access-Control-Max-Age", "1728000");
            serverWebExchange.getResponse().setStatusCode(HttpStatus.NO_CONTENT);
            return Mono.empty();
        } else {
            serverWebExchange.getResponse().getHeaders().add("Access-Control-Expose-Headers", "DNT,X-AUTH-TOKEN,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
            return webFilterChain.filter(serverWebExchange);
        }
    }
}
```



