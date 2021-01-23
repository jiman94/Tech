#### Spring Cloud Gateway 
```yaml 
spring:
  profiles: default
  cloud:
    gateway:
      routes:
        - id: order
          uri: http://localhost:8081
          predicates:
            - Path=/orders/** 
        - id: delivery
          uri: http://localhost:8082
          predicates:
            - Path=/deliveries/** 
        - id: customer
          uri: http://localhost:8083
          predicates:
            - Path=/custormers/** /myPages/**
        - id: bookInventory
          uri: http://localhost:8084
          predicates:
            - Path=/books/**/deliverables/**/stockInputs/** 
        - id: point
          uri: http://localhost:8085
          predicates:
            - Path=/points/** 
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins:
              - "*"
            allowedMethods:
              - "*"
            allowedHeaders:
              - "*"
            allowCredentials: true
```            