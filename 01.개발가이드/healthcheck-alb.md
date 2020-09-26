
```java
curl -v https://api.pilot.com/member/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/member/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}


curl -v https://api2.pilot.com/front/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/front/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}

curl -v https://api2.pilot.com/system/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/system/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}



curl -v https://api2.pilot.com/cs/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/cs/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}


curl -v https://api2.pilot.com/promotion/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/promotion/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}


curl -v https://api2.pilot.com/order/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/order/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}

curl -v https://api2.pilot.com/product/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/product/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}


curl -v https://api2.pilot.com/product/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/product/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}

curl -v https://api2.pilot.com/aws/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/aws/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}


curl -v https://api2.pilot.com/co/v1/healthcheck/alb

@RestController
@RequestMapping(value = "/co/v1/healthcheck")
public class HealthCheckController {

    @GetMapping("/alb")
    public String healthCheck() {
        return "ok";
    }

}
```


#### routes 

```java
routes:
    system-api:
      path: /system/v1/**
      url: http://api2i.pilot.com
      sensitiveHeaders:
      stripPrefix: false
    user-api:
      path: /member/v1/**
      url: https://api.pilot.com
      sensitiveHeaders:
      stripPrefix: false
    cs-api:
      path: /cs/v1/**
      url: http://api2i.pilot.com
      sensitiveHeaders:
      stripPrefix: false
    promotion-api:
      path: /promotion/v1/**
      url: http://api2i.pilot.com
      sensitiveHeaders:
      stripPrefix: false
    order-api:
      path: /order/v1/**
      url: http://api2i.pilot.com
      sensitiveHeaders:
      stripPrefix: false
    product-api:
      path: /product/v1/**
      url: http://api2i.pilot.com
      sensitiveHeaders:
      stripPrefix: false
    front-api:
      path: /front/v1/**
      url: http://api2i.pilot.com
      sensitiveHeaders:
      stripPrefix: false
    aws-api:
      path: /aws/v1/**
      url: http://api2i.pilot.com
      sensitiveHeaders:
      stripPrefix: false
    co-api:
      path: /co/v1/**
      url: http://api2i.pilot.com
      sensitiveHeaders:
      stripPrefix: false  
    juso-api:
      path: /app/search/**
      url: https://zipcode.pilot.com
      sensitiveHeaders:
      stripPrefix: false


spring:
  application:
    name: pilot-api-front
  logging:
    name: front_api.log
    path: /logs/api/front/
  cloud:
    config:
      name: api,api-message,api-keyword,api-aws,api-main,api-login-exclude
      uri: http://config.stg.pilot.co.kr:8888

server:
  port: 8097

management:
  endpoints:
    web:
      exposure:
        include: refresh

```