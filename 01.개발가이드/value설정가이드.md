 ```java
  @Value("${spring.application.name}")
  private String applicationName;

  @Value("${downstream.server.url:}")
  private String url;

```


```bash 
Start service 1
./mvnw spring-boot:run -Dspring.application.name=upstream -Dserver.port=8081 -Ddownstream.server.url=http://localhost:8082

Start service 2
./mvnw spring-boot:run -Dspring.application.name=middle -Dserver.port=8082 -Ddownstream.server.url=http://localhost:8083

Start service 3
./mvnw spring-boot:run -Dspring.application.name=downstream -Dserver.port=8083

```