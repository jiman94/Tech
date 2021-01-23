WSO API GW 구축 메뉴얼
KIM JONG IL | 20 9월 2016
JDK 1.8 설치

$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer

$ sudo apt-get install oracle-java8-set-default

WSO2AM API Manager 설치
sudo mkdir /service
sudo chown ubuntu:ubuntu -R /service
unzip wso2am-2.0.0.zip
mv wso2am-2.0.0 APIGW
cd APIGW/bin
sh wso2server.sh -Dsetup      : 처음 시작할 때
sh wso2server.sh --start      : 추후 시작할 때
sh wso2server.sh --stop       : 서비스 종료시

WSO2AM Analytics 설치
sudo mkdir /service
sudo chown ubuntu:ubuntu -R /service
unzip wso2am-analytics-2.0.0.zip
mv wso2am-analytics-2.0.0 DAS
cd DAS/bin
sh wso2server.sh -Dsetup      : 처음 시작할 때
sh wso2server.sh --start      : 추후 시작할 때
sh wso2server.sh --stop       : 서비스 종료시

Analytics 설정
API MANAGER 설정
/service/APIGW/repository/conf/api-manager.xml

테그 true로 설정
tcp://DAS서버IP:PORT로 설정 기본 7612포트
tcp://DASt서버IP:PORT로 설정 기본 9444포트
    <Analytics>
        <!-- Enable Analytics for API Manager -->
        <Enabled>true</Enabled>

        <!-- Server URL of the remote DAS/CEP server used to collect statistics. Must
             be specified in protocol://hostname:port/ format.

             An event can also be published to multiple Receiver Groups each having 1 or more receivers. Receiver
             Groups are delimited by curly braces whereas receivers are delimited by commas.
             Ex - Multiple Receivers within a single group
             tcp://localhost:7612/,tcp://localhost:7613/,tcp://localhost:7614/

             Ex - Multiple Receiver Groups with two receivers each
             {tcp://localhost:7612/,tcp://localhost:7613},{tcp://localhost:7712/,tcp://localhost:7713/} -->
        <DASServerURL>{tcp://DAS IP:DAS PORT}</DASServerURL>
        <!--DASAuthServerURL>{ssl://localhost:7712}</DASAuthServerURL-->
        <!-- Administrator username to login to the remote DAS server. -->
        <DASUsername>${admin.username}</DASUsername>
        <!-- Administrator password to login to the remote DAS server. -->
        <DASPassword>${admin.password}</DASPassword>

        <!-- For APIM implemented Statistic client for RDBMS -->
        <StatsProviderImpl>org.wso2.carbon.apimgt.usage.client.impl.APIUsageStatisticsRdbmsClientImpl</StatsProviderImpl>

        <!-- DAS REST API configuration -->
        <DASRestApiURL>https://DAS IP:PORT</DASRestApiURL>
        <DASRestApiUsername>${admin.username}</DASRestApiUsername>
        <DASRestApiPassword>${admin.password}</DASRestApiPassword>

        <!-- Below property is used to skip trying to connect to event receiver nodes when publishing events even if
            the stats enabled flag is set to true. -->
        <SkipEventReceiverConnection>false</SkipEventReceiverConnection>

        <!-- API Usage Data Publisher. -->
        <PublisherClass>org.wso2.carbon.apimgt.usage.publisher.APIMgtUsageDataBridgeDataPublisher</PublisherClass>

        <!-- If below property set to true,then the response message size will be calculated and publish
             with each successful API invocation event. -->
        <PublishResponseMessageSize>false</PublishResponseMessageSize>
        <!-- Data publishing stream names and versions of API requests, responses and faults. If the default values
            are changed, the toolbox also needs to be changed accordingly. -->
        <Streams>
            <Request>
                <Name>org.wso2.apimgt.statistics.request</Name>
                <Version>1.1.0</Version>
            </Request>
            <Response>
                <Name>org.wso2.apimgt.statistics.response</Name>
                <Version>1.1.0</Version>
            </Response>
            <Fault>
                <Name>org.wso2.apimgt.statistics.fault</Name>
                <Version>1.0.0</Version>
            </Fault>
            <Throttle>
                <Name>org.wso2.apimgt.statistics.throttle</Name>
                <Version>1.0.0</Version>
            </Throttle>
            <Workflow>
                <Name>org.wso2.apimgt.statistics.workflow</Name>
                <Version>1.0.0</Version>
            </Workflow>
            <ExecutionTime>
                <Name>org.wso2.apimgt.statistics.execution.time</Name>
                <Version>1.0.0</Version>
            </ExecutionTime>
            <AlertTypes>
                <Name>org.wso2.analytics.apim.alertStakeholderInfo</Name>
                <Version>1.0.0</Version>
            </AlertTypes>
        </Streams>

    </Analytics>

/service/APIGW/repository/conf/log4j.properties

rootLogger DAS_AGENT 추가
log4j.appender.DAS_AGENT.url DAS서버 설정 tcp://DAS IP:PORT 기본 PORT 7612
log4j.rootLogger=ERROR, CARBON_CONSOLE, CARBON_LOGFILE, CARBON_MEMORY, CARBON_SYS_LOG, ERROR_LOGFILE, DAS_AGENT
# DAS_AGENT is set to be a Custom Log Appender.
log4j.appender.DAS_AGENT=org.wso2.carbon.analytics.shared.data.agents.log4j.appender.LogEventAppender
# DAS_AGENT uses PatternLayout.
log4j.appender.DAS_AGENT.layout=org.wso2.carbon.analytics.shared.data.agents.log4j.util.TenantAwarePatternLayout
log4j.appender.DAS_AGENT.columnList=%D,%S,%A,%d,%c,%p,%m,%H,%I,%Stacktrace
log4j.appender.DAS_AGENT.userName=admin
log4j.appender.DAS_AGENT.password=admin
log4j.appender.DAS_AGENT.url=tcp://DAS IP:PORT
log4j.appender.DAS_AGENT.maxTolerableConsecutiveFailure=5
log4j.appender.DAS_AGENT.streamDef=loganalyzer:1.0.0
log4j.logger.trace.messages=TRACE,CARBON_TRACE_LOGFILE

/service/APIGW/repository/conf/datasources/master-datasources.xml

API MANAGER와 Analytics 분리시 공통 DB설정
WSO2AM_STATS_DB 인스턴스는 생성해 주어야 함.
         <datasource>
            <name>WSO2AM_STATS_DB</name>
            <description>The datasource used for getting statistics to API Manager</description>
            <jndiConfig>
                <name>jdbc/WSO2AM_STATS_DB</name>
            </jndiConfig>
            <definition type="RDBMS">
                <configuration>
                    <url>jdbc:mysql://DB IP:PORT/WSO2AM_STATS_DB?autoReconnect=true&amp;relaxAutoCommit=true</url>
                    <username>root</username>
                    <password>password</password>
                    <driverClassName>com.mysql.jdbc.Driver</driverClassName>
                    <maxActive>50</maxActive>
                    <maxWait>60000</maxWait>
                    <testOnBorrow>true</testOnBorrow>
                    <validationQuery>SELECT 1</validationQuery>
                    <validationInterval>30000</validationInterval>
                    <defaultAutoCommit>false</defaultAutoCommit>
                </configuration>
            </definition>
         </datasource>

Analytics 설정
/service/DAS/repository/conf/datasources/stats-datasources.xml

<datasource>
          <name>WSO2AM_STATS_DB</name>
          <description>The datasource used for setting statistics to API Manager</description>
          <jndiConfig>
            <name>jdbc/WSO2AM_STATS_DB</name>
            </jndiConfig>
          <definition type="RDBMS">
                <configuration>
                    <url>jdbc:mysql://DB IP:PORT/WSO2AM_STATS_DB?autoReconnect=true&amp;relaxAutoCommit=true</url>
                    <username>root</username>
                    <password>password</password>
                    <driverClassName>com.mysql.jdbc.Driver</driverClassName>
                    <maxActive>50</maxActive>
                    <maxWait>60000</maxWait>
                    <testOnBorrow>true</testOnBorrow>
                    <validationQuery>SELECT 1</validationQuery>
                    <validationInterval>30000</validationInterval>
                    <defaultAutoCommit>false</defaultAutoCommit>
                </configuration>
            </definition>
        </datasource>

JDBC 드라이버 추가
각 DB에 맞는 드라이버를 /service/DAS/repository/components/lib, /service/APIGW/repository/components/lib에 추가함
서비스 재시작
DAS 서버 재시작 완료 후 APIGW 서비스 재시작