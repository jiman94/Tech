Tomcat은 크게 엔진과 인스턴스 부분으로 나뉜다.

CATALINA_BASE : 구동되어야 할 각 서버의 톰캣 인스턴스 경로
TOMCAT_HOME : 톰캣 엔진 경로


전체적인 톰캣 구성도

/app/pilot/apps
               ./tomcat
                    ./bin
                    ./lib
               ./tomcat-olive
                    ./conf/server.xml
                    ./logs
                    ./temp
                    ./webapps
                    ./work
               ./tomcat-supernova
                    ./conf/server.xml
                    ./logs
                    ./temp
                    ./webapps
                    ./work


추가로, 멀티 인스턴스들의 로그를 한 곳에서 모아서 관리하고 싶다면 해당 모아둔 디렉토리를 만들고 logs를 디렉토리가 아닌 심볼릭 링크로 만들어 주면 된다.

$ mkdir ~/logs/tomcat-pilot
$ rm -rf logs/
$ ln -s ~/logs/tomcat-pilot logs

logs -> /app/pilot/logs/tomcat-pilot

각 인스턴스 별 startup.sh, shutdown.sh 스크립트 생성하기

#!/bin/sh
# 멀티 인스턴스 중 하나인 tomcat-pilot의 startup.sh

export CATALINA_BASE=/app/pilot/apps/tomcat-pilot의 # tomcat instance 경로
export TOMCAT_HOME=/app/pilot/apps/tomcat # tomcat engine 경로
export JAVA_HOME=/app/pilot/apps/jdk # 각 인스턴스마다 사용할 jdk가 다를 경우
export JAVA_OPTS="-Dspring.profiles.active=real" # 구동할 서버에 java option을 주고 싶다면...

cd $TOMCAT_HOME/bin
./startup.sh


> D:\WORKS\startup1.bat

@echo off
set "CATALINA_HOME=D:\Tomcat7"
set "CATALINA_BASE=D:\WORKS\instance1"
set "JAVA_OPTS=-javaagent:에이전트 jar 경로 -Djava.net.preferIPv4Stack=true"
set "CATALINA_OPTS=-Denv=product -Denv.servername=instance1"
call %CATALINA_HOME%\bin\catalina.bat start %CATALINA_OPTS%

> D:\WORKS\shutdown1.bat

@echo off
set "CATALINA_HOME=D:\Tomcat7"
set "CATALINA_BASE=D:\WORKS\instance1"
call %CATALINA_HOME%\bin\shutdown.bat

<Context path="/" docBase="/app/deploy/pilot/doc_base" reloadable="false"/> \
<Context path="/pilot" debug="0" privileged="true" docBase="pilot.war" />




sessionCookieName="ADMIN_JSESSIONID"

추가적으로 톰켓은 JSESSIONID 쿠키를 풀 도메인으로 셋팅한다. 
즉, 서브 도메인이 서로 다르면 JSESSIONID 값도 달라진다는 것이다.

a.test.com <-- JSESSIONID : 11114B1E761B88562A923438B1DA2F17
b.test.com <-- JSESSIONID : 22224B1E761B88562A923438B1DA2F17
c.test.com <-- JSESSIONID : 33334B1E761B88562A923438B1DA2F17



# 여러 도메인의 세션을 공유하기 원한다면 server.xml 

<Context sessionCookieDomain=”.test.com”>



