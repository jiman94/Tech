docker images -a

docker volume prune -f

docker system prune -a -f



· docker container prune은 중지된 모든 컨테이너를 삭제한다.
· docker image prune은 이름 없는 모든 이미지를 삭제한다.
· docker network prune은 사용되지 않는 도커 네트워크를 모두 삭제한다.
· docker volume prune은 도커 컨테이너에서 사용하지 않는 모든 도커 볼륨을 삭제한다.
· docker system prune -a는 중지된 모든 컨테이너, 사용되지 않은 모든 네트워크, 하나 이상의 컨테이너에서 사용되지 않는 모든 이미지를 삭제한다. 따라서 남아있는 컨테이너 또는 이미지는 현재 실행 중인 컨테이너에서 필요하다.



http://stg.pilot.co.kr/main/home

http://stg.pilot.co.kr/main

http://co.stg.pilot.co.kr/lgin



scp -i "chicor_key_pair.pem" ec2-user@10.222.16.54:/tmp/security.tar.gz ./

ssh -i "chicor_key_pair.pem" ec2-user@10.222.16.54


scp -P 40022 -i "chicor-prd-batch-kp.pem" /tmp/security.tar.gz  ec2-user@10.222.16.54:/tmp



scp -i "chicor_key_pair.pem" ec2-user@10.222.16.54:/tmp/ssg.tar.gz .




curl -v 10.222.16.54:8098/oz80/sample_mng.html

scp -i "chicor-prd-batch-kp.pem" ssg.tar ec2-user@10.222.33.240:/tmp

scp oz80.tar chicor10.222.16.54:/app


ssh -i "chicor_key_pair.pem" ec2-user@10.222.16.54


ssh -i "chicor_key_pair.pem" ec2-user@10.222.16.54

scp -P 40022 -i "chicor-prd-batch-kp.pem" oz80.tar ec2-user@10.222.16.54:/tmp


scp -i "chicor_key_pair.pem" //home/chicor/apache-tomcat-9.0.33.tar.gz ec2-user@10.222.16.54:/tmp


scp -i "chicor_key_pair.pem" ./oz80.tar ec2-user@10.222.16.54:/tmp


scp -i "chicor-prd-batch-kp.pem" oz80.tar ec2-user@10.222.16.54:/tmp

