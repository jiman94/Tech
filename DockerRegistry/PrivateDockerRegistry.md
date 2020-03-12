
mkdir /Users/mz02-jmryu/Downloads/nexus-data && sudo chown -R 200 /Users/mz02-jmryu/Downloads/nexus-data

docker run -d -p 8081:8081 -p 12000:12000 --name nexus -v /Users/mz02-jmryu/Downloads/nexus-data:/nexus-data sonatype/nexus3

http://localhost:8081/


주소 : http://ip:8081
관리자 유저 ID 및 password : admin // admin123



Docker 설정 변경
Docker V1 API 는 Docker Registry V1 을 의미한다. 현재 사용되고 있는 버전은 V2 로 https 만 지원한다. https 로 하려면 추가로 인증서 작업이 필요해서 해당 글에서는 Docker Registry V1 을 사용하면서 Http 로 할 수 있도록 설정을 변경한다.

[Nexus 서버 IP 또는 도메인] 부분을 변경해서 실행한다.

sudo sh -c "echo 'DOCKER_OPTS=\"\$DOCKER_OPTS --insecure-registry=[Nexus 서버 IP 또는 도메인]:12000\"' >> /etc/default/docker"

Docker 재시작
Docker 옵션을 변경하고, 서비스를 재시작하면 해당 옵션이 반영된다. Docker 재시작 시 컨테이너가 종료되서, nexus 를 다시 실행 시켜준다.

sudo service docker restart
docker start nexus

로그인

docker login -u admin -p admin123 [Nexus 서버 IP 또는 도메인]:12000


docker pull hello-world
docker tag hello-world [Nexus 서버 IP 또는 도메인]:12000/hello-world
docker push [Nexus 서버 IP 또는 도메인]:12000/hello-world
docker rmi hello-world [Nexus 서버 IP 또는 도메인]:12000/hello-world
docker pull [Nexus 서버 IP 또는 도메인]:12000/hello-world
docker images


docker pull [Nexus 서버 IP 또는 도메인]:12000/hello-world
docker images
