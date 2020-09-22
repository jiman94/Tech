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
