cat nginx.sh
#! /bin/bash
docker run --name pilot-mobile  -p 8001:80 -v /home/ec2-user/pilot/publishing/mf:/usr/share/nginx/html -d nginx
docker run --name pilot-web     -p 8002:80 -v /home/ec2-user/pilot/publishing/df:/usr/share/nginx/html -d nginx
docker run --name pilot-partner -p 8003:80 -v /home/ec2-user/pilot/publishing/partner:/usr/share/nginx/html -d nginx
docker run --name pilot-back    -p 8004:80 -v /home/ec2-user/pilot/publishing/back:/usr/share/nginx/html -d nginx
docker run --name pilot-mockup  -p 8005:80 -v /home/ec2-user/pilot/publishing/mockup:/usr/share/nginx/html -d nginx

docker run --restart=always -d <container>

docker ps -a
docker start c9bf4428a487
docker start 5388833f46cd
docker start be51a6c46bfc
docker start 7fdaeb58f79b
docker start 43434e6d1a6e
docker start b796c9907613
