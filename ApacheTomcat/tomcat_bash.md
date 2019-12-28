# Tomcat 설정 
* env.sh

```bash 
#!/bin/sh
# env.sh
export MODULE_CODE="iq3"
export MODULE_NAME="skmair"
export MODULE_ENV="live"
export MODULE_IDX="21"

export JAVA_HOME="/usr/java/jdk1.7.0_67"
export CATALINA_HOME="/app/topasibeskm/apache-tomcat-7"
export CATALINA_BASE="/app/topasibeskm/tomcat7-skmair21"

export TC_SERVER_PORT=28115
export TC_AJP_PORT=28119
export TC_HTTP_PORT=28110
export TC_REDIRECT_PORT=28113

#export PORT_HTTPS= `expr ${PORT_SERVICE} + 363`
#export PORT_AJP= `expr ${PORT_SERVICE} + 71`
#export PORT_SHUTDOWN= `expr ${PORT_SERVICE} + 75`

export TC_JVM_ROUTE="${MODULE_NAME}${MODULE_IDX}"
export TC_USER="topasibeskm"

export TOPAS_SERVER_NAME="${MODULE_CODE}${MODULE_NAME}${MODULE_ENV}${MODULE_IDX}"
export LOG_DIR="/LOG/${TC_USER}/ibelog/${MODULE_NAME}${MODULE_IDX}"

TC_MAX_THREADS=400
TC_ACCEPT_COUNT=200

# ----------------------------------------------------------
# tomcat opts
# ----------------------------------------------------------
JAVA_OPTS="-Dtomcat.server.port=${TC_SERVER_PORT}"
JAVA_OPTS="${JAVA_OPTS} -Dtomcat.ajp.port=${TC_AJP_PORT}"
JAVA_OPTS="${JAVA_OPTS} -Dtomcat.http.port=${TC_HTTP_PORT}"
JAVA_OPTS="${JAVA_OPTS} -Dtomcat.redirect.port=${TC_REDIRECT_PORT}"
JAVA_OPTS="${JAVA_OPTS} -Dtomcat.jvm.route=${TC_JVM_ROUTE}"
export JAVA_OPTS

CATALINA_OPTS="-server"

# ----------------------------------------------------------
# performanc opts
# ----------------------------------------------------------
CATALINA_OPTS="${CATALINA_OPTS} -Dtomcat.max.threads=${TC_MAX_THREADS}"
CATALINA_OPTS="${CATALINA_OPTS} -Dtomcat.accept.count=${TC_ACCEPT_COUNT}"

# ----------------------------------------------------------
# component opts
# ----------------------------------------------------------
CATALINA_OPTS="${CATALINA_OPTS} -Dtopas.server.name=${TOPAS_SERVER_NAME}"
CATALINA_OPTS="${CATALINA_OPTS} -Dtopas_server_name=${TOPAS_SERVER_NAME}"
CATALINA_OPTS="${CATALINA_OPTS} -Dtopas.module.code=${MODULE_CODE}"
CATALINA_OPTS="${CATALINA_OPTS} -Dtopas.module.docbase=/www/topasibeskm/was_skmair21/air"
CATALINA_OPTS="${CATALINA_OPTS} -Dtopas.log.dir=${LOG_DIR}"
CATALINA_OPTS="${CATALINA_OPTS} -Dspring.profiles.active=skm"

# ----------------------------------------------------------
# jvm memory opts
# ----------------------------------------------------------
CATALINA_OPTS="${CATALINA_OPTS} -Xms2048m -Xmx2048m -Djennifer.config=/app/jennifer5/agent.java/conf/tomcat7-skmair21.conf -javaagent:/app/jennifer5/agent.java/jennifer.jar "
CATALINA_OPTS="${CATALINA_OPTS} -XX:MaxNewSize=512m -XX:MaxPermSize=512m"

# ----------------------------------------------------------
# jmx opts
# ----------------------------------------------------------
#CATALINA_OPTS="${CATALINA_OPTS} -Dcom.sun.management.jmxremote"
#CATALINA_OPTS="${CATALINA_OPTS} -Dcom.sun.management.jmxremote.port=12516"
#CATALINA_OPTS="${CATALINA_OPTS} -Dcom.sun.management.jmxremote.ssl=false"
#CATALINA_OPTS="${CATALINA_OPTS} -Dcom.sun.management.jmxremote.authenticate=false"

#--------------------------------------
# java gc opts
#--------------------------------------
CATALINA_OPTS="${CATALINA_OPTS} -XX:+DoEscapeAnalysis"
CATALINA_OPTS="${CATALINA_OPTS} -XX:+UseCompressedOops"
CATALINA_OPTS="${CATALINA_OPTS} -XX:+UseParallelGC"
CATALINA_OPTS="${CATALINA_OPTS} -XX:+UseParallelOldGC"
CATALINA_OPTS="${CATALINA_OPTS} -verbose:jni"
CATALINA_OPTS="${CATALINA_OPTS} -verbose:gc"
CATALINA_OPTS="${CATALINA_OPTS} -Xloggc:${LOG_DIR}/gc.log.`date +%Y%m%d%H%M%S`"
CATALINA_OPTS="${CATALINA_OPTS} -XX:+PrintGCDetails"
CATALINA_OPTS="${CATALINA_OPTS} -XX:+PrintGCTimeStamps"
CATALINA_OPTS="${CATALINA_OPTS} -XX:+PrintHeapAtGC"
CATALINA_OPTS="${CATALINA_OPTS} -XX:+HeapDumpOnOutOfMemoryError"
CATALINA_OPTS="${CATALINA_OPTS} -XX:HeapDumpPath=${CATALINA_BASE}"

CATALINA_OPTS="${CATALINA_OPTS} -Djava.net.preferIPv4Stack=true"
CATALINA_OPTS="${CATALINA_OPTS} -Dsun.rmi.dgc.client.gcInterval=3600000"
CATALINA_OPTS="${CATALINA_OPTS} -Dsun.rmi.dgc.server.gcInterval=3600000"
CATALINA_OPTS="${CATALINA_OPTS} -Dsun.lang.ClassLoader.allowArraySyntax=true"
CATALINA_OPTS="${CATALINA_OPTS} -Djava.awt.headless=true"

#--------------------------------------
# scouter opts
#--------------------------------------
#SCOUTER_AGENT_DIR="/app/topasibe/scouter/agent.java"
#CATALINA_OPTS=" ${CATALINA_OPTS} -javaagent:${SCOUTER_AGENT_DIR}/scouter.agent.jar"
#CATALINA_OPTS=" ${CATALINA_OPTS} -Dscouter.config=${CATALINA_BASE}/conf/scouter.conf"

export CATALINA_OPTS

echo "========================================================================"
echo "JAVA_HOME=${JAVA_HOME}"
echo "CATALINA_HOME=${CATALINA_HOME}"
echo "CATALINA_BASE=${CATALINA_BASE}"
echo "JAVA_OPTS=${JAVA_OPTS}"
echo "CATALINA_OPTS=${CATALINA_OPTS}"
echo "========================================================================"


```

* startup.sh
```bash 
#!/bin/sh
# startup.sh

. ./env.sh

#
# check user for can't run as root
#
UNM=`id -u -n`
if [ "x${UNM}" != "x${TC_USER}" ]; then
  echo "You can't start ${TOPAS_SERVER_NAME} server..."
  exit;
fi

#
# check previous process was alive
# 
PID=`ps -ef | grep java | grep "topas.server.name=${TOPAS_SERVER_NAME}" | awk '{print $2}'`
echo "#"
echo "# tomcat7-${TOPAS_SERVER_NAME} process id : $PID"
echo "#"
if [ "x$PID" != "x" ]; then
    echo "Tomcat 7 ${TOPAS_SERVER_NAME} is already Running..."
    exit;
fi

# ${CATALINA_HOME}/bin/catalina.sh run
${CATALINA_HOME}/bin/startup.sh


```
* shutdown.sh
```bash 
#!/bin/sh
# shutdown.sh

. ./env.sh

${CATALINA_HOME}/bin/shutdown.sh


```
* status.sh
```bash 
#!/bin/sh
# status.sh

. ./env.sh

ps -ef | grep java | grep "${TOPAS_SERVER_NAME} "


```
* tail.sh
```bash 
#!/bin/sh
# tail.sh

. ./env.sh

tail -f ${CATALINA_BASE}/logs/catalina.out


```
* kill.sh
```bash 
#!/bin/sh
# kill.sh

. ./env.sh

ps -ef | grep java | grep "server.name=${TOPAS_SERVER_NAME} " | awk {'print "kill -9 " $2'} | sh -x


```
* version.sh
```bash 
#!/bin/sh
# version.sh

. ./env.sh

$CATALINA_HOME/bin/version.sh

```
* tdump.sh
```bash 
#!/bin/bash
# tdump.sh - thread dump

. ./env.sh

count=3
delay=10
while [ $count -gt 0 ]; do
    ps -ef | grep java | grep "server.name=${TOPAS_SERVER_NAME} " | awk {'print "${JAVA_HOME}/bin/jstack "$2'} | sh -x >> ${TOPAS_SERVER_NAME}-$(date +"%Y%m%d%H%M%S" ).tdump
    sleep $delay
    let count--
    echo -n "."
done


```
* mdump.sh
```bash 
#!/bin/sh
# mdump.sh - heap memory dump

. ./env.sh

ps -ef | grep java | grep "server.name=${TOPAS_SERVER_NAME} " | awk {'print "${JAVA_HOME}/bin/jmap -dump:format=b,file=${TOPAS_SERVER_NAME}-$(date +"%Y%m%d%H%M%S" ).hprof "$2'} | sh -x


```
