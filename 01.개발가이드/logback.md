# logback 

```xml 
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds">

    <!-- 변수 지정 -->
    <springProperty name="LOG_DIR" source="errorlog.file.path" defaultValue="src/main/resources" />
    <springProperty name="LOG_FILE_NAME" source="errorlog.file.name" defaultValue="chicor.batch" />
    <property name="LOG_PATH_NAME" value="${LOG_DIR}/${LOG_FILE_NAME}" />
    <springProperty name="JOB_NAME" source="job.name" defaultValue="UNDEFINED" />
    <if condition='"${JOB_NAME}".equals("BT_SC_JOB_041")'>
        <then>
            <property name="BATCH_NAME" value="PROMOTION" />
        </then>
        <else>
            <springProperty name="BATCH_NAME" source="batch.name" defaultValue="UNDEFINED" />
        </else>
    </if>
    <springProperty name="MON_JOB_NAME" source="errorlog.monitor.jobs" defaultValue="UNDEFINED" />

    <!-- FILE Append 로깅 설정 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <if condition='"${MON_JOB_NAME}".contains("${JOB_NAME}")'>
            <then>
                <filter class="ch.qos.logback.classic.filter.LevelFilter">
                    <level>error</level>
                    <onMatch>ACCEPT</onMatch>
                    <onMismatch>DENY</onMismatch>
                </filter>
                <!-- 일자별로 로그파일 적용하기 -->
                <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                    <fileNamePattern>${LOG_PATH_NAME}.%d{yyyyMMdd}.log</fileNamePattern>
                    <maxHistory>30</maxHistory> <!-- 일자별 백업파일의 보관기간 -->
                    <!-- 추후 일자별로 로그파일 파일 사이즈 로테이션 필요시 적용 -->
                    <!--            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">-->
                    <!--                <maxFileSize>100MB</maxFileSize>-->
                    <!--            </timeBasedFileNamingAndTriggeringPolicy>-->
                </rollingPolicy>
                <encoder>
                    <charset>UTF-8</charset>
                    <pattern>%d{yyyyMMddHHmmss} : ${BATCH_NAME} : ${JOB_NAME} : %-5p : %m%n</pattern>
                </encoder>
            </then>
        </if>
    </appender>

    <!-- 콘솔 출력 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-dd-MM HH:mm:ss.SSS} %highlight(%-5level) %magenta([%thread]) %-30.-30F : %msg%n</pattern>
        </layout>
    </appender>

    <!-- 실행 프로파일별 분기 -->
    <springProfile name="dev">
        <logger name="com.chicor.batch" level="DEBUG" additivity="false">
            <appender-ref ref="FILE" />
            <appender-ref ref="STDOUT" />
        </logger>
    </springProfile>

    <springProfile name="stg">
        <logger name="com.chicor.batch" level="DEBUG" additivity="false">
            <appender-ref ref="FILE" />
            <appender-ref ref="STDOUT" />
        </logger>
    </springProfile>

    <springProfile name="prod">
        <logger name="com.chicor.batch" level="INFO" additivity="false">
            <appender-ref ref="FILE" />
            <appender-ref ref="STDOUT" />
        </logger>
    </springProfile>

    <logger name="org.springframework" level="INFO" additivity="false">
        <appender-ref ref="FILE" />
        <appender-ref ref="STDOUT" />
    </logger>
    
    <logger name="com.zaxxer.hikari" level="INFO" additivity="false">
        <appender-ref ref="FILE" />
        <appender-ref ref="STDOUT" />
    </logger>
</configuration>
```