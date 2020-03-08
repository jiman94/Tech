#### Docker Container 실행하기

-d : detached mode 흔히 말하는 백그라운드 모드
-p : 호스트와 컨테이너의 포트연결(포워딩)
-v : 호스트와 컨테이너의 디렉토리 연결(마운트, volume)
-e : 컨테이너 내에서 사용할 환경변수 설정(environment)
--name : 컨테이너 이름 설정
--rm : 프로세스 종료시 컨테이너 자동 제거
-i : Keep STDIN open even if not attached(interactive)
-t : Allocate a pseudo-TTY(tty)
-it : -i와 -t를 동시에 사용한 것으로 터미널 입력을 위한 옵션
--network : 네트워크 연결

docker run -d -p 3306:3306 \
  -e MYSQL_ALLOW_EMPTY_PASSWORD=true \
  --name mysql \
  mysql:5.7 

docker exec -it mysql mysql

mysql > create database wp CHARACTER SET utf8;

wp 계정에 권한부여하기
mysql > grant all privileges on wp.* to wp@'%' identified by 'wp';
mysql > flush privileges;
mysql > quit

docker network connect app-network mysql

docker-compose 파일명이 다를 경우 -f 옵션 사용
$ docker-compose -f test.yml up

$ docker run -it ubuntu:latest /bin/bash
# apt-get update
# apt-get install git
# git version

$ docker run -it ubuntu:git bash
$ docker build -t ubuntu:git02 .
$ docker run -it ubuntu:git02 bash


#### Jenkins 실행하기

기본 Jenkins 프로젝트에 docker 와 docker-compose 가 설치된 도커 이미지 사용

docker run -u root --rm -p 8080:8080 --name jenkins \
           -v $(데이터 디렉토리):/var/jenkins_home \
           -v /var/run/docker.sock:/var/run/docker.sock \
           subicura/jenkins:2

데이터 디렉토리 : /Users/${USER}/jenkins
http://localhost:8080 접속해서 확인

	
