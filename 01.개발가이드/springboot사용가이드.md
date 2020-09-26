# Spring Boot Admin

```build.gradle 
compile group: 'de.codecentric', name: 'spring-boot-admin-starter-server', version: '2.3.0'
compile group: 'de.codecentric', name: 'spring-boot-admin-server-ui', version: '2.3.0'
```

```java
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class SpringBootAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }

}
package org.springframework.samples.petclinic.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class SpringBootAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }

}
```    

# application.yaml
```
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

# application.yaml
```
spring:
  security:
    user:
      name: jaehyun
      password: 1234
```

<dependency>
    <groupId>io.zipkin.java</groupId>
    <artifactId>zipkin-server</artifactId>
</dependency>
<dependency>
    <groupId>io.zipkin.java</groupId>
    <artifactId>zipkin-autoconfigure-ui</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>

# Enabling Zipkin Server

@SpringBootApplication
@EnableZipkinServer
public class ZipkinApplication {...}



<dependency>
    <groupId>org.jolokia</groupId>
    <artifactId>jolokia-core</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>


compile 'io.micrometer:micrometer-registry-prometheus:latest.release'


management.metrics.export.prometheus.enabled: true
management.metrics.distribution.percentiles-histogram.http: true


dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    implementation 'io.micrometer:micrometer-registry-prometheus'
}


management:
  endpoints:
    web:
      exposure:
        include: health, info, prometheus


 http://localhost:8080/actuator



 http://localhost:8080/actuator/prometheus


# Prometheus Server는 기동시 /etc/prometheus/prometheus.yml 설정 파일을 사용한다. docker volume mount를 이용해 Prometheus Server에서 사용할 설정 prometheus.yml 파일을 만들어보자.

global:
  scrape_interval: 10s # 10초 마다 Metric을 Pulling
  evaluation_interval: 10s
scrape_configs:
  - job_name: 'spring-boot-app'
    metrics_path: '/actuator/prometheus' # Application prometheus endpoint
    static_configs:
      - targets: ['host.docker.internal:8080'] # Application host:port


# docker run -p 9090:9090 -v /Users/user/work/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml --name prometheus -d prom/prometheus --config.file=/etc/prometheus/prometheus.yml

 http://localhost:9090 

 docker run -d --name=grafana -p 3000:3000 grafana/grafana


 http://localhost:3000 에 접속해보자. 다음과 같이 Grafana login 화면을 볼 수 있다.
 ID/PW인 admin/admin 으로 로그인할 수 있다.


spring:
  profiles:
    active: local
    
spring.profiles: real2
spring.profiles.include: real-db, information

server:
  port: 8082










