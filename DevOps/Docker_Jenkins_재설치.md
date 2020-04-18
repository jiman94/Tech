
1. Docker 디렉토리 삭제 

/var/lib/docker 아래의 모든 파일, 디렉토리를 삭제한다.
cd /var/lib/docker
rm -rf *
/var/run 아래에서 docker.sock, docker.pid 파일과 docker 디렉토리를 삭제한다.
cd /var/run
rm docker.sock docker.pid
rm -rf docker
여기까지 수행하였다면 Docker 를 완전히 삭제한 것이다.

2. 도커를 재 설치하기

yum install -y docker 

chmod 777 /var/run/docker.sock

3. Jenkins 설치 

docker pull jenkins/jenkins:lts

docker run -d -p 8080:8080 -p 50000:50000 -v /Users/mz02-jmryu/Downloads/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --restart unless-stopped --name pilot-jenkins jenkins/jenkins:lts

Stg
docker build --build-arg JENKINS_VERSION=2.190.2 -t chicor-jenkins .
docker run -d -p 8080:8080 -p 50000:50000  -v /home/ec2-user/apps/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --restart unless-stopped  --name chicor-jenkins chicor-jenkins:latest

Prod 

docker run -d -p 8080:8080 -p 50000:50000  -v /app/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --restart unless-stopped  --name chicor-jenkins chicor-jenkins:latest
