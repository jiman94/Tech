docker run -d --name=grafana -p 3000:3000 --name pilot-grafana grafana/grafana:latest

docker run -d -p 9000:9000 --name pilot-sonarqube pilot-sonarqube:latest

docker run -d -p 8080:8080 -p 50000:50000  -v /home/ec2-user/apps/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --restart unless-stopped  --name pilot-jenkins pilot-jenkins:latest

docker run -d -p 9000:9000 –name pilot-sonarqube pilot-sonarqube:latest

docker run -it -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name pilot-prometheus prom/prometheus:latest

docker run -p 24224:24224 -v /home/ec2-user/apps/pilot_efk/config:/fluentd/etc --name log-fluentd 75_fluentd:latest

docker run -d --net="host" --pid="host" -v "/:/host:ro,rslave" --name pilot-node-exporter quay.io/prometheus/node-exporter:latest   --path.rootfs=/host

docker run --volume=/:/rootfs:ro --volume=/var/run:/var/run:ro --volume=/sys:/sys:ro --volume=/var/lib/docker/:/var/lib/docker:ro   --volume=/dev/disk/:/dev/disk:ro   --publish=8082:8080   --detach=true   --name=chicor-cadvisor   gcr.io/google-containers/cadvisor:latest

---

docker run --volume=/:/rootfs:ro --volume=/var/run:/var/run:ro --volume=/sys:/sys:ro --volume=/var/lib/docker/:/var/lib/docker:ro   --volume=/dev/disk/:/dev/disk:ro   --publish=8082:8082   --detach=true   --name=pilot-cadvisor   gcr.io/google-containers/cadvisor:latest

docker run -d --net="host" --pid="host" -v "/:/host:ro,rslave"   quay.io/prometheus/node-exporter --path.rootfs=/host --name chicor-node-exporter

docker run -p 9100:9100 -v "/proc:/host/proc" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" prom/node-exporter --path.procfs /host/proc --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)"

docker run -p 9100:9100 -v "/proc:/host/proc" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" pilot-node-exporter --path.procfs /host/proc --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)"

docker run -p 9100:9100 -v "/:/host:ro,rslave" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" --path.procfs /host/proc --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)" --name pilot-node-exporter 

docker run -p 9100:9100 -v "/:/host:ro,rslave" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)" --name pilot-node-exporter 

docker run -p 9100:9100 -v "/:/host:ro,rslave" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" --path.rootfs=/host quay.io/prometheus/node-exporter --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)" --name pilot-node-exporter 

docker run -d --net="host" --pid="host" -v "/:/host:ro,rslave" quay.io/prometheus/node-exporter --path.rootfs=/host --name pilot-node-exporter

---

sudo yum update -y
sudo amazon-linux-extras install docker
docker info
sudo usermod -a -G docker $USER
sudo systemctl start docker
sudo systemctl enable docker


sudo yum update -y
sudo amazon-linux-extras install docker
sudo service docker start
sudo usermod -a -G docker ec2-user
docker info
sudo yum update -y
sudo yum install git

sudo su -
sudo yum update
java 
grep . /etc/*-release
yum install git
sudo yum install git 

ifconfig 

sudo yum update -y
fdisk
df -h
vgdisplay
disk -l
fdisk -l


git config --global user.name "유지만"
git config --global user.email "ryu.jiman@gmail.com"
git clone http://pilot.amazonaws.com:7000/scm/pilot/pilot_security.git

git status
git add .
git commit
git push origin



docker build -t pilot_config_server .
docker run --name pilot_config_server -p 8000:8000 -d pilot_config_server


kubectl describe po kafka

kubectl get po -w

---

FROM openjdk:8-jdk-stretch

RUN apt-get update && apt-get upgrade -y && apt-get install -y vim

ENV profile stg
ENV configServer 'http://pilot.elb.amazonaws.com:8000'
ENV port 8099

EXPOSE $port

CMD java -Dspring.profiles.active=$profile \
         -Dspring.cloud.config.uri=$configServer \
         -Dserver.port=$port \
    -jar pilot_dev_aws_cdn.jar

COPY ./chicor_dev_aws_cdn.jar .

FROM openjdk:8-jdk-stretch 

RUN apt-get update && apt-get upgrade -y && apt-get install -y vim

ENV profile stg 
ENV configServer 'http://pilot.elb.amazonaws.com:8000'
ENV port 8082

EXPOSE $port

CMD java -Dspring.profiles.active=$profile \
         -Dspring.cloud.config.uri=$configServer \
         -Dserver.port=$port \
    -jar pilot_po_web.jar

COPY ./pilot_po_web.jar .
