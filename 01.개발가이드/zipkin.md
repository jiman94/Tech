https://github.com/openzipkin-attic/docker-zipkin

docker-compose -f docker-compose.yml -f docker-compose-ui.yml up


#### template

```yaml 
buildscript {
    ext {
        //springBootVersion = '2.3.2.RELEASE'
        springBootVersion = '2.1.6.RELEASE'
        
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath "io.spring.gradle:dependency-management-plugin:1.0.9.RELEASE"
    }
}
```
#### springBootVersion = '2.1.6.RELEASE'

```yaml 
implementation("org.springframework.cloud:spring-cloud-starter-zipkin:2.1.4.RELEASE")
implementation("org.springframework.cloud:spring-cloud-starter-sleuth:2.1.4.RELEASE")
```

#### springBootVersion = '2.3.2.RELEASE'

```yaml 
compile group: 'org.springframework.cloud', name: 'spring-cloud-starter-zipkin', version: '2.2.4.RELEASE'
```


