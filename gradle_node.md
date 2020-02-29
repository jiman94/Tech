
#### build.gradle
```yaml 
buildscript {
    repositories {
        maven {
            url "https://plugins.gradle.org/m2/"
        }
    }
    dependencies {
        classpath 'com.moowork.gradle:gradle-node-plugin:1.2.0'
    }
}
plugins {
    id 'org.springframework.boot' version '2.1.6.RELEASE'
    id 'java'
}

apply plugin: 'com.moowork.node'
apply plugin: 'io.spring.dependency-management'

group = 'com.chicor'
sourceCompatibility = '1.8'

node {
    version = '9.8.0'
    npmVersion = '5.6.0'
    download = true
    nodeModulesDir = file('frontend')
}
repositories {
    mavenCentral()
}

ext {
    set('springCloudVersion', "Greenwich.SR2")
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.cloud:spring-cloud-starter-config'
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-zuul'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    // lombock
    compileOnly 'org.projectlombok:lombok:1.18.8'
    annotationProcessor 'org.projectlombok:lombok:1.18.8'

    // gson
    compile 'com.google.code.gson:gson:2.8.5'

    // jackson dataformat
    compile 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.8.5'
    
    compile fileTree(dir: 'libs', include: '*.jar')
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}

bootRun {
    systemProperties System.properties
}

// build시 npm 빌드까지 되도록 해주는 소스
task webpack(type: NpmTask, dependsOn: 'npmInstall') {
    workingDir = file("frontend")
    args = ['run', 'build']
}
processResources.dependsOn 'webpack'
```
