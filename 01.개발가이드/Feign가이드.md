1. Feign Client 란?

1) Feign Client는 web service 클라이언트를 보다 쉽게 작성할 수 있도록 도와줍니다.
2) interface를 작성하고 annotation을 붙여주면 세부적인 내용 없이 사용할 수 있기 때문에 코드 복잡도가 낮아집니다.
3) Netflix 에서 만들어졌고, spring-cloud-starter-openfeign 으로 스프링 라이브러리에서 사용할 수 있습니다.


1) 스프링부트 프로젝트 만들기
spring start에서 스프링부트 프로젝트를 만들었습니다. (maven, gradle 상관없습니다.) 라이브러리는 web만 넣어주었습니다.

2) feign client 라이브러리 넣기

```java 
dependencyManagement {
    imports {
        mavenBom 'org.springframework.cloud:spring-cloud-dependencies:Greenwich.RELEASE'
    }
}

dependencies {
//...
    // Feign Client
    compile 'org.springframework.cloud:spring-cloud-starter-openfeign'
//...
}
```

```yaml 
spring:
  application:
    name: feign

server:
  port: 8080

feign:
  client:
    config:
      default: # @FeignClient 에서 name 값, 전역으로 설정하려면 default
        connectTimeout: 5000
        readTimeout: 2000
        loggerLevel: FULL

external-api:
  http-bin: https://httpbin.org

logging:
  level:
    io.github.mayaul: DEBUG
```    
3) Application.java

```java
package com.example.feignTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // Feign Client를 사용할 것임을 알려줍니다.
@SpringBootApplication
public class FeignTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignTestApplication.class, args);
	}

}
````
4) Client 작성

```java

package io.github.mayaul.feign.tip;

import io.github.mayaul.config.FeignConfiguration;
import io.github.mayaul.config.FeignRetryConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "retry", url = "${external-api.http-bin}", configuration = {FeignConfiguration.class, FeignRetryConfiguration.class})
public interface RetryClient {

    @GetMapping("/status/{status}")
    void status(@PathVariable("status") int status);
}
```

```java
package com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="feign", url="http://localhost:8080")
public interface TestClient {

    @GetMapping("/testfeign")
    String testFeign();
}
```
5) Service 작성

```java
package com.service;

import com.client.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
  	//@Autowired를 통해 방금 작성한 client 의존성 주입
    @Autowired
    TestClient testClient;

  	// client의 기능을 사용할 메소드 testFeign 작성
    public String testFeign() {
        return testClient.testFeign();
    }
}
```
6) Controller 작성

```java
package com.controller;

import com.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  	// 1. 작성한 서비스를 가져오고
    private final TestService testService;

    // 2. 서비스를 사용하기 위한 생성자를 만들어줍니다.
    public TestController(TestService testService) {
        this.testService = testService;
    }

    // 1) 메인 페이지로 접근
    // 2) Feign Client가 /testfeign 으로 get 호출
    // 3) 반환값 받고 메인에서 보여줍니다.
    @GetMapping("/")
    public String main() {
        return testService.testFeign();
    }

    // Feign Client 요청에 응답을 주기 위한 컨트롤러
    @GetMapping("/testfeign")
    public String testFeign() {
        return "Hello Feign Cleint~ 찡긋";
    }
}
```

7) 확인
http://localhost:8080/
