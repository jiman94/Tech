
```java
compile group: 'org.springframework.cloud', name: 'spring-cloud-starter-zipkin', version: '2.2.4.RELEASE'
```

curl -sSL https://zipkin.io/quickstart.sh | bash -s

```java
java -jar zipkin.jar
```

http://localhost:9411/zipkin/


http://localhost:8080/first/start
-> 
http://localhost:8081/second/ping

[first-point,4ccb4cdd7bbbcc74,4ccb4cdd7bbbcc74,true]


sleuth_example : 어플리케이션 이름
654228cc354b4b60 : trace ID
654228cc354b4b60 : span ID
true : exportable (true or false) - zipkin 등 외부로 이 값을 전송하는지 여부



[build.gradle]
compile group: 'com.github.danielwegener', name: 'logback-kafka-appender', version: '0.1.0'
compile group: 'ch.qos.logback', name: 'logback-classic', version: '1.1.6'
compile group: 'ch.qos.logback', name: 'logback-core', version: '1.1.6'
compile group: 'net.logstash.logback', name: 'logstash-logback-encoder', version: '4.8'


<configuration>
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
<encoder>
<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
</encoder>
</appender>
<appender name="asyncMyLogKafka"
class="net.logstash.logback.appender.LoggingEventAsyncDisruptorAppender">
<appender name="kafkaVerboseAppender"
class="com.github.danielwegener.logback.kafka.KafkaAppender">
<encoder
class="com.github.danielwegener.logback.kafka.encoding.LayoutKafkaMessageEncoder">
<layout class="ch.qos.logback.classic.PatternLayout">
<pattern>my_logs-%msg</pattern>
</layout>
</encoder>
<topic>my_topic</topic>
<keyingStrategy
class="com.github.danielwegener.logback.kafka.keying.RoundRobinKeyingStrategy" />
<deliveryStrategy
class="com.github.danielwegener.logback.kafka.delivery.AsynchronousDeliveryStrategy" />
<producerConfig>bootstrap.servers=localhost:9092</producerConfig>
<producerConfig>retries=2</producerConfig>
</appender>
<appender-ref ref="STDOUT">
</appender>
<root level="info">
<appender-ref ref="asyncMyLogKafka" />
</root>
</configuration>
