
docker build -t pilot_dev_config_server .
docker run --name pilot_dev_config_server -p 8888:8888 -d pilot_dev_config_server


#! /bin/bash
docker run --name pilot-mobile -p 8011:80 -v /home/ec2-user/pilot/publishing/mf:/usr/share/nginx/html -d nginx
docker run --name pilot-web -p 8012:80 -v /home/ec2-user/pilot/publishing/df:/usr/share/nginx/html -d nginx
docker run --name pilot-partner -p 8014:80 -v /home/ec2-user/pilot/publishing/partner:/usr/share/nginx/html -d nginx
docker run --name pilot-back -p 8013:80 -v /home/ec2-user/pilot/publishing/back:/usr/share/nginx/html -d nginx
docker run --name pilot-mockup -p 8010:80 -v /home/ec2-user/pilot/publishing/mockup:/usr/share/nginx/html -d nginx

docker run --restart=always -d <container>

docker ps -a
docker start c9bf4428a487

docker run -d --name=grafana -p 3000:3000 --name pilot-grafana grafana/grafana:latest

docker run -d -p 9000:9000 --name pilot-sonarqube pilot-sonarqube:latest

docker run -d -p 8080:8080 -p 50000:50000  -v /home/ec2-user/apps/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --restart unless-stopped  --name pilot-jenkins pilot-jenkins:latest

docker run -it -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name pilo-prometheus prom/prometheus:latest

docker run -p 24224:24224 -v /home/ec2-user/apps/pilot_dev_efk/config:/fluentd/etc --name log-fluentd 75_fluentd:latest

docker run -d --net="host" --pid="host" -v "/:/host:ro,rslave" --name pilot-node-exporter quay.io/prometheus/node-exporter:latest   --path.rootfs=/host

docker run   --volume=/:/rootfs:ro   --volume=/var/run:/var/run:ro   --volume=/sys:/sys:ro   --volume=/var/lib/docker/:/var/lib/docker:ro   --volume=/dev/disk/:/dev/disk:ro   --publish=8082:8080   --detach=true   --name=pilot-cadvisor   gcr.io/google-containers/cadvisor:latest

docker run -d   --net="host"   --pid="host"   -v "/:/host:ro,rslave"   quay.io/prometheus/node-exporter   --path.rootfs=/host --name pilot-node-exporter


docker run -p 9100:9100 -v "/proc:/host/proc" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" prom/node-exporter --path.procfs /host/proc --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)"

docker run -p 9100:9100 -v "/proc:/host/proc" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" pilot-node-exporter --path.procfs /host/proc --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)"


docker run -p 9100:9100 -v "/:/host:ro,rslave" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" --path.procfs /host/proc --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)" --name pilot-node-exporter 

docker run -p 9100:9100 -v "/:/host:ro,rslave" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)" --name pilot-node-exporter 

docker run -p 9100:9100 -v "/:/host:ro,rslave" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" --path.rootfs=/host quay.io/prometheus/node-exporter --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)" --name pilot-node-exporter 

docker run -d   --net="host"   --pid="host"   -v "/:/host:ro,rslave"   quay.io/prometheus/node-exporter   --path.rootfs=/host   --name pilot-node-exporter
