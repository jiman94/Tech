(Spring Boot / Spring Cloud / MSA) 3. Eureka란? / 적용방법

Netflix Stack?

Zuul - Gateway, 인증, Logging 수집 등
Eureka - Service Registry & Discovery
Ribbon - Load Balancing
Hystrix - Circuit Breaker
Turbine - Hystrix Stream Aggregator

RabbitMQ : AMQP (Advanced Message Queueing Protocol) 로 만들어져 있으며 Message Queue를 제공한다. 
이상적인 마이크로서비스 환경은 마이크로서비스 사이의 통신이 비동기적으로 이루어지는 것인데, RabbitMQ를 사용하면 마이크로 서비스들이 외부의 Queue를 통해 메세지를 주고받도록 함으로써 쉽게 이 부분을 구성할 수 있다. 

Eureka : 마이크로서비스들의 정보를 레지스트리에 등록할 수 있도록 하고 마이크로서비스의 동적인 탐색과 로드밸런싱을 제공한다.
Zuul : 모든 마이크로서비스에 대한 요청을 먼저 받아들이고 라우팅하는 프록시 API Gateway 기능을 수행한다.



Eureka 란?
마이크로서비스들의 정보를 레지스트리에 등록할 수 있도록 하고 마이크로서비스의 동적인 탐색과 로드밸런싱을 제공한다.

먼저 Eureka는 Eureka Server와 Eureka Client로 구성된다. 

Eureka Server는 Eureka Client에 해당하는 마이크로서비스들의 상태 정보가 등록되어있는 레지스트리를 갖는다. 

Eureka Client의 서비스가 시작 될 때 Eureka Server에 자신의 정보를 등록한다. 
등록된 후에는 30초마다 레지스트리에 ping을 전송하여 자신이 가용 상태임을 알리는데 일정 횟수 이상 ping이 확인되지 않으면 Eureka Server에서 해당 서비스를 레지스트리에서 제외시킨다. 

레지스트리의 정보는 모든 Eureka Client에 복제되어 있어 필요할 때마다 가용 상태인 모든 서비스들의 목록을 확인할 수 있고 이 목록은 30초마다 갱신된다. 
가용 상태의 서비스 목록을 확인할 경우에는 서비스의 이름을 기준으로 탐색하며 로드밸런싱을 위해 내부적으로 Ribbon(클라이언트 측의 로드밸런서)을 사용한다.

Spring Cloud의 적용 과정 : Eureka

먼저 현재 운영중인 마이크로서비스들을 Client로 가질 Eureka Server를 구성해야한다.

#### 1. Spring Boot 프로젝트를 하나 생성하고 bulid.gradle 파일에 Dependency를 추가한다.
 
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-server')
}

#### 2. application.yml 파일에 Eureka Server 설정에 필요한 정보를 추가한다.

# -- Server Port

server:
  port: 8787

# -- Eureka
eureka:
  instance:
    hostname: 127.0.0.1
  client:
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
    register-with-eureka: false
    fetch-registry: false

#### 3. Application.java 파일의 Class에 @EnableEurekaServer를 추가한다.


@EnableEurekaServer
@SpringBootApplication

public class EurekaServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaServerApplication.class, args);
  }
}


이렇게 Eureka Server의 생성이 완료되었다. 
방금 생성한 어플리케이션을 동작 시키고 브라우저에서 http://127.0.0.1:8787에 접속하면

Eureka Server가 제공하는 Page를 확인할 수 있다.
 

이 Page를 통해 Registry에 등록된 모든 인스턴스들의 정보와 상태를 확인할 수 있다.
다음으로 Eureka Server에 등록될 마이크로서비스들에 Eureka Client 설정을 해야한다.


#### 1. 해당 프로젝트의 bulid.gradle 파일에 Dependency를 추가한다.

dependencies {
    // Add Eureka Client Dependency
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client')
}
 
#### 2. application.yml 파일에 Eureka Client 설정에 필요한 정보를 추가한다.

# -- Default spring configuration

spring:
  application:
    name: (Your Service Name)

# -- Eureka client
eureka:
  client:
    serviceUrl:
      defaultZone: ${EUREKA_URL:http://127.0.0.1:8787/eureka/}
 

#### 3.    Application.java 파일의 Class에 @EnableDiscoveryClient를 추가한다.

@EnableDiscoveryClient
@SpringBootApplication

public class EurekaClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaClientApplication.class, args);
  }
}
 

이제 Eureka Client 설정도 완료하였다. 
Eureka Server가 작동하고 있는 상태에서 Eureka Client를 시작하면 Eureka Server의 registry에 등록된다.


Eureka의 모든 설정을 완료 하였으니 기능을 활용 해 볼 차례다. 

Eureka는 가용 가능한 서비스를 이름을 기준으로 검색하여 요청을 넘길 수 있고 내부적으로 Ribbon을 사용한다고 하였다. 

나는 이 기능을 활용하기 위해 UI Service에서 User Service로 API 호출이 일어나는 부분을 수정하였다.

 

@LoadBalanced
@Bean(name = "onvernodesRestTemplate")

public RestTemplate commonRestTemplate() {

  final RestTemplate restTemplate = new RestTemplate();
  // Code ...
  return restTemplate;
}

마이크로서비스간의 통신을 위해 RestTemplate을 사용하는데, Bean 설정 부분에 @LoadBalanced를 추가하였다. 

이 Annotation을 추가하면 Ribbon을 사용한 로드밸런싱 기능이 추가되고 요청을 보내기 전 Eureka에서 서비스의 이름으로 검색하여 적절한 경로에 요청을 넘긴다. 
Spring Security를 적용하여 OAuth2RestTemplate을 사용하는 경우도 마찬가지이다.

RestTemplate을 사용하여 요청을 넘길 때 주의할 점이 있다.

@Autowired
@Qualifier("overnodesRestTemplate")

private RestTemplate restTemplate;

private String userServiceName = "USER-SERVICE";

private String apiPath = "/get-path";

public JsonObject get(){

  return restTemplate.getForEntity("http://" + userServiceName + apiPath, JsonObject.class).getBody();

}
 

Zuul 이란?

모든 마이크로서비스에 대한 요청을 먼저 받아들이고 라우팅하는 프록시 API Gateway 기능을 수행한다.

Spring Cloud의 적용 과정 : Zuul

Eureka의 설정이 완료 되었으면 Zuul을 적용해 보자. 먼저 API Gateway로 사용될 프로젝트를 생성하고 설정을 진행해야 한다.
 

1.    Spring Boot 프로젝트를 하나 생성하고 bulid.gradle 파일에 Dependency를 추가한다.

dependencies {
compile('org.springframework.cloud:spring-cloud-starter-netflix-zuul)
compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client')
}


2. application.yml 파일에 Zuul설정에 필요한 정보를 추가한다.
 

# -- Default spring configuration

spring:
  application:
    name: api-gateway-service

# -- Eureka client
eureka:
  client:
    serviceUrl:
      defaultZone: ${EUREKA_URL:http://127.0.0.1:8787/eurek

# -- Zuul
zuul: 
 routes:
  user-service:
   path: /user/**
   service-id: USER-SERVICE


l  이 Api gateway가 받는 요청의 path가 /user/** 형식이라면 USER-SERVICE 라는 이름을 갖는 마이크로서비스에 요청을 넘기도록 설정하였다.

3.  Application.java 파일의 Class에 @EnableZuulProxy와 @EnableDiscoveryClient를 추가한다.

 

@EnableZuulProxy

@EnableDiscoveryClient
@SpringBootApplication


public class ApiGatewayServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayServiceApplication.class, args);
  }
}
 

설정이 완료되면 해당 프로젝트는 Zuul 프록시 역할을 하게된다. 이제 Zuul의 기능을 활용 해 볼 차례다. 나는 이 기능을 활용하기 위해 UI Service에서 User Service로 API 호출이 일어 날 때 요청이 직접적으로 User Service에 가는 것이 아니라 Api gateway를 거쳐 가도록 코드를 수정할 것이다.

 

@Autowired
@Qualifier("overnodesRestTemplate")

private RestTemplate restTemplate;

private String apiGatewayServiceName= "API-GATEWAY-SERVICE";

private String apiPath = "/user/get-path";

public JsonObject get(){

  return restTemplate.getForEntity("http://" + apiGatewayService + apiPath, JsonObject.class).getBody();

}

 

호출하는 경로가 User Service에서 Api Gateway로 바뀐 것을 볼 수 있다. 그리고apiPath 앞에 /user 라는 path가 추가되었다.  이렇게 다른 마이크로서비스로 요청하는 모든 부분의 경로를 Api Gateway로 수정하고 호출 경로에 /user등 이름으로 구분할 수 있도록 키워드가 추가될 것이다.

 

이 과정에서 우리팀이 고민했던 부분은 Spring security oauth2가 적용되어있기 때문에 UI Service에서 User Service쪽으로 Api Call이 일어날 때 OAuth2RestTemplate을 사용하여 Token값을 함께 넘기고 인증이 된 경우에만 호출이 정상적으로 이루어진다는 것이었다. 