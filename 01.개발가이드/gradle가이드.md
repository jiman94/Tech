#### settings.gradle

rootProject.name = 'pilot'
include 'pilot-module-common'
include 'pilot-module-utils'
include 'pilot-module-web'
include 'pilot-module-mail'
include 'pilot-module-member'
include 'pilot-module-log'
include 'pilot-module-resource'
include 'pilot-module-test'
include 'pilot-module-dbclient'
include 'pilot-module-incident'
include 'pilot-module-srcenter'
include 'pilot-service-dbclient'
include 'pilot-service-incident'
include 'pilot-service-srcenter'

### build.gradle

```yaml 
bootJar.enabled = false
jar.enabled = true 

test {
	useJUnitPlatform {
      includeTags "fast", "smoke & feature-a" //@Tag("대응")
      includeEngines "junit-jupiter"
	}
}

dependencies {
	implementation project(':pilot-module-utils')
}
```

```yaml 
bootJar.enabled = false
jar.enabled = true
	
dependencies {
	implementation project(':pilot-module-utils')
	implementation project(':pilot-module-common')
	testImplementation project(':pilot-module-test').sourceSets.test.output
}
```

```yaml 
apply plugin: 'com.palantir.docker'
apply plugin: 'application'
mainClassName = 'oss.pilot.Application'

bootJar.enabled = true

dependencies {
	implementation project(':pilot-module-common')
	implementation project(':pilot-module-utils')
	testImplementation project(':pilot-module-test').sourceSets.test.output
}

ext {
    BUILD_VERSION = new Date().format("yyyyMMddHHmmss") // 빌드한 시간을 Image Tag 로 표시하기 위하여 사용한다.
}

docker {
    name "pilot/srcenter"
    tags BUILD_VERSION, "latest"
    files tasks.bootJar.outputs.files
    buildArgs(['JAR_FILE': tasks.bootJar.outputs.files.singleFile.name])
}

```