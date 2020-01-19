# build.gradle 

buildscript {
ext {
springBootVersion = "2.2.2.RELEASE"
lombokVersion = "1.18.2"
}
}

compileOnly "org.projectlombok:lombok:${lombokVersion}"
annotationProcessor "org.projectlombok:lombok:${lombokVersion}"
testCompileOnly "org.projectlombok:lombok:${lombokVersion}"
testAnnotationProcessor "org.projectlombok:lombok:${lombokVersion}"



bootRun {
   jvmArgs = ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005"]
}

