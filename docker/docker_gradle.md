#### Build a simple Spring Boot app with Docker¶

$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
$ java -version
java version "1.8.0_45"
Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)

$ sudo apt-get install gradle
$ sudo apt-get install maven

#### Setup a Spring Boot App

$git clone https://github.com/spring-guides/gs-spring-boot-docker.git


```bash 
src/main/java/hello/Application.java

package hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

        @RequestMapping("/")
        public String home() {
                return "Hello Docker World";
        }


        public static void main(String[] args) {
                SpringApplication.run(Application.class, args);
        }

}
```

#### Dockerfile

```bash 
FROM java:8
VOLUME /tmp
ADD gs-spring-boot-docker-0.1.0.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```


### Build a Docker Image with Maven
#### pom.xml
```bash 
<properties>
        <docker.image.prefix>springio</docker.image.prefix>
</properties>
<build>
    <plugins>
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>docker-maven-plugin</artifactId>
            <version>0.2.3</version>
            <configuration>
                <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
                <dockerDirectory>src/main/docker</dockerDirectory>
                <resources>
                    <resource>
                        <targetPath>/</targetPath>
                        <directory>${project.build.directory}</directory>
                        <include>${project.build.finalName}.jar</include>
                    </resource>
                </resources>
            </configuration>
        </plugin>
    </plugins>
</build>
````

#### Using the maven build the docker image

$ mvn package docker:build

```bash 
build.gradle
buildscript {
    ...
    dependencies {
        ...
        classpath('se.transmode.gradle:gradle-docker:1.2')
    }
}

group = 'springio'

...
apply plugin: 'docker'

task buildDocker(type: Docker, dependsOn: build) {
  push = true
  applicationName = jar.baseName
  dockerfile = file('src/main/docker/Dockerfile')
  doFirst {
    copy {
      from jar
      into stageDir
    }
  }
}
```


$ ./gradlew build buildDocker

#### Run the Spring Boot App container

```bash 
$ docker images
REPOSITORY                        TAG                 IMAGE ID            CREATED             VIRTUAL SIZE
gregturn/gs-spring-boot-docker    latest              3e70f57df702        21 hours ago        841.4 MB
```

$ docker run -p 8080:8080 -t gregturn/gs-spring-boot-docker

The application is now available and can be accessed at the address http://localhost:8080.

Reference
https://spring.io/guides/gs/spring-boot-docker/#initial
