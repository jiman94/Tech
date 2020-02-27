
https://jenkins.io/download/

```bash 

rem java -jar D:/Project/tools/jenkins/jenkins.war --httpPort=8080
rem # nohup java –jar /data/jenkins.war  --httpPort= 8089 --prefix=/sonarqube > /data/jenkins/app/logs/sonarqube/jenkins.log 2>&1 &

set MODULE_NAME=dev
set JAVA_HOME=Z:/Project/Java/jdk1.8.0_241

set JENKINS_BASE=Z:/Project/CI
set JENKINS_HOME=Z:/Project/CI/%MODULE_NAME%

set JENKINS_LOG=%JENKINS_BASE%/logs/%MODULE_NAME%
set JENKINS_HTTP_PORT=8090
set JENKINS_VERSION=2.204.2

set JENKINS_OPTS=--prefix=%JENKINS_PREFIX%
set JENKINS_OPTS=%JENKINS_OPTS% --httpPort=%JENKINS_HTTP_PORT%


set JAVA_OPTS=-server
set JAVA_OPTS=%JAVA_OPTS% -noverify -Dfile.encoding=UTF-8 
set JAVA_OPTS=%JAVA_OPTS% -Dserver=jenkins

rem # ----------------------
rem # jenkins opts
rem # ----------------------

set JAVA_OPTS=%JAVA_OPTS% -DJENKINS_HOME=%JENKINS_HOME%

rem # ----------------------
rem # java memery opts
rem # ----------------------

set JAVA_OPTS=%JAVA_OPTS% -Djava.awt.headless=true
set JAVA_OPTS=%JAVA_OPTS% -Xms2g
set JAVA_OPTS=%JAVA_OPTS% -Xmx6g
set JAVA_OPTS=%JAVA_OPTS% -Xss2m
set JAVA_OPTS=%JAVA_OPTS% -XX:MetaspaceSize=2g
set JAVA_OPTS=%JAVA_OPTS% -XX:MaxMetaspaceSize=6g 

rem # ----------------------
rem # java gc opts
rem # ----------------------

set JAVA_OPTS=%JAVA_OPTS% -verbose:gc
set JAVA_OPTS=%JAVA_OPTS% -Xloggc:%JENKINS_LOG/gc.log
set JAVA_OPTS=%JAVA_OPTS% -XX:+PrintGCDetails
set JAVA_OPTS=%JAVA_OPTS% -XX:+PrintGCTimeStamps
set JAVA_OPTS=%JAVA_OPTS% -XX:+PrintHeapAtGC
set JAVA_OPTS=%JAVA_OPTS% -XX:+HeapDumpOnOutOfMemoryError
set JAVA_OPTS=%JAVA_OPTS% -XX:HeapDumpPath=Z:/

%JAVA_HOME%/bin/java -jar "%JENKINS_BASE%/jenkins-%JENKINS_VERSION%.war" %JENKINS_OPTS%

```
