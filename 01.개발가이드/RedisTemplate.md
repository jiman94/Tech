### RedisTemplate

```java
@Autowired
@Qualifier("redisTemplate")
private RedisTemplate<String, Object> template;
```    



compile group: 'de.codecentric', name: 'spring-boot-admin-starter-server', version: '2.3.0'


```java
@SpringBootApplication
@EnableAdminServer
public class AdminmonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminmonitorApplication.class, args);
    }

}
```


```yaml 
spring
  boot:
    admin:
      client:
        url: http://localhost:8080
        instance:
          service-url: http://localhost:8080
management:
  endpoints:
    web:
      exposure:
        include: "*"
server:
  port: 18080
 ``` 


```yaml 
spring:
  security:
    user:
      name: jmryu
      password: 1234
````

```yaml
spring:
  boot:
    admin:
      client:
        url: http://localhost:8080
        username: jmryu
        password: 1234
        instance:
          metadata:
            user.name: jmryu
            user.password: 1234
```
