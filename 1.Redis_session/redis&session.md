레디스 클러스터링 수행, 스프링부트 (2.2.5 version 기준)

1. 레디스 의존성 추가 (build.gradle)

security 랑 controller 수행할 수 있도록 starter 도 있어야 한다.
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
implementation 'org.springframework.session:spring-session-data-redis'
compile 'org.springframework.boot:spring-boot-starter-security'
 

2. @EnableRedisHttpSession 설정

maxInactiveIntervalInSeconds 는 세션만료시간이고, 초단위이다. 레디스 내 저장될때는 밀리세컨드단위로 들어간다. (millisecond 는 1초의 10^(-3)) 아래의 코드는 60초이기 때문에 60000밀리세컨드이다.
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class RedisHttpSessionConfiguration extends AbstractHttpSessionApplicationInitializer {

    public RedisHttpSessionConfiguration(){
        super(RedisHttpSessionConfiguration.class);
    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Autowired
    private ObjectMapper mapper;

    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        standaloneConfiguration.setPassword(password.isEmpty() ? RedisPassword.none() : RedisPassword.of(password));
        return new LettuceConnectionFactory(standaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(mapper));

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
 

3. 스프링 시큐리티 설정

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("1")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("2")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .anyRequest().authenticated();

        http
                .formLogin()
                .defaultSuccessUrl("/", true)
                    .and()
                .logout()
                .logoutUrl("/logout");
    }
}
 

4. 컨트롤러 설정

@RestController
public class DemoController {

    @GetMapping("/")
    public String root() {
        return "root";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
 

5. yml 등록

spring:
  redis:
    host: localhost
    port: 6379
    password: ''
  session:
    store-type: redis
    redis:
      flush-mode: on_save


# 

build.gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-redis'
	implementation 'org.springframework.session:spring-session-data-redis'
}

application.yml
flush-mode: on-save (default, 호출될 때만 Redis에 기록), immediate (즉시 Redis에 기록)

namespace: 세션을 저장하는 데에 key값이 prefix라고 보시면되요. default는 spring:session입니다.


spring:
  redis:
    host: localhost
    port: 6379
  session:
    timeout: 600
    store-type: redis
    redis:
      flush-mode: on-save
      namespace: spring:session


# application.yml
collection-name: 세션을 저장할 collection을 지정해주세요. default는 sessions입니다.

 

spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: tutorial
  session:
    timeout: 600
    store-type: mongodb
    mongodb:
      collection-name: sessions



# Spring Boot Session MySQL 연동, 저장
 
Spring Boot Session 사용하기 (Bean Scope)



build.gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.session:spring-session-jdbc'
	runtimeOnly 'org.mariadb.jdbc:mariadb-java-client' // MariaDB
}
application.yml
spring:
  datasource:
    url: jdbc:mariadb://localhost:3306/tutorial
    driver-class-name: org.mariadb.jdbc.Driver
    username: root
    password: 1234
  session:
    timeout: 600
    store-type: jdbc
    jdbc:
      initialize-schema: always
      table-name: SPRING_SESSION
  jpa:
    open-in-view: false
    generate-ddl: true


# Spring Boot Session MongoDB 연동, 저장

Spring Boot에서 세션을 사용하는 방법에 대해서 알아볼게요. Bean Scope를 활용하여 session 데이터를 편리하게 관리할 수 있는 방법을 알려드릴거에요. 굉장히! 간단합니다. 컴포넌트 하나만 추가하면 되요. Use..

gofnrk.tistory.com
build.gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
	implementation 'org.springframework.session:spring-session-data-mongodb'
}

application.yml
collection-name: 세션을 저장할 collection을 지정해주세요. default는 sessions입니다.

 

spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: tutorial
  session:
    timeout: 600
    store-type: mongodb
    mongodb:
      collection-name: sessions


# Spring Boot Session Redis 연동, 저장

Spring Boot에서 세션을 사용하는 방법에 대해서 알아볼게요. Bean Scope를 활용하여 session 데이터를 편리하게 관리할 수 있는 방법을 알려드릴거에요. 굉장히! 간단합니다. 컴포넌트 하나만 추가하면 되요. Use..

gofnrk.tistory.com
build.gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-redis'
	implementation 'org.springframework.session:spring-session-data-redis'
}

application.yml
flush-mode: on-save (default, 호출될 때만 Redis에 기록), immediate (즉시 Redis에 기록)

namespace: 세션을 저장하는 데에 key값이 prefix라고 보시면되요. default는 spring:session입니다.

나머지 설정은 굳이 설명안해도 될 것 같아서 넘어갈게요.

 

spring:
  redis:
    host: localhost
    port: 6379
  session:
    timeout: 600
    store-type: redis
    redis:
      flush-mode: on-save
      namespace: spring:session
 

redis-cli에서 아래와 같이 세션 데이터가 저장됨을 확인할 수 있어요.

세션 시간(timeout)이 만료되면 데이터베이스 자체에서 완벽히 삭제 처리합니다.
 Redis는 키-값 구조의 비정형 데이터를 저장하는 메모리 기반 데이터베이스죠.

세션 데이터는 단순히 키-값 구조이기 때문에 많은 저장 메모리 용량을 요구하지 않고, 높은 속도가 중요하므로 Redis는 그야말로 세션을 저장하기에 가장 알맞는 데이터베이스라고 봅니다.
