# Telemetry 

docker run -d -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name prometheus prom/prometheus:latest

docker exec -it prometheus /bin/sh


docker run -d --name=grafana -p 3000:3000 --name grafana grafana/grafana:latest

docker exec -it grafana /bin/bash

docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:latest

docker exec -it zipkin /bin/sh

---
```bash 
D:\>docker run -d -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name prometheus prom/prometheus:latest
Unable to find image 'prom/prometheus:latest' locally
latest: Pulling from prom/prometheus


D:\>docker run -d --name=grafana -p 3000:3000 --name grafana grafana/grafana:latest
Unable to find image 'grafana/grafana:latest' locally
latest: Pulling from grafana/grafana

D:\>docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:latest
Unable to find image 'openzipkin/zipkin:latest' locally
latest: Pulling from openzipkin/zipkin
D:\>docker ps -a
CONTAINER ID        IMAGE                      COMMAND                  CREATED              STATUS                          PORTS                              NAMES

59b76f7d665c        openzipkin/zipkin:latest   "/busybox/sh run.sh"     8 seconds ago        Up 7 seconds                    9410/tcp, 0.0.0.0:9411->9411/tcp   zipkin

4ad85b5a8c5b        grafana/grafana:latest     "/run.sh"                24 seconds ago       Up 23 seconds                   0.0.0.0:3000->3000/tcp             grafana

69fc533d15ea        prom/prometheus:latest     "/bin/prometheus --c…"   About a minute ago   Exited (1) About a minute ago                                      prometheus

D:\>
```

