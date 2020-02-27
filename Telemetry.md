# Telemetry 

docker run -d -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name prometheus prom/prometheus:latest
docker exec -it prometheus /bin/sh


docker run -d --name=grafana -p 3000:3000 --name grafana grafana/grafana:latest
docker exec -it grafana /bin/bash

docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:latest
docker exec -it zipkin /bin/sh

