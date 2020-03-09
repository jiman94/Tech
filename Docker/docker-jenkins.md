
#### 1. Jenkins 설치

https://github.com/jenkinsci/docker

https://jenkins.io/download/


#### 2. pilot-jenkins  빌드 

docker build --build-arg JENKINS_VERSION=2.204.5 -t pilot-jenkins .


#### Mac
docker run -d -p 8080:8080 -p 50000:50000 -v /Users/jmryu/Downloads/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --restart unless-stopped --name pilot-jenkins pilot-jenkins:latest



#### 3. docker ps 실행 시 아래 오류 발생하면,

unix:///var/run/docker.sock. Is the docker daemon running?

위와 같은 명령어가 뜨면 docker service가 실행이 안되어 있는 것이다.
 
$sudo systemctl status docker
 
상태를 확인해 봐라 stop이면,
 
아래 명령어를 입력해 주자. 그리고 다시 명령어를 실행해보자

$sudo systemctl start docker
$sudo systemctl enable docker


4. Jenkins 설치

4-1 AWS linux2 용 docker 설치

sudo yum –y upgrade
sudo yum –y install docker

설치가 안되면,
sudo amazon-linux-extras install –y docker




docker build --build-arg JENKINS_VERSION=2.190.2 -t pilot-jenkins .

docker run -d -p 8080:8080 \ 
            -p 50000:50000 \ 
            -v /Users/mz02-jmryu/Downloads/jenkins:/var/jenkins_home \
            -v /var/run/docker.sock:/var/run/docker.sock \
            --restart unless-stopped \
            --name chicor-jenkins pilot-jenkins:latest




-d: 데몬 상태로 실행한다는 뜻
-p: 컨테이너 내부(50000)의 포트를 외부(8080)로 내보낼 포트로 연결
-v: 호스트에 볼륨을 지정해 주는 것 (해당 컨테이너가 삭제되면, 내부에 작성했던 스크립트 등의 데이터가 삭제되므로 볼륨을 지정해 외부에 백업하는 용도)
-restart: 컨테이너 안의 프로세스 종료 시 재시작 정책을 설정
 no: 프로세스가 종료되더라도 컨테이너를 재시작하지 않는다. (--restart=”no”)
 on-failure: 프로세스의 Exit Code가 0이 아닐 때만 재시작재시도 횟수를 지정 (--restart=”on-failure:10”)
 unless-stopped: 외부에서 중지/재시작하지 않는한 재시작
 always: 프로세스의 Exit Code와 상관없이 재시작합니다. (--restart=”always”)
--name : 해당 컨테이너의 이름을 지정

```bash 

FROM ubuntu:18.04

FROM openjdk:8-jdk-stretch

RUN apt-get update && apt-get upgrade -y && apt-get install -y git curl && rm -rf /var/lib/apt/lists/*

ARG user=jenkins
ARG group=jenkins
ARG uid=1000
ARG gid=1000
ARG http_port=8080
ARG agent_port=50000
ARG JENKINS_HOME=/var/jenkins_home
ARG REF=/usr/share/jenkins/ref

ENV JENKINS_HOME $JENKINS_HOME
ENV JENKINS_SLAVE_AGENT_PORT ${agent_port}
ENV REF $REF

# Jenkins is run with user `jenkins`, uid = 1000
# If you bind mount a volume from the host or a data container,
# ensure you use the same uid
RUN mkdir -p $JENKINS_HOME \
  && chown ${uid}:${gid} $JENKINS_HOME \
  && groupadd -g ${gid} ${group} \
  && useradd -d "$JENKINS_HOME" -u ${uid} -g ${gid} -m -s /bin/bash ${user}

# Jenkins home directory is a volume, so configuration and build history
# can be persisted and survive image upgrades
VOLUME $JENKINS_HOME

# $REF (defaults to `/usr/share/jenkins/ref/`) contains all reference configuration we want
# to set on a fresh new installation. Use it to bundle additional plugins
# or config file with your custom jenkins Docker image.
RUN mkdir -p ${REF}/init.groovy.d

# Use tini as subreaper in Docker container to adopt zombie processes
ARG TINI_VERSION=v0.16.1
COPY tini_pub.gpg ${JENKINS_HOME}/tini_pub.gpg
RUN curl -fsSL https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini-static-$(dpkg --print-architecture) -o /sbin/tini \
  && curl -fsSL https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini-static-$(dpkg --print-architecture).asc -o /sbin/tini.asc \
  && gpg --no-tty --import ${JENKINS_HOME}/tini_pub.gpg \
  && gpg --verify /sbin/tini.asc \
  && rm -rf /sbin/tini.asc /root/.gnupg \
  && chmod +x /sbin/tini

# jenkins version being bundled in this docker image
ARG JENKINS_VERSION
ENV JENKINS_VERSION ${JENKINS_VERSION:-2.176.2}

# jenkins.war checksum, download will be validated using it
ARG JENKINS_SHA=47620a00004af5634e45904149897fe4a36b0463ec691bfabc2086779f90f127

# Can be used to customize where jenkins.war get downloaded from
ARG JENKINS_URL=https://repo.jenkins-ci.org/public/org/jenkins-ci/main/jenkins-war/${JENKINS_VERSION}/jenkins-war-${JENKINS_VERSION}.war

# could use ADD but this one does not check Last-Modified header neither does it allow to control checksum
# see https://github.com/docker/docker/issues/8331
RUN curl -fsSL ${JENKINS_URL} -o /usr/share/jenkins/jenkins.war \
  && echo "${JENKINS_SHA}  /usr/share/jenkins/jenkins.war" | sha256sum -c -

ENV JENKINS_UC https://updates.jenkins.io
ENV JENKINS_UC_EXPERIMENTAL=https://updates.jenkins.io/experimental
ENV JENKINS_INCREMENTALS_REPO_MIRROR=https://repo.jenkins-ci.org/incrementals
RUN chown -R ${user} "$JENKINS_HOME" "$REF"

# for main web interface:
EXPOSE ${http_port}

# will be used by attached slave agents:
EXPOSE ${agent_port}

ENV COPY_REFERENCE_FILE_LOG $JENKINS_HOME/copy_reference_file.log

USER ${user}

COPY jenkins-support /usr/local/bin/jenkins-support
COPY jenkins.sh /usr/local/bin/jenkins.sh
COPY tini-shim.sh /bin/tini
ENTRYPOINT ["/sbin/tini", "--", "/usr/local/bin/jenkins.sh"]

# from a derived Dockerfile, can use `RUN plugins.sh active.txt` to setup ${REF}/plugins from a support bundle
COPY plugins.sh /usr/local/bin/plugins.sh
COPY install-plugins.sh /usr/local/bin/install-plugins.sh

# Install the latest Docker CE binaries and add user `jenkins` to the docker group
USER root

RUN apt-get update && \
    apt-get -y install apt-transport-https \
    ca-certificates \
    curl \
    gnupg2 \
    software-properties-common && \
    curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
    add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
    $(lsb_release -cs) \
    stable" && \
    apt-get update && \
    apt-get -y install docker-ce && \
    usermod -aG docker jenkins

# drop back to the regular jenkins user - good practice
USER ${user} 


```
