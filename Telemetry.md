

docker run -d -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name chicor-prometheus prom/prometheus:latest

docker exec -it chicor-prometheus /bin/sh


docker run -d --name=grafana -p 3000:3000 --name chicor-grafana grafana/grafana:latest
docker exec -it chicor-grafana /bin/bash


docker run -d -p 9411:9411 --name chicor-zipkin openzipkin/zipkin:latest
docker exec -it chicor-zipkin /bin/sh

