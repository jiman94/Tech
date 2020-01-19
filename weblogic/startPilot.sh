#!/bin/sh
 USER_NAME=weblogic
DOMAIN_HOME=/data/weblogic1036/domains/Pilot_domain
SERVER_NAME=Pilot01
SERVER_PORT=7001
DOMAIN_NAME=Pilot_domain
LOG_DIR=//logs/was_log/${SERVER_NAME}/weblogic
ADMIN_URL=t3://1.1.1.4:7000
USER_MEM_ARGS="-Xms1536m -Xmx1536m -XX:PermSize=768m -XX:MaxPermSize=768m -XX:NewRatio=3 -XX:SurvivorRatio=8 -XX:+UseParallelOldGC -XX:+UseCompressedOops -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapDump/${SERVER_NAME}.`date +%y%m%d_%H%M`"
USER_MEM_ARGS="${USER_MEM_ARGS} -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:/logs/pilot01/weblogic/pilot01.gc"
USER_MEM_ARGS="-D${SERVER_NAME} -D${SERVER_PORT} ${USER_MEM_ARGS}"
USER_MEM_ARGS="${USER_MEM_ARGS} -Dinstance.name=pilotlive01 -Dtopas.log.dir=/logs/${SERVER_NAME} "
USER_MEM_ARGS="${USER_MEM_ARGS} -Dspring.profiles.active=skm "
export USER_MEM_ARGS

# Check User Name
IAM=`id | awk '{print substr($1, 1, index($1,")")-1 )}' | awk '{print substr($1, index($1,"(")+1 )}'`

# Check startup user validation
if [ $USER_NAME != $IAM ]
then
echo "Startup Error : User validation is failed. This instance has been started as \"$IAM\", actual script owner is \"$USER_NAME\""
exit
fi
 
# Check process status
PID=`ps -ef|grep java|grep D${SERVER_NAME}| grep D${SERVER_PORT} | awk '{print $2}'`

if [ "$PID" != "" ]
then
echo "Startup Error : \"${SERVER_NAME}\" process is already running !!!"
exit
fi

echo "============================================================================="
echo " WL_HOME=${WL_HOME}"
echo " JAVA_VM=${JAVA_VM}"
echo " JAVA_HOME=${JAVA_HOME}"
echo " SERVER_NAME=${SERVER_NAME}"
echo "-----------------------------------------------------------------------------"
echo " USER_MEM_ARGS=${USER_MEM_ARGS}"
echo " JAVA_OPTIONS=${JAVA_OPTIONS}"
echo "============================================================================="
 
mv ${LOG_DIR}/${SERVER_NAME}.out ${LOG_DIR}/${SERVER_NAME}.out.`date +%y%m%d_%H%M`

nohup ./bin/startManagedWebLogic.sh ${SERVER_NAME} ${ADMIN_URL} > ${LOG_DIR}/${SERVER_NAME}.out &
sleep 2
echo tail -f ${LOG_DIR}/${SERVER_NAME}.out

