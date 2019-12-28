### 가이드 
> Tomcat :: server.xml , web.xml 

----------------------------------------------------

## [개발가이드] 

> servlet 2.5 

> web.xml 

```xml 

<Host name="pilot"  appBase="D:\TopasDesignTmp\pilot\"
        unpackWARs="true" autoDeploy="true"
        xmlValidation="false" xmlNamespaceAware="false">
<Context path="/pilot" 
     docBase="D:/Project/workspace-pilot/pilot/" 
	 reloadable="false" >
</Context>
</Host>
```

```xml 
<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
   version="2.5">

 <servlet>
  <servlet-name>jsp</servlet-name>
  <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>
 </servlet>

 <servlet-mapping>
  <servlet-name>jsp</servlet-name>
	<url-pattern>*.html</url-pattern>
 </servlet-mapping>

</web-app>
```

