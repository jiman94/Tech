# 톰캣(Tomcat) 서버의 서블릿(Servlet) 파일 경로

```java 
jsp
tomcat
 |-- bin
 |-- conf
 |-- lib
 |-- logs
 |-- temp
 |-- webapps
 `-- work
      `-- [Catalina]
           `-- [localhost]
                `-- _ (vof)
                     `-- org
                          `-- apache
                               `-- jsp
                                    |-- [파일이름]_jsp.class   <--- 요놈
                                    `-- [파일이름]_jsp.java    <--- 이놈
```           


```bash
find /app/topasibe/tomcat6-air01/work/Catalina/localhost -name "*_jsp.java" -exec rm -rf {} \;


find /app/topasibe/tomcat6-air01/work/Catalina/localhost -name "*_jsp.class" -exec rm -rf {} \;
```
