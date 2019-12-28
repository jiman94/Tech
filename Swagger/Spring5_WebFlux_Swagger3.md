Spring Boot — 2.2.0.RELEASE
JDK — 11 or later
Spring Framework — 5.2.0 RELEASE
Gradle — 5.6.2 +
Swagger — 2+
springfox-swagger2 — 3.0.0
springfox-swagger-ui — 3.0.0

http://start.spring.io/
https://github.com/pgilad/spring-boot-webflux-swagger-starter.git
http://localhost:8080/swagger-ui.html

```java
-vm
D:/topasIBE/Java/jdk-13.0.1/bin
-Xms2g
-Xmx6g
-XX:MetaspaceSize=2g
-XX:MaxMetaspaceSize=6g 
-Dfile.encoding=UTF-8 
``
- build.gradle

```java
plugins {
    id 'java'
    id 'idea'
    id 'org.springframework.boot' version '2.2.0.RELEASE'
    id 'io.spring.dependency-management' version '1.0.8.RELEASE'
    id 'com.github.ben-manes.versions' version '0.27.0'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

sourceCompatibility = JavaVersion.VERSION_11
targetCompatibility = JavaVersion.VERSION_11

springBoot {
    mainClassName = 'com.example.swagger.Application'
}

wrapper {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = '5.6.2'
}

repositories {
    jcenter()
    maven { url 'http://oss.jfrog.org/artifactory/oss-snapshot-local/' }
}

dependencies {
    compile 'org.springframework.boot:spring-boot-starter-webflux'

    compile 'io.springfox:springfox-swagger2:3.0.0-SNAPSHOT'
    compile 'io.springfox:springfox-swagger-ui:3.0.0-SNAPSHOT'
    compile 'io.springfox:springfox-spring-webflux:3.0.0-SNAPSHOT'

    testCompile 'org.springframework.boot:spring-boot-starter-test'
    testCompile 'io.projectreactor:reactor-test:3.3.0.RELEASE'
}
```
```java
package com.example.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}


package com.example.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebfluxConfig implements WebFluxConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

package com.example.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static java.util.Objects.requireNonNullElse;

@RestController
public class HelloController {

    @GetMapping("/hello/get")
    public Mono<String> getHello() {
        return Mono.just("Getting back..");
    }

    @PostMapping("/hello/post")
    public Mono<String> postHello() {
        return Mono.just("Posting back..");
    }

    @GetMapping("/person")
    public Mono<Person> getPerson(String name, Integer age) {
        var personName = requireNonNullElse(name, "Anonymous");
        var personAge = requireNonNullElse(age, 21);
        var person = new Person(personName, personAge);

        return Mono.just(person);
    }
}

package com.example.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

package com.example.swagger;

@SuppressWarnings("WeakerAccess")
class Person {
    public String name;
    public Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}


```



- pom.xml 

```java
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.0.RELEASE</version>
    <relativePath /> <!-- lookup parent from repository -->
  </parent>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
    <swagger.version>3.0.0-SNAPSHOT</swagger.version>
  </properties>
  <dependencies> <!-- srping webflux -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency> <!-- swagger 3 -->
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>${swagger.version}</version>
    </dependency>
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>${swagger.version}</version>
    </dependency>
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-spring-webflux</artifactId>
      <version>${swagger.version}</version>
    </dependency>
  </dependencies>

  <repositories>
    <repository>
      <id>oss-snapshot</id>
      <name>OSS Snapshot</name>
      <url>http://oss.jfrog.org/oss-snapshot-local</url>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
  </repositories>
```  

```java

@EnableSwagger2WebFlux
@SpringBootApplication
public class Boot2WithSwaggerApplication {

  public static void main(String[] args) {
    SpringApplication.run(Boot2WithSwaggerApplication.class, args);
  }

}
```

#Configuration

```java
@Configuration
public class swaggerConfiguration {

  @Bean
  public Docket swaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerApiInfo()).select() 
 .apis(RequestHandlerSelectors.basePackage("io.github.gsealy.boot2withswagger.controller")) 
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo swaggerApiInfo() {
    return new ApiInfoBuilder().title("webflux-swagger2 API doc")
        .description("how to use this")
        .termsOfServiceUrl("https://github.com/Gsealy")
        .contact(new Contact("Gsealy", "https://gsealy.github.io", "gsealy@gmail.com")) 
        .version("1.0")
        .build();
  }
}
```

# Controller
```java
@RestController
@RequestMapping("/apis")
@Api(value = "Swagger test Controller", description = "learn how to use swagger")
public class SwaggerController {

  @GetMapping
  @ApiOperation(value = "GET Method", response = String.class)
  public Mono<String> get() {
    return Mono.just("this is GET Met" + "hod.");
  }

  @PostMapping
  @ApiOperation(value = "POST Method", response = String.class)
  public Mono<String> post() {
    return Mono.just("this is POST Method.");
  }

  @PutMapping
  @ApiOperation(value = "PUT Method", response = String.class)
  public Mono<String> put() {
    return Mono.just("this is PUT Method.");
  }

  @DeleteMapping
  @ApiOperation(value = "DELETE Method", response = String.class)
  public Mono<String> delete() {
    return Mono.just("this is DELETE Method.");
  }

}

```
