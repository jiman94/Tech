# Hystrix, Hystrix dashboard, Turbine 의 구성
Dependencies Used
Spring Boot 2.1.9
Spring Cloud Gateway
Spring Cloud Netflix Hystrix
Spring Cloud Netflix Hystrix Dashboard

http://localhost:8060/personalized/1

http://localhost:8070/recommendations

curl -d '{"username":"foo","password":"foo"}' -H "Content-Type: application/json" -X POST http://localhost:8080/authenticate




### Hystrix Dashboard 인스턴스 
http://localhost:8081/hystrix

### Hystrix 적용 인스턴스 
http://localhost:8080/actuator/hystrix.stream

```java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class SampleController
{
    @RequestMapping(value = "/")
    @HystrixCommand(fallbackMethod = "planb", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String hello() throws InterruptedException {
        Thread.sleep(2000);
        return "Hello World";
    }
    private String planb() {
        return "Sorry our Systems are busy! try again later.";
    }
}   
```



### Turbine
#### Cluster via Turbine (custom cluster): 
https://turbine-hostname:port/turbine.stream?cluster=[clusterName]

http://localhost:9090/turbine.stream?cluster=RECOMMENDATION-SERVICE

#### Cluster via Turbine (default cluster): 
https://turbine-hostname:port/turbine.stream

#### Single Hystrix App: 
https://hystrix-app:port/actuator/hystrix.stream


### zipkin 
```java
https://repo1.maven.org/maven2/io/zipkin/zipkin-server/2.19.3/

java -jar zipkin.jar

http://localhost:9411/zipkin/
```


#### build.gradle

```java
plugins {
    id 'org.springframework.boot' version '2.2.4.RELEASE'
    id 'io.spring.dependency-management' version '1.0.9.RELEASE'
    id 'java'
}

group = 'oss.pilot'
version = '1.0.1'
sourceCompatibility = '1.8'

repositories {
    mavenCentral()
}

ext {
    set('springBootAdminVersion', "2.2.1")
    set('springCloudVersion', "Hoxton.SR1")
}

dependencies {

    compile 'org.springframework.boot:spring-boot-starter-actuator'

    compile 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'
    compile 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
    
    compile 'org.springframework.cloud:spring-cloud-starter-netflix-hystrix-dashboard'
    compile 'org.springframework.cloud:spring-cloud-starter-netflix-hystrix'
    
    compile 'org.springframework.cloud:spring-cloud-starter-openfeign'
    
    compile 'org.springframework.cloud:spring-cloud-starter-netflix-zuul'
    
    compile 'org.springframework.cloud:spring-cloud-starter-sleuth'
    compile 'org.springframework.cloud:spring-cloud-starter-zipkin'
    
    compile 'org.springframework.cloud:spring-cloud-starter-netflix-turbine'
    
    
    compile 'de.codecentric:spring-boot-admin-starter-server'
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'  
}


dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
        mavenBom "de.codecentric:spring-boot-admin-dependencies:${springBootAdminVersion}"
    }
}

test {
    useJUnitPlatform()
}


```

####  Eureka & SpringBootAdmin 


```java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableEurekaServer
@EnableAdminServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}


```


```yaml 
server:
  port: 8761
spring:
  application:
    name: eureka-discovery
  profiles:
    active: local
eureka:
  client:
    registryFetchIntervalSeconds: 5
    registerWithEureka: false
#    serviceUrl:
#      defaultZone: ${DISCOVERY_URL:http://localhost:8761}/eureka/
  instance:
    leaseRenewalIntervalInSeconds: 10    
---    
management:
  security:
    enabled: false    
---    
spring:
  boot:
    admin:
      context-path: /admin        
```



### Hystrix Dashboard

```java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
@EnableDiscoveryClient
@EnableHystrixDashboard
public class HystrixTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApplication.class, args);
    }

}
```

```yaml 
server:
  port: 9090
spring:
  application:
    name: hystrix-dashboard
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    preferIpAddress: false
    leaseRenewalIntervalInSeconds: 10
    leaseExpirationDurationInSeconds: 30
    metadataMap:
      instanceId: ${spring.application.name}:${spring.application.instance_id:${random.value}}
---    
management:
  security:
    enabled: false    
---
turbine: 
  appConfig: user-service,recommendation-service
  aggregator: 
    clusterConfig: USER-SERVICE,RECOMMENDATION-SERVICE
#or
#turbine.clusterNameExpression= new String("default")      

```
### RecommendationServiceApp

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class RecommendationServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(RecommendationServiceApp.class, args);
    }

}
```

```yaml 
server:
  port: 8070
spring:
  application:
    name: recommendation-service
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    preferIpAddress: false
    leaseRenewalIntervalInSeconds: 10
    leaseExpirationDurationInSeconds: 30
    metadataMap:
      instanceId: ${spring.application.name}:${spring.application.instance_id:${random.value}}
---    
management:
  endpoints:
    web:
      exposure:
        include: "*"
  security:
    enabled: false
```

### UserServiceApp
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class UserServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApp.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
```

```yaml 
server:
  port: 8060
spring:
  application:
    name: user-service
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    preferIpAddress: false
    leaseRenewalIntervalInSeconds: 10
    leaseExpirationDurationInSeconds: 30
    metadataMap:
      instanceId: ${spring.application.name}:${spring.application.instance_id:${random.value}}
---    
management:
  endpoints:
    web:
      exposure:
        include: "*"
  security:
    enabled: false
```



### Hystrix 
...
compile('org.springframework.cloud:spring-cloud-starter-hystrix')
...

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CompositeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompositeApiApplication.class, args);
    }
}

public Page<Product> getDefaultProducts(int page, int size, String sort) {
    logger.debug("page : {}, size : {}", page, size);

    return new PageImplBean<Product>();

@HystrixCommand(fallbackMethod = "getDefaultProducts")
@Override
public Page<Product> getProducts(int page, int size, String sort) {
    String uri = new StringBuffer(PRODUCT_API_URL).append("/?size={size}&page={page}&sort={sort}").toString();
    Map<String Object> pageMap = new HashMap<>();
    pageMap.put("page", page);
    pageMap.put("size", size);
    pageMap.put("sort", sort);


    ResponseEntity<PageImplBean<Product>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<PageImplBean<Product>>() {
    }, pageMap);

    if (responseEntity == null || responseEntity.getStatusCode() != HttpStatus.OK) {
        return null;
    }

    return responseEntity.getBody();
}
```




### Turbine

Turbine은 Hystrix 를 이용한 Circuit 정보를 모아주는 역할(Aggregator)을 해 준다.
Hystrix 설정과 Hystrix dashboard 만으로도 모니터링을 충분하게 할 수 있겠지만, 많은 서비스가 구역마다 올라간다거나 하는 경우 cluster로 묶어서 관리할 수 있다면 더 좋지 않을까 하는 생각을 하게 되는데 이 요구를 만족하게 해주는 것이 turbine이다.

 
RabbitMQ Docker 설치.

## Docker가 이미 설치되어있고 사용자가 docker 그룹이라면...
 
# RabbitMQ 이미지를 Docker hub을 이용하여 설치
road$ docker run -d --hostname rabbitmq --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq
 
# RabbitMQ의 management plugin 활성화
road$ docker start -i -t rabbitmq bash
road$ rabbitmq-plugins enable rabbitmq_management


...java
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-eureka')
    compile('org.springframework.cloud:spring-cloud-starter-turbine-amqp')
}
```


Turbine 서버는 AMQP로 작업을 하는 경우 Configuration 서버는 쓰지 않는 것이 좋은 것 같다. 이상하게 port 들을 조정하는데도 port 중복이 발생한다. Turbine 의 기본 포트는 8989 인데, 이 포트는 Tomcat이 아니라 application 안의 Rx server가 사용하게 된다. 따라서 Tomcat이 해당 포트를 점유하려고 하면 오류가 발생하게 된다. Reference에서는 server.port, turbine.amqp.port, management.port 를 조정하라고 되어있는데, Configuration 서버의 설정을 사용하는 경우 계속 오류가 발생한다. 같은 설정을 Configuration 서버에 설정하는 것과 로컬에 설정하는 것이 다르게 동작한다. 어떤 문제 때문인지는 모르겠다. 내가 잘못한 것일지도 모른다.


spring:
  application:
    name: turbine
 
server:
  port: ${PORT:8989}
 
management:
  port: -1
 
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    preferIpAddress: false
    leaseRenewalIntervalInSeconds: 10
    leaseExpirationDurationInSeconds: 30
    metadataMap:
      instanceId: ${spring.application.name}:${spring.application.instance_id:${random.value}}



@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbineAmqp
public class TurbineServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineServiceApplication.class, args);
    }
}


Spring cloud 프로젝트들이 다 그렇듯 너무 간단하다. 
@EnableTurbineAmqp 만 사용하면 된다. 
이름에서도 알 수 있지만, AMQP를 사용하지 않을 때에는 @EnableTurbine 을 사용하면 된다.


우선 RabbitMQ의 Management dashboard를 이용해서 몇 가지 내용을 보면 아래와 같다.

이제 Hystrix dashboard에 접속하고, http://localhost:8989 를 입력해보자.
Hystrix 메인 화면에 보면
Cluster via Turbine (custom cluster): http://turbine-hostname:port/turbine.stream?cluster=[clusterName] 
라고 되어있는데, http://localhost:8989 형식으로만 해도 default cluster를 이용할 수 있다.

http://localhost:9090/turbin

http://localhost:8989/turbine.stream
http://localhost:8989/turbine.stream?cluster=default



ab -n 600 -c 10 http://localhost:9000/composite/product/1
