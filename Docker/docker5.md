# 도커

* 가볍고 빠른 성능
* 컨테이너간 영향 분리
* 오버헤드 5% 이내로 줄일 수 있음

## 도커의 설치 및 활용

```text
$ docker search ubuntu    이미지 검색
$ docker pull ubuntu      이미지 다운로드
$ docker images           이미지 리스트 출력
$ docker run (-it) --name=ubuntu_name ubuntu   컨테이너 생성(-it하면 바로 접속까지)
$ docker attach ubuntu
$ docker exec -it ubuntu bash    컨테이너 접속
# exit or ctrl-P-Q(정지하지 않고 나옴)
```

### 도커 사용법

도커 기본 명령어

```text
$ docker ps -a            도커 컨테이너 리스트 (-a 실행/종료중인 것 모두)
$ docker stop ubuntu      정지
$ docker restart ubuntu   재시작
$ docker rm ubuntu        컨테이너 삭제
$ docker rmi ubuntu       이미지 삭제
$ docker inspect ubuntu | grep "IPAddress"  컨테이너 IP 주소 확인
```

```text
$ docker save -o ubuntu_img.tar ubuntu    ubuntu이미지를 AUFS->디스크파일시스템으로 저장
$ docker load -i ubuntu_img.tar           로컬 파일시스템에서 이미지 만들기
$ docker images
$ docker tag ubuntu sample
```

## 컨테이너기반 가상화와 자동화기술 : Dockerfile

### 커밋을 통한 이미지 생성

```text
$ docker diff ubuntu
$ docker commit -m "test" -a "author" ubuntu ubuntu_nano
$ docker images
```

### 도커파일 기반 이미지 생성

```text
$ mkdir sample
$ cd sample
$ vi Dockerfile

FROM ubuntu:latest (base image)
MAINTAINER author <author@gmail.com>
RUN apt-get update
RUN apt-get install -y nano
ENV TERM=xterm

$ docker build --tag=ubuntu_nano .(도커파일 경로)
$ docker images
$ docker history ubuntu_nano    이미지 변경사항 확인
```

## Dockerfile 상세

* FROM : base image
* MAINTAINER : 생성자
* RUN : command 수행. 패키지 추가
* CMD : 컨테이너 생성 시점 명령어 ex\) CMD \["echo $PATH"\]
* ENTRYPOINT : CMD와 비슷. run or start 될 때 실행

#### 도커에서 환경변수 설정

* docker run --env sample=/sample --name=ubuntu ubuntu
* dockerfile : ENV sample=/sample

#### 포트 노출

* Dockerfile : EXPOSE 80 \(container port\)
* 컨테이너의 80번 포트와 호스트의 80번 포트 연결
  * 외부에서 80으로 접근하면 컨테이너의 80으로 연결\(포트포워딩\)
  * docker run -p 80:80 --name=ubuntu ubuntu

#### 호스트의 파일을 이미지 생성시 추가\(복사\)

* Dockerfile
  * ADD ~/sample.txt /sample.txt

#### RUN/CMD/ENTRYPOINT 수행하기 전 사용자계정 지정

* Dockerfile : USER user2

#### RUN/CMD/ENTRYPOINT 수행하기 전 폴더 지정

* Dockerfile : WORKDIR ~/sample \(~/sample 폴더로 변경해서 명령어 수행\)

#### 볼륨연결 \(컨테이너의 폴더와 호스트의 물리폴더 간 연결\)

* Dockerfile : VOLUME /download \(컨테이너 폴더\)

  ```text
           VOLUME ["/data","/sample"]
  ```

* 물리폴더 ~/Downloads를 컨테이너의 /download 폴더로 연결
* docker run --name=ubuntu ubuntu -v ~/Downloads:/download

#### 도커 컨테이너 간 연결

* mysql 컨테이너 실행 : docker run ~~~ --name=mysql mysql
* 우분투 컨테이너 실행 : docker run --name ubuntu -d --link mysql:mysql ubuntu

## 실전 도커 적용실습

1. 오픈자바설치

   Dockerfile

   ```text
   RUN apt-get install -y openjdk-8-jdk
   RUN apt-get clean
   ...
   ENV JAVA_HOME=JVM이 설치된 폴더
   ENV CLASSPATH=자바컴파일러와 자바가상머신과 같은 실행파일 위치
   ENV PATH=자바클래스파일 .class 위치
   ```

2. 오라클자바설
3. SSH\(Secure shell\) 설정
4. MySQL

   ```text
   $ docker pull mysql
   $ docker run -e(환경변수) MYSQL_ROOT_PASSWORD=kitri -d(서버모드) -p 3306:3306 --name=mysql mysql
   $ docker exec -it mysql bash
   # mysql -uroot -p     mysql 접속
   ```

   다른 컨테이너에서 위 mysql 컨테이너 접근

   ```text
   $ docker exec -it sample_ubuntu bash
   # apt-get install mysql-client
   $ docker inspect mysql | grep "IPAddress"
   $ docker exec -it sample_ubuntu bash
   # mysql -h 172.17.0.5 -uroot -p
   ```

   ```text
   $ docker run --name=ubuntu --link db:db ubuntu
   # apt-get install mysql-client
   # mysql -h(호스트 ip 지정) db -uroot -p
   ```

### Dockerfile Sample

Dockerfile : 오픈자바 SSH Hadoop 설치

```text
FROM ubuntu:latest
MAINTAINER pilot <pilot@gmail.com>

RUN apt-get update
RUN apt-get install –y openjdk-8-jdk

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV CLASSPATH=$JAVA_HOME/lib/*:.
ENV PATH=$PATH:$JAVA_HOME/bin

RUN apt-get install –y openssh-server
RUN mkdir /var/run/sshd
RUN echo ‘root:kitri’ | chpasswd
RUN sed –i ‘s/PermitRootLogin prohibit-password/PermitRootLogin yes/’ /etc/ssh/sshd_config
RUN sed –i ‘s/PermitRootLogin without-password/PermitRootLogin yes/’ /etc/ssh/sshd_config
EXPOSS 22
CMD [“/usr/sbin/sshd”, “-D”]

RUN wget http://archive.apache.org/dist/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz
RUN tar xvfz hadoop-1.2.1.tar.gz

ENV HADOOP_HOME=/hadoop-1.2.1
ENV PATH=$PATH:$HADOOP_HOME/sbin
```

