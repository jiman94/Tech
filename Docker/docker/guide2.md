# 도커 이미지 생성 & 컨테이너 실행

$ ./gradlew clean
$ ./gradlew build buildDocker

#### 이미지 확인(이미지아이디, 리파지토리명, 태그 확인)

$ docker images -a

#### 컨테이너 실행

> docker run -p 로컬포트:8080(톰캣포트) --name 컨테이너명 -t 리파지토리명:태그

$ docker run -p 8883:8080 --name demo -t com.example.test/demo:0.0.1-SNAPSHOT


#### 컨테이너 연결 종료
control(⌃) + z

#### 컨테이너 아이디 확인

$ docker ps -a

#### 컨테이너 정지 및 삭제

$ docker stop 컨테이너아이디
$ docker rm 컨테이너아이디



### mysql image run 
```bash 

docker run -d --name spring-boot-mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=docker -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbpassword -d mysql:latest

```


### mysql Container --link 사용 하기  

```bash 

docker run --name spring-boot-docker --link spring-boot-mysql:mysql -p 8080:8080  -d jiman/spring-boot-docker

```
