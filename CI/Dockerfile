FROM openjdk:8-jdk-stretch

RUN apt-get update && apt-get upgrade -y && apt-get install -y vim

USER root
LABEL maintainer="oss"

ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENV profile stg
ENV configServer 'http://config.stg.pilot'
ENV port 80

EXPOSE $port

ADD ./newrelic/newrelic.yml /usr/local/tomcat/newrelic/newrelic.yml
RUN mkdir -p /usr/local/tomcat/newrelic
ADD ./newrelic/newrelic.jar /usr/local/tomcat/newrelic/newrelic.jar
ENV JAVA_OPTS="$JAVA_OPTS -javaagent:/usr/local/tomcat/newrelic/newrelic.jar"

CMD java -Dspring.profiles.active=$profile \
         -Dspring.cloud.config.uri=$configServer \
         -Dserver.port=$port \
    -jar pilot_dev_mobile.jar

COPY ./pilot_dev_mobile.jar .
