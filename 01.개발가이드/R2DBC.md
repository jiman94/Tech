# Spring R2DBC

- Spring 에서 사용되는 JDBC Datasource는 항상 Blocking 방식으로 제공되어졌다.
- 한 Query를 수행하고 그에 대한 결과를 받기까지는 항상 Blocking이 되어 해당 쓰레드가 대기하는 문제점이 존재한다.
- Spring을 사용하면서도 Async-Nonblokcing Datasource를 함께 사용할 수 있다는 뜻이 된다.

- Postgres (io.r2dbc:r2dbc-postgresql)
- H2 (io.r2dbc:r2dbc-h2)
- Microsoft SQL Server (io.r2dbc:r2dbc-mssql)
- MySQL (com.github.mirromutth:r2dbc-mysql)
- jasync-sql MySQL (com.github.jasync-sql:jasync-r2dbc-mysql)

#### Spring Boot(Reactive) + R2DBC + MySQL Driver 

```xml 
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>io.r2dbc</groupId>
      <artifactId>r2dbc-bom</artifactId>
      <version>${r2dbc-releasetrain.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-r2dbc</artifactId>
    <version>1.0.0.BUILD-SNAPSHOT</version>
  </dependency>
  <dependency>
    <groupId>io.r2dbc</groupId>
    <artifactId>r2dbc-h2</artifactId>
    <version>Arabba-RELEASE</version>
  </dependency>
</dependencies>
```

```java
// build.gradle

implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
compile 'dev.miku:r2dbc-mysql:0.8.0.RELEASE'
annotationProcessor 'org.projectlombok:lombok'
compile 'org.projectlombok:lombok:1.16.20'

// application.properties

spring.r2dbc.url=r2dbc:mysql://localhost:3306/test_schema?useUnicode=true&characterEncoding=utf8
spring.r2dbc.username=root
spring.r2dbc.password=root123

// User.java

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private String registDate;
}
// UserRepository.java

import com.taes.r2dbc.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends R2dbcRepository<User, String> {
}

// UserService.java

import com.taes.r2dbc.entity.User;
import com.taes.r2dbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> getAllUser()
    {
        return userRepository.findAll();
    }
}

```


logging.level.org.springframework.data.r2dbc=DEBUG


```java
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void test() {
        Flux<User> allUser = userRepository.findAll();
        List<User> blocked = allUser.collectList().block();
        blocked.forEach(item -> log.info("user : {}", item));
    }
}
```


##### Async/Non-Blocking하게 데이터들을 가져오도록 구현

public class SimpleR2dbcRepository<T, ID> implements ReactiveCrudRepository<T, ID> {
  ...
  public Flux<T> findAll() {
    return this.databaseClient.select().from(this.entity.getJavaType()).fetch().all();
  }
}

