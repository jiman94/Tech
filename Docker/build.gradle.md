```bash 
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:1.5.8.RELEASE")
        classpath('se.transmode.gradle:gradle-docker:1.2')
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'docker'

description = "gradle-springboot"

jar {
    baseName = 'gradle-springboot'
    version =  '1.0'
}

def javaVersion = JavaVersion.VERSION_1_8

sourceCompatibility = javaVersion
targetCompatibility = javaVersion

tasks.withType(JavaCompile) {
	options.encoding = 'UTF-8'
}

repositories {   
     mavenCentral()
}

dependencies {
    compile group: 'org.springframework.boot', name: 'spring-boot-starter-web', version:'1.5.8.RELEASE'
    compile group: 'org.springframework.boot', name: 'spring-boot-starter-tomcat', version: '1.5.8.RELEASE'
    compile group: 'org.springframework.boot', name: 'spring-boot-starter-data-jpa', version:'1.5.8.RELEASE'
    compile group: 'mysql', name: 'mysql-connector-java', version:'5.1.44'
    compile group: 'commons-dbcp', name: 'commons-dbcp', version:'1.4'
    compile group: 'org.apache.commons', name: 'commons-collections4', version:'4.1'
    compile group: 'org.springframework.boot', name: 'spring-boot-legacy', version:'1.1.0.RELEASE'
}

task buildDocker (type:Docker, dependsOn: build) {
	applicationName = jar.baseName
	dockerfile = file('Dockerfile')
	doFirst {
		copy {
			from jar
			into stageDir
		}
	}
}

```
