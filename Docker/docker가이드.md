### Install

#### Mac
$ brew cask install docker
$ open /Applications/Docker.app



### help
$ docker image --help
$ docker container --help

### search
$ docker search nginx
$ docker search nginx --limit 10

### image

####  이미지 빌드
$ docker image build -t IMAGE     Dockerfile경로
$ docker image build -t IMAGE:TAG Dockerfile경로

#### 이미지 목록 보기
$ docker image ls

#### 이미지 다운로드
$ docker image pull IMAGE:TAG

#### 이미지 태그 설정
$ docker image tag TARGET_IMAGE:TAG  NEW_IMAGE:TAG

#### 이미지 삭제
$ docker image rm IMAGE:TAG
$ docker image prune        # 실행중이 아닌 모든 컨테이너 삭제

container

#### 컨테이너 목록 보기
$ docker container ls
$ docker container ls -q    # 컨테이너 ID만 출력
$ docker container ls -a    # 종료된 컨테이너 목록

#### 컨테이너를 실행하는 여러 방법들

$ docker container run -it IMAGE:TAG
$ docker container run -d -t IMAGE:TAG
$ docker container run -it -p HOST_PORT:CONTAINER_PORT  IMAGE:TAG
$ docker container run -it --name my_container  IMAGE:TAG
$ docker container run --rm IMAGE

```bash 
-d: 백그라운드로(데몬) 실행.
-i: Interactive.
-t: TTY 모드 사용.
-it: -i와 -t를 합친 옵션.
-p: 호스트 포트와 연결할 컨테이너 포트 지정.
--name: 구동하는 컨테이너에 내가 지정한 이름을 붙인다.
--rm: 구동이 끝난 후 컨테이너를 삭제한다.
```

#### 컨테이너 정지
$ docker container stop CONTAINER_ID
$ docker container stop CONTAINER_NAME

#### 컨테이너 재시작
$ docker container restart CONTAINER_ID
$ docker container restart CONTAINER_NAME

#### 컨테이너 삭제
$ docker container rm CONTAINER_ID
$ docker container rm CONTAINER_NAME
$ docker container rm -f CONTAINER_NAME
$ docker container prune                # 실행중이 아닌 모든 컨테이너 삭제

#### 컨테이너 표준 출력 보기
$ docker container logs CONTAINER_ID
$ docker container logs -f CONTAINER_ID  # tail -f 처럼 보여준다

#### 실행중인 컨테이너에서 명령 실행
$ docker container exec CONTAINER_ID  COMMAND

#### 시스템 리소스 사용 상태 보기
$ docker container stats

- helloworld 출력해 보기
- helloworld라는 이름의 간단한 셸 스크립트를 작성한다.

#! /bin/sh

echo Hello, World!
이제 helloworld를 실행할 환경을 정의하자. 
Dockerfile을 작성

FROM ubuntu:latest

COPY helloworld /usr/local/bin
RUN chmod +x /usr/local/bin/helloworld

CMD ["helloworld"]


이미지를 빌드한다.

$ docker image build -t helloworld:latest .
컨테이너를 실행해 보자. Hello, World! 문자열이 출력되면 성공이다.

$ docker container run helloworld
Hello, World!

이미지 목록을 확인해 보자.

$ docker image ls
REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
helloworld              latest              584cf23a6604        8 minutes ago       69.9MB

그냥 문자열 출력만 하는데 SIZE가 69.9MB나 된다.

그렇다면 Dockerfile을 수정하여 FROM 값을 용량이 작은 alpine으로 바꿔보자.

# ubuntu => alpine
FROM alpine:latest

COPY helloworld /usr/local/bin
RUN chmod +x /usr/local/bin/helloworld

CMD ["helloworld"]
이미지 목록을 확인해 보면 용량이 5.53MB로 줄어 있다.

$ docker image ls
REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
helloworld              latest              a9a80e4487ea        6 minutes ago       5.53MB

간단한 go 웹 서버 띄워보기
다음과 같은 main.go파일을 작성하자.

package main // import "github.com/johngrib/go-http-helloworld"

import (
    "fmt"
    "log"
    "net/http"
)

func main() {

    http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
        log.Println("/ request")
        fmt.Fprintf(w, "Hello World\n")
    })

    http.HandleFunc("/ping", func(w http.ResponseWriter, r *http.Request) {
        log.Println("/ping request")
        fmt.Fprintf(w, "pong\n")
    })

    server := &http.Server{
        Addr: ":3000",
    }

    if err := server.ListenAndServe(); err != nil {
        log.Println(err)
    }
}

그리고 다음과 같이 Dockerfile도 작성해 준다.

FROM golang:1.12

RUN mkdir /hello
COPY main.go /hello
CMD ["go", "run", "/hello/main.go"]
그리고 다음 명령어를 실행해주면 컨테이너 환경에서 서버가 실행된다.

#### 이미지 빌드
$ docker build -t hello:latest .

#### 컨테이너 실행

$ docker container run -t -p 8080:3000 hello:latest

-p: 포트 지정. 포트는 호스트:컨테이너 형식으로 지정한다.
예: -p 8080:3000은 컨테이너의 3000 포트를 호스트의 8080 포트로 연결하는 것.
실행되면 다음과 같이 요청을 보낼 수 있다.

curl http://localhost:8080을 입력하면 Hello World가 출력된다.
curl http://localhost:8080/ping을 입력하면 pong이 출력된다.
한편 docker container ls 명령으로 실행중인 컨테이너 목록을 확인할 수 있다.

$ docker container ls
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                      NAMES
1111        hello:latest        "go run /hello/main.…"   6 minutes ago       Up 6 minutes        0.0.0.0:8080->3000/tcp     keen_driscoll
다음 명령으로 실행중인 컨테이너를 종료할 수 있다.

$ docker container stop 1111
다음과 같이 해도 된다.

$ docker container stop 1111
만약 실행할 때 도커 컨테이너가 포어그라운드를 차지하는 것이 싫다면 -d 옵션을 주면 된다. 데몬으로 실행한다.

$ docker run -d -t -p 8080:3000 hello
docker-compose
docker-compose를 사용하면
docker 명령에 옵션을 주렁주렁 붙이는 일을 yml로 편하게 할 수 있다.
여러 컨테이너의 실행을 yml 파일로 정의할 수 있어 편리하다.
위에서 만든 go 웹 서버 디렉토리로 가서 다음과 같은 docker-compose.yml 파일을 작성하자.

version: "3.7"
services:
  go-helloworld:
    image: helloworld:latest
    ports:
      - 8080:3000
그 다음 다음과 같이 up을 실행하면 컨테이너가 뜬다.

$ docker-compose up
$ docker-compose up -d
$ docker-compose up --build
-d: 데몬으로 실행
--build: 빌드한 다음 실행

컨테이너를 정지하려면 down 옵션을 주면 된다.

$ docker-compose down


```yml 
docker run -d \
  -e MYSQL_ALLOW_EMPTY_PASSWORD=true \
  --name mysql \
  --network=app-network \
  mysql:5.7

docker exec -it mysql mysql

create database wp CHARACTER SET utf8;
grant all privileges on wp.* to wp@'%' identified by 'wp';
flush privileges;

quit

docker run -d -p 8000:80 \
  --network=app-network \
  -e WORDPRESS_DB_HOST=mysql \
  -e WORDPRESS_DB_NAME=wp \
  -e WORDPRESS_DB_USER=wp \
  -e WORDPRESS_DB_PASSWORD=wp \
  wordpress
```  