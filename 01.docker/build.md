# 1. 개요

## 1.2. 폴더 설명

| 폴더명               | 용도                          | 개발환경    |
| -------------------- | -------------------------- | ----------- |
| backend-node   | send a node greeting message     | node.js |
| backend-spring | send a spring greeting message   | Spring boot    |
| bff            | gateway (backend for frontend)   | Spring boot |
| frontend-vue   | integrate all micro services     | Vue.js |

# 2. 작업 흐름

> 로컬 개발 환경 > docker (compose) 환경 > kubenetes 환경 > + istio

## 2.1. 로컬 개발 환경

## 2.2. docker 환경

### 2.2.1. docker build

#### 2.2.1.1. Node 프로젝트 docker build

> backend-node / frontend-vue

```sh
docker build . -t emoket/frontend-vue:1.0.0
docker build . -t emoket/backend-node:1.0.0
```

#### 2.2.1.2. Spring boot 프로젝트 docker build

```sh
mvn clean package docker:build
```

### 2.2.2. docker run

> docker 환경에서 테스트 시 서비스 간 호출을 할 수 있도록 `--link` 옵션을 적용하는 것이 핵심!

``` sh
docker run --name backend-node -p 8083:8083 -d emoket/backend-node:1.0.0
docker run --name backend-spring -p 8082:8082 -d emoket/backend-spring:1.0.0
docker run --name bff -d -p 8081:8081 -e 'BACKEND_SPRING_HOST=backend-spring' -e 'BACKEND_SPRING_PORT=8082' -e 'BACKEND_NODE_HOST=backend-node' -e 'BACKEND_NODE_PORT=8083' --link backend-node --link backend-spring emoket/bff:1.0.0
docker run --name frontend-vue -p 8080:8080 -d --link bff emoket/frontend-vue:1.0.0
```

### 2.2.3. docker-compose up

> 컨테이너가 많아질 경우 docker run 명령어를 일일이 수행하는 것에는 한계가 있기에 docker-compose 를 사용한다.

```sh
docker-compose up -d
```

## 2.3. Kubernetes 환경

> helm chart 를 이용하여 모든 구성을 한 번에 설치



# 브랜치 

## 브랜치 관리 전략

* 해당 프로젝트는 Git-Flow 를 이용하여 브랜치를 관리하였습니다.
![git_flow](https://user-images.githubusercontent.com/54772162/88681478-92ce9600-d12c-11ea-8be4-8b516f54cc6d.png)

* master : 배포시 사용할 브랜치.    
* develop : 다음 버전을 개발하는 브랜치, 완전히 배포가 가능하다고 생각되면 master 브랜치에 merge 합니다.    
* feature : 기능을 개발하는 브랜치.    
* release : 배포를 준비할 때 사용할 브랜치.    
* hotfix : 배포 후에 발생한 버그를 수정 하는 브랜치.    
