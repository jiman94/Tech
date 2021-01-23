```yaml 
server:
  port: 8080
---
spring:
  profiles: default
  cloud:
    stream:
      kafka:
        binder:
          brokers: localhost:9092
        streams:
          binder:
            configuration:
              default:
                key:
                  serde: org.apache.kafka.common.serialization.Serdes$StringSerde
                value:
                  serde: org.apache.kafka.common.serialization.Serdes$StringSerde
      bindings:
        event-in:
          group: delivery
          destination: 
          contentType: application/json
        event-out:
          destination: 
          contentType: application/json
```
