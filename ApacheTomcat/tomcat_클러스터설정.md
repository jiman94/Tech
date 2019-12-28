클러스터
```java
<?xml version='1.0' encoding='utf-8'?>
<Server port="${tomcat.server.port}" shutdown="SHUTDOWN">

  <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="off" />
  <Listener className="org.apache.catalina.core.JasperListener" />
  <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
  <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
  <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />

  <GlobalNamingResources>
    <Resource name="UserDatabase" auth="Container"
              type="org.apache.catalina.UserDatabase"
              description="User database that can be updated and saved"
              factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
              pathname="conf/tomcat-users.xml" />
  </GlobalNamingResources>

  <Service name="Catalina">

    <Executor name="tomcatThreadPool" namePrefix="catalina-exec-" maxThreads="1500" minSpareThreads="100"/>
    <Connector executor="tomcatThreadPool" port="${tomcat.http.port}" protocol="org.apache.coyote.http11.Http11NioProtocol"
               acceptCount="10" enableLookups="false"
               connectionTimeout="10000" maxConnections="10000"
               maxKeepAliveRequests="1"  maxThreads="500" tcpNoDelay="true"
               redirectPort="8443"  URIEncoding="UTF-8"
               compression="on" compressionMinSize="1024"
               compressableMimeType="text/html,text/xml,text/plain,application/javascript,application/json,text/javascript,application/xml" />

    <Engine name="Catalina" defaultHost="localhost" jvmRoute="${tomcat.jvm.route}">

   
       <Cluster className="org.apache.catalina.ha.tcp.SimpleTcpCluster"
                     channelSendOptions="6">

        <Manager className="org.apache.catalina.ha.session.DeltaManager"
                       expireSessionsOnShutdown="false"
                       notifyListenersOnReplication="true"
                       mapSendOptions="6"/>

                  <Channel className="org.apache.catalina.tribes.group.GroupChannel">
                      <Membership className="org.apache.catalina.tribes.membership.McastService"
                                address="228.0.0.5"
                                port="45564"
                                frequency="500"
                                dropTime="3000"/>

                        <Receiver className="org.apache.catalina.tribes.transport.nio.NioReceiver"
                                 address="auto"
                                 port="4001"
                                 selectorTimeout="100"
                                 maxThreads="30"/>


                      <Sender className="org.apache.catalina.tribes.transport.ReplicationTransmitter">
                          <Transport className="org.apache.catalina.tribes.transport.nio.PooledParallelSender"/>
                      </Sender>

                      <Interceptor className="org.apache.catalina.tribes.group.interceptors.TcpFailureDetector"/>
                      <Interceptor className="org.apache.catalina.tribes.group.interceptors.MessageDispatch15Interceptor"/>
                      <Interceptor className="org.apache.catalina.tribes.group.interceptors.ThroughputInterceptor"/>
                  </Channel>

                  <Valve className="org.apache.catalina.ha.tcp.ReplicationValve"
                                  filter=".*\.gif|.*\.js|.*\.jpeg|.*\.jpg|.*\.png|.*\.htm|.*\.html|.*\.css|.*\.txt"/>

                  <Deployer className="org.apache.catalina.ha.deploy.FarmWarDeployer"
                                      tempDir="/tmp/war-temp/"
                                      deployDir="/tmp/war-deploy/"
                                      watchDir="/tmp/war-listen/"
                                      watchEnabled="false"/>

                  <ClusterListener className="org.apache.catalina.ha.session.ClusterSessionListener"/>
            </Cluster>
   
      <Realm className="org.apache.catalina.realm.LockOutRealm">
        <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
               resourceName="UserDatabase" digest="MD5" />
      </Realm>

      <Host name="localhost" appBase="webapps"
            unpackWARs="true" autoDeploy="true">


                  <Context path="/session" distributable="true" docBase="C:/topasIBE/workspace-fwcore/session/WebContent" privileged="true" reloadable="false" >
                     
                 </Context>


      </Host>

    </Engine>
  </Service>
</Server>
```




세션 체크 
```java
http://10.24.1.247:8081/air/session/main.jsp

http://10.24.1.247:8081/air/session/sessionList.jsp

http://10.24.1.247:8081/air/topas/front_session.jsp
```