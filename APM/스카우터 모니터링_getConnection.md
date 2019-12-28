## 스카우터 모니터링 

- JDBC Connection  Open & Close 프로파일에 추가 하는 방법 

```java
-javaagent:D:/Project/APM/scouter/agent.java/scouter.agent.jar
-Dscouter.config=D:/Project/APM/scouter/scouter.conf
```

###  scouter.conf

```java
hook_method_patterns=oss.*.*,javax.net.*.*,org.apache.http.*.*,com.zaxxer.hikari.pool.HikariProxyConnection.*.*
hook_connection_open_patterns=org.springframework.jdbc.datasource.DataSourceUtils.doGetConnection
```


```java
------------------------------------------------------------------------------------------
    p#      #       TIME         T-GAP   CPU          CONTENTS
------------------------------------------------------------------------------
```

