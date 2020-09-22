[SpringBoot] session 을 redis 서버에 저장하기.(gradle)
demonic_ 2018. 7. 12. 14:47

 
세션클러스터링을 위해 세션 스토리지를 따로 구성하는 경우가 있는데 자주 쓰이는 것으로 redis 가 있습니다.



여기서는 윈도우에서 redis 설치와 세션정보가 redis에 저장될 수 있도록 구성하겠습니다.

Redis 는 64비트 Windows 만 지원합니다.





1. Redis 설치하기.

Redis는 공식적으로 윈도우를 지원하지 않습니다. 그래서 MSOpenTech라는 곳에서 지속적으로 윈도우 버전을 릴리즈 하고 있으니 아래사이트에서 다운받아 설치하면 됩니다.



Zip 파일을 다운로드 받습니다.

https://github.com/MicrosoftArchive/redis/releases









2. Redis 서버 인스톨 & 접속하기

받은 ZIP 파일의 압축을 해제하고 명령프롬프트로 해당 폴더로 이동합니다.

2. Redis 서버 인스톨 & 접속하기
받은 ZIP 파일의 압축을 해제하고 명령프롬프트로 해당 폴더로 이동합니다.

# Redis 서버를 설치합니다.
# 기본포트는 6379 입니다.
# 포트번호를 수정하시려면 redis.windows.conf 파일 내에 port 6379를 수정하시면 됩니다.
redis-server --service-install redis.windows.conf --loglevel verbose
redis-server --service-start

이제 redis-client 로 접근해보겠습니다.
redis-cli

127.0.0.1:6379> 
127.0.0.1:6379> keys *
(empty list or set)
현재 데이터가 아무것도 없음을 확인했습니다.






 



이제 SpringBoot 를 설정해보도록 하겠습니다.



1. build.gradle 설정

buildscript {
    ext {
        springBootVersion = '2.0.3.RELEASE'
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = 1.8

repositories {
    mavenCentral()
}


dependencies {
    compile('org.springframework.boot:spring-boot-starter-data-redis')
    compile('org.springframework.boot:spring-boot-starter-thymeleaf')
    compile('org.springframework.boot:spring-boot-starter-web')
    compile('org.springframework.session:spring-session-data-redis')
    runtime('org.springframework.boot:spring-boot-devtools')
    compileOnly('org.projectlombok:lombok')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}




2. application.yml 파일 수정

spring:
  redis:
    host: localhost
    port: 6379




3. Application 설정

클래스내에 RestController 를 설정하였고, 분리하여도 무방.

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@RestController
class SessionController{
//    @Value("${redis.host}")
//    private String ip = null;

    private static String ip;

    public SessionController(@Value("${CF_INSTANCE_IP:127.0.0.1}") String ip){
        this.ip = ip;
    }

    @GetMapping("hi")
    Map uid(HttpSession session){
        UUID uid = Optional.ofNullable(UUID.class.cast(session.getAttribute("uid")))
                .orElse(UUID.randomUUID());
        session.setAttribute("uid", uid);

        Map m = new HashMap<>();
        m.put("instance_ip", this.ip);
        m.put("uuid", uid.toString());
        return m;
    }
}



 

4. 결과확인

브라우저에서 아래를 호출하면 다음과같이 리턴합니다.

http://localhost:8080/hi
{"instance_ip":"127.0.0.1","uuid":"ead26e8a-4cd6-4f4b-aa92-cb539d26220b"}


Redis 서버에서 확인

127.0.0.1:6379> keys *
1) "spring:session:sessions:eae72b76-dd1c-4cf8-99e1-279751f19257"
2) "spring:session:sessions:expires:eae72b76-dd1c-4cf8-99e1-279751f19257"
3) "spring:session:expirations:1531374540000"


만약 redis 서버내 정보를 지우면 세션정보가 변경되는 것을 확인할 수 있습니다.

# redis 삭제
127.0.0.1:6379> flushall

# 브라우저 결과
{"instance_ip":"127.0.0.1","uuid":"14197909-3516-4ad4-a7dc-fc50b3edfd40"}

# redis 확인
127.0.0.1:6379> keys *
1) "spring:session:expirations:1531374720000"
2) "spring:session:sessions:625d3ac1-8751-42af-928a-f74afec154c0"
3) "spring:session:sessions:expires:625d3ac1-8751-42af-928a-f74afec154c0"