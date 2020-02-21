# Docker 사용 가이드 

### Install
#### Mac

```bash 
brew cask install docker
open /Applications/Docker.app
```

### help

```bash 
docker image --help
docker container --help
```

### search

```bash 
docker search nginx
docker search nginx --limit 10
```
### image

#### 이미지 빌드

```bash 
docker image build -t IMAGE     Dockerfile경로
docker image build -t IMAGE:TAG Dockerfile경로
```
#### 이미지 목록 보기

```bash 
docker image ls
```
#### 이미지 다운로드

```bash 
docker image pull IMAGE:TAG
```

#### 이미지 태그 설정

```bash 
docker image tag TARGET_IMAGE:TAG  NEW_IMAGE:TAG
```
#### 이미지 삭제

```bash 
docker image rm IMAGE:TAG

// 실행중이 아닌 모든 컨테이너 삭제
docker image prune  
```

#### 컨테이너 목록 보기

```bash
docker container ls

// 컨테이너 ID만 출력
docker container ls -q    

// 종료된 컨테이너 목록
docker container ls -a   
```

#### 컨테이너를 실행 방법

```bash 
docker container run -it IMAGE:TAG

docker container run -d -t IMAGE:TAG

docker container run -it -p HOST_PORT:CONTAINER_PORT  IMAGE:TAG

docker container run -it --name my_container  IMAGE:TAG

docker container run --rm IMAGE
```
#### 컨테이너 실행시 옵셥 정리 

```bash 
-d: 백그라운드로(데몬) 실행.
-i: Interactive.
-t: TTY 모드 사용.
-it: -i와 -t를 합친 옵션.
-p: 호스트 포트와 연결할 컨테이너 포트 지정.
--name: 구동하는 컨테이너에 내가 지정한 이름을 붙인다.
--rm: 구동이 끝난 후 컨테이너를 삭제한다.
```

#### 컨테이너 

```bash
docker container stop CONTAINER_ID

docker container stop CONTAINER_NAME
```

#### 컨테이너 재시작

```bash
docker container restart CONTAINER_ID

docker container restart CONTAINER_NAME
```
#### 컨테이너 삭제

```bash
docker container rm CONTAINER_ID
docker container rm CONTAINER_NAME
docker container rm -f CONTAINER_NAME

// 실행중이 아닌 모든 컨테이너 삭제
docker container prune               
```

#### 컨테이너 표준 출력 보기

```bash
docker container logs CONTAINER_ID
docker container logs -f CONTAINER_ID  # tail -f 처럼 보여준다
```

#### 실행중인 컨테이너에서 명령 실행

```bash
docker container exec CONTAINER_ID  COMMAND
```

#### 시스템 리소스 사용 상태 보기

```bash 
docker container stats
```

---

### 테스트 시나리오 
#### 1. helloworld 이름으로 스크립트 작성 

```bash 
#! /bin/sh
echo Hello, World!
```
#### 2. Dockerfile 이름으로 스크립트 작성

```bash
FROM ubuntu:latest
COPY helloworld /usr/local/bin
RUN chmod +x /usr/local/bin/helloworld
CMD ["helloworld"]
```

#### 3. 이미지 빌드 하기 

```bash 
docker image build -t helloworld:latest .
```

#### 4. 컨테이너를 실행 하기 

```bash 
docker container run helloworld
```

Hello, World!

#### 5. 이미지 목록을 확인 하기 
- 문자열 하나 출력 하는데 SIZE가 69.9MB , alpine 사용해 사이즈를 줄여 보자 ! 

```bash 
docker image ls
REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
helloworld              latest              584cf23a6604        8 minutes ago       69.9MB
```

#### 6. OS별 사이즈 ( ubuntu => alpine )
- 이미지 목록을 확인해 보면 용량이 줄어 있다 ( 5.53MB ) 

```bash

FROM alpine:latest
COPY helloworld /usr/local/bin
RUN chmod +x /usr/local/bin/helloworld
CMD ["helloworld"]

```

```bash
docker image build -t helloworld:latest .
```

```bash
docker container run helloworld
```

```output
Hello, World!
```

```bash 
docker image ls
REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
helloworld              latest              a9a80e4487ea        6 minutes ago       5.53MB
```


### 간단한 go 웹 서버 띄워보기
#### 1. main.go 파일 작성
```bash 
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
```

####  2. Dockerfile 작성 

```bash 
FROM golang:1.12
RUN mkdir /hello
COPY main.go /hello
CMD ["go", "run", "/hello/main.go"]
```

####  3. 이미지 빌드

```bash
docker build -t hello:latest .
```

####  4. 컨테이너 실행

```bash
docker container run -t -p 8080:3000 hello:latest
```
####  5. 컨테이너 실행 옵션 

```bash 
-p: 포트 지정. 포트는 호스트:컨테이너 형식으로 지정한다.
예: -p 8080:3000은 컨테이너의 3000 포트를 호스트의 8080 포트로 연결하는 것.
```

####  6. curl 호출 

```bash 
curl http://localhost:8080
curl http://localhost:8080/ping
```
#### 7. 실행중인 컨테이너 목록 확인

```bash 
docker container ls
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                      NAMES
1111        hello:latest        "go run /hello/main.…"   6 minutes ago       Up 6 minutes        0.0.0.0:8080->3000/tcp     keen_driscoll
```

#### 8. 실행중인 컨테이너 종료

```bash 
docker container stop 1111
```

#### 9. 데몬으로 컨테이너 실행 

```bash
docker run -d -t -p 8080:3000 hello
```


### docker-compose 사용  

- docker 명령을  옵션을 주렁주렁 붙이는 일을 yml로 편하게 할 수 있다.
- 여러 컨테이너의 실행을 yml 파일로 정의할 수 있어 편리하다.

#### 1. docker-compose.yml 파일 작성 

```bash 
version: "3.7"
services:
  go-helloworld:
    image: helloworld:latest
    ports:
      - 8080:3000
```

#### 2. docker-compose 옵션 

```bash 
-d: 데몬으로 실행
--build: 빌드한 다음 실행
```

#### 3. docker-compose 빌드후 실행 하기 

```bash 
docker-compose up --build
```

#### 4. docker-compose 실행 하기 

```bash 
docker-compose up
docker-compose up -d
```


#### 5. docker-compose로 실행된 컨테이너 정지 하기 

```bash 
docker-compose down
```
---

#### 컨테이너간 포트 공유 ( wordpress 가 mysql DB 접속 ) 

```yaml

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
