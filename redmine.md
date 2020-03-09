
sudo yum install -y yum-utils device-mapper-persistent-data lvm2

sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

docker 설치 

sudo yum install docker-ce docker-ce-cli containerd.io

docker start 

$ sudo systemctl start docker
docker test 

$ sudo docker run hello-world

#### docker 그룹 생성 및 사용자 그룹에 추가 

sudo groupadd docker
sudo usermod -aG docker $USER
sudo newgrp docker
sudo service docker restart
docker-compose 설치(centos)


#### docker-compose 다운로드 

sudo curl -L "https://github.com/docker/compose/releases/download/1.25.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

#### gitlab 설치

version: '3.1'
services:
  db:
    image: gitlab/gitlab-ce
    restart: always
    hostname: localhost  #git 파일 링크가 hostname으로 링크
    ports:
      - 80:80
      - 443:443
    environment:
      - GITLAB_OMNIBUS_CONFIG="gitlab_rails['time_zone'] = 'Asia/Seoul'"
    volumes:
      - /srv/gitlab/data:/etc/gitlab
      - /srv/gitlab/logs:/var/log/gitlab
      - /srv/gitlab/config:/var/opt/gitlab
#관리자 계정 : root/admin

#### redmine 설치

version: '3.1'
services:
  redmine:
    image: redmine:3.4.13
    restart: always
    ports:
      - 3000:3000
    environment:
      REDMINE_DB_MYSQL: db
      REDMINE_DB_PASSWORD: example
      REDMINE_DB_DATABASE: redmine
      REDMINE_SECRET_KEY_BASE: supersecretkey
    volumes:
     - ./git:/git
     - ./plugins:/usr/src/redmine/plugins
  xwiki:
    image: xwiki:lts-mysql-tomcat
    restart: always
    depends_on:
      - db
    ports:
      - 8765:8080
    environment:
      DB_USER: mz
      DB_PASSWORD: mz
      DB_DATABASE: xwiki
      DB_HOST: db
  db:
    image: mysql:5.7
    restart: always
    ports:
      - 3306:3306    
    volumes:
      - ./mysql.cnf:/etc/mysql/conf.d/mysql.cnf
      - /srv/mysql/data:/var/lib/mysql
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
    environment:
      MYSQL_ROOT_PASSWORD: example
      MYSQL_USER: mz
      MYSQL_PASSWORD: mz
      MYSQL_DATABASE: redmine


#### init.sql

grant all privileges on *.* to mz@'%' identified by 'mz';
CREATE DATABASE xwiki;

mysql.cnf
[client]
default-character-set = utf8

[mysqld]
character-set-server = utf8
collation-server = utf8_bin
explicit_defaults_for_timestamp = 1

[mysql]
default-character-set = utf8
