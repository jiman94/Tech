#### 1. docker ps 실행 시 아래 오류 발생하면,

unix:///var/run/docker.sock. Is the docker daemon running?

위와 같은 명령어가 뜨면 docker service가 실행이 안되어 있는 것이다.

```bash 
$sudo systemctl status docker
$sudo systemctl start docker
$sudo systemctl enable docker
```

#### SonarSource
https://github.com/SonarSource/docker-sonarqube


docker-sonarqube/8/community/Dockerfile


#### Dockerfile 빌드 

docker build -t chicor-sonarqube .

#### Dockerfile 실행 

docker run -d -p 9000:9000 --name pilot-sonarqube pilot-sonarqube:latest

#### inspect 실행 

docker inspect pilot-sonarqube 

#### exec -it 실행 

docker exec -it  pilot-sonarqube  /bin/bash



# Docker 사용 가이드 

#### 시스템 리소스 사용 상태 보기

```bash 
docker container stats
```

#### 컨테이너에 접속하기
```bash 
docker run -i -t --name ubuntu01 ubuntu /bin/bash

docker attach ubuntu01
```

#### 컨테이너 중지하기
```bash 
docker stop 5555b7dd1385
```

#### 모든 컨테이너 중지하기
```bash 
docker stop $(docker ps -a -q)
```

#### 컨테이너 재부팅하기
```bash 
docker restart 5555b7dd1385
```

#### ps 명령어로 확인해보기
```bash 
docker ps
```

#### 컨테이너 확인하기 (-a 옵션으로 종료된 컨테이너까지 확인하기)
```bash 
docker ps -a
```

#### 컨테이너 삭제하기
```bash 
docker rm 479266e88816
```

#### 모든 컨테이너 삭제
```bash 
docker rm $(docker ps -a -q)
```

#### 모든 이미지 삭제하기
```bash 
docker rmi $(docker images -q)
```
