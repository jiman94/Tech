# env
```bash 
RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV TERM xterm
```

```bash 
export CATALINA_OPTS="-Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -Xms1024m -Xmx2048m -XX:MaxPermSize=512m -XX:MaxPermSize=1024m -Djava.awt.headless=true"
```
