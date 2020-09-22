# Build

Pre-requisites
--------------
**1.Clone project**
```
git clone  https://github.com/Jooho/scouter-docker.git
cd scouter-docker
export SCOUTER_HOME=$PWD
```
**2.Update Scouter Version**
```
vi config
SCOUTER_VERSION=2.8.1
```
**3.Download Scouter Release**
By default, preq.sh do not need direct url but specifying the direct url is acceptable.
curl -O -L  https://github.com/scouter-project/scouter/releases/download/v2.8.1/scouter-all-2.8.1.tar.gz
```
sh /preq.sh 
```

Scouter Server
---------------
**1. Execute build-docker.sh**
This shell create a new Scouter Server Image with tag "v${SCOUTER_VERSION} and latest"

```
cd ${SCOUTER_HOME}/scouter-server
./build-docker.sh
```

```
cd ${SCOUTER_HOME}/scouter-host-agent
./build-docker.sh
```



**2.Test**
```


docker run -it --name scouter-server -p 6100:6100/tcp -p 6101:6101/udp chicor/scouter-server 


docker run -d -it --name scouter-server -p 6100:6100/tcp -p 6101:6101/udp chicor/scouter-server 


 / ___|  ___ ___  _   _| |_ ___ _ __ 
 \___ \ / __/   \| | | | __/ _ \ '__|
  ___) | (_| (+) | |_| | ||  __/ |   
 |____/ \___\___/ \__,_|\__\___|_|                                      
 
```

Scouter Host Agent
------------------
**1. Execute build-docker.sh**
```
cd ${SCOUTER_HOME}/scouter-host-agent
./build-docker.sh
```

**2.Test**
```
docker run -it --link scouter-server chicor/scouter-host-agent

  ____                  _            
 / ___|  ___ ___  _   _| |_ ___ _ __ 
 \___ \ / __/   \| | | | __/ _ \ '__|
  ___) | (_| (+) | |_| | ||  __/ |   
 |____/ \___\___/ \__,_|\__\___|_|                                      
 
Configure -Dscouter.config=./conf/scouter.conf

```

Scouter Test App
----------------
**1. Execute build-docker.sh**
```
cd ${SCOUTER_HOME}/scouter-test-app
./build-docker.sh
```

**2.Test**
```
docker run -it --link scouter-server -e OBJ_NAME=scouter-jpetstore ljhiyh/scouter-test-app
```


Scouter Tomcat OpenShift S2I
------------------
**1. Execute build-docker.sh**
```
cd ${SCOUTER_HOME}/scouter-tomcat-openshift-s2i
./build-docker.sh
```

**2.Test**
```
docker run -it ljhiyh/webserver30-tomcat7-openshift
```

sudo yum update -y

sudo yum install docker -y

docker --version
Docker version 18.09.9-ce, build 039a7df

sudo docker info
Cannot connect to the Docker daemon at unix:///var/run/docker.sock. Is the docker daemon running?


chmod 777 /var/run/docker.sock

sudo service docker start

sudo docker info

sudo usermod -a -G docker ec2-user


sudo systemctl enable docker

sudo service docker stop

sudo service docker start

sudo yum install git


cat /etc/*release


