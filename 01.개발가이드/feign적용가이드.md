# Feign 은 Netflix 에서 개발된 Http client binder 입니다.


```java
@SpringBootApplication
@EnableFeignClients
public class Application {
 
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
@FeignClient(value = "example", url = "${external-api.http-bin}")
public interface ExampleClient {
 
    @GetMapping("/status/{status}")
    void status(@PathVariable("status") int status);
}
```

```java
  ext {
      springCloudVersion = 'Finchley.RELEASE'
  }
  dependencyManagement {
      imports {
          mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
      }
  }
     
  dependencies {
      compile("org.springframework.cloud:spring-cloud-starter-openfeign")
  }
```  