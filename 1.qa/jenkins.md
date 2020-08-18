1. chicor-jenkins  빌드 

cd /home/ec2-user/apps/docker/docker-jenkins 

2. Docker 확인 후 docker build

docker build -t selenium-jenkins .

3. Docker run

docker run -d  -p 80:8080 -p 50001:50000  -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home --restart unless-stopped --name selenium-jenkins selenium-jenkins 

# 모든 이미지 삭제하기

docker rmi $(docker images -q)


docker run -d --env JAVA_OPTS="-Dorg.jenkinsci.plugins.gitclient.Git.timeOut=20" -p 8088:8080 -p 50001:50000  -v /Users/mz03-jmryu/Downloads/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v /etc/localtime:/etc/localtime:ro -e TZ=Asia/Seoul --restart unless-stopped --name selenium-jenkins selenium-jenkins  

# Dockerfile 

# Getting jenkins image
# FROM jenkins/jenkins:2.176.3
FROM jenkins/jenkins:2.235.3
# FROM jenkinsci/blueocean

# Changing the user to root
USER root
ENV TZ=America/New_York
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# installing chrome driver
RUN apt-get update && apt-get -qq -y install curl

RUN apt-get install ca-certificates

# We need wget to set up the PPA and xvfb to have a virtual screen and unzip to install the Chromedriver
RUN apt-get install -y wget xvfb unzip

RUN curl -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -

RUN echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list

# Update the package list and install chrome
RUN apt-get update -y
RUN apt-get install -y google-chrome-stable


# Set up Chromedriver Environment variables
ENV CHROMEDRIVER_VERSION 85.0.4183.38
ENV CHROMEDRIVER_DIR /chromedriver
RUN mkdir $CHROMEDRIVER_DIR

RUN curl http://chromedriver.storage.googleapis.com/85.0.4183.38/chromedriver_linux64.zip -o /chromedriver/chromedriver_linux64.zip -J -L

RUN unzip $CHROMEDRIVER_DIR/chromedriver* -d $CHROMEDRIVER_DIR

RUN chmod 777 -R /chromedriver

# Put Chromedriver into the PATH
ENV PATH $CHROMEDRIVER_DIR:$PATH

USER jenkins
