## Router53 / S3 / Scouter Backup
개요 : Scouter 일자별 DataBase 파일을 지정 일자별 빽업 및 삭제 처리 AWS 진행 구성

Router53
도메인 : scouter.pilot.co.kr
타입 : S3 웹 사이트 엔드포인트에 대한 별칭
리전 : 아시아태평양 서울
엔드포인트 : s3-website.ap-northeast-2.amazonaws.com.


#### S3
- 버킷 정책
- 전체 허용

```yaml 
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "1",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::scouter.pilot.co.kr/*"
        }
    ]
}
```

#### 아이피 제한(필요시)

```yaml  
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "AllowIPs",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::awss3buckettest/*",
            "Condition": {
                "IpAddress": {
                    "aws:SourceIP": "192.168.0.0/24"
                },
                "NotIpAddress": {
                    "aws:SourceIP": "192.168.0.100/32"
                }
            }
        }
    ]
}
```

#### CORS(참조)

```xml 
<?xml version="1.0" encoding="UTF-8"?>
<CORSConfiguration xmlns="http://s3.amazonaws.com/doc/2006-03-01/">
<CORSRule>
    <AllowedOrigin>*</AllowedOrigin>
    <AllowedOrigin>http://m.pilot.co.kr</AllowedOrigin>
    <AllowedMethod>POST</AllowedMethod>
    <AllowedMethod>GET</AllowedMethod>
    <AllowedMethod>PUT</AllowedMethod>
    <AllowedMethod>DELETE</AllowedMethod>
    <AllowedMethod>HEAD</AllowedMethod>
    <AllowedHeader>*</AllowedHeader>
</CORSRule>
</CORSConfiguration>
```

## Scouter
#### Docker run command : 볼륨구성

```bash  
docker run -it --name scouter-server \
-v /home/ec2-user/scouter/apm-scouter/server/database:/opt/server/database \
-p 6100:6100/tcp -p 6101:6101/udp chicor/scouter-server
```


### crond
```bash 
#### daily scouter backup -4 day
0 1 * * * /home/ec2-user/scouter/apm-scouter/bin/daily-scouter-backup.sh
#### daily scouter delete -4 day
0 2 * * * /home/ec2-user/scouter/apm-scouter/bin/daily-scouter-delete.sh
```

#### backup.sh
```bash 
#!/bin/sh
aws_access_key_id=[IAM 참조]
aws_secret_access_key=[IAM 참조]

BACKUPDATE=`date -d "-4 days" "+%Y%m%d"`;
TARGETDIR="/home/ec2-user/scouter/apm-scouter/server/database/$BACKUPDATE";
BACKUPDIR="/home/ec2-user/scouter/apm-scouter/backup";
# CONTAINERDIR="/opt/server/database/$BACKUPDATE";
# docker cp scouter-server:$CONTAINERDIR $BACKUPDIR # 볼륨 없을 경우??
cd $TARGETDIR
sudo tar -cvf $BACKUPDATE.tar *
sudo mv *.tar $BACKUPDIR
aws s3 cp --recursive $BACKUPDIR s3://scouter.pilot.co.kr/ --acl public-read
delete.sh
aws_access_key_id=[IAM 참조]
aws_secret_access_key=[IAM 참조]

BACKUPDATE=`date -d "-4 days" "+%Y%m%d"`;
BACKUPDIR="/home/ec2-user/scouter/apm-scouter/backup";
TARGETDIR="/home/ec2-user/scouter/apm-scouter/server/database/$BACKUPDATE";
NRES=`aws s3 ls s3://scouter.pilot.co.kr/$BACKUPDATE.tar | grep $BACKUPDATE.tar | wc -l`
echo $NRES
if [[ $NRES -lt 1 ]]; then
  echo "Do not found file"
  exit 1;
fi
echo "File found";
sudo rm -f $BACKUPDIR/*.tar
sudo rm -rf $TARGETDIR
```

