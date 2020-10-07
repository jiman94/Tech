# guest os 파일 host os 로 가져오는 방법 

1. 호스트 -> 컨테이너
docker cp [host 파일경로] [container name]:[container 내부 경로]

2. 컨테이너 -> 호스트
docker cp [container name]:[container 내부 경로] [host 파일경로]


docker ps : 컨테이너명 확인

docker cp scouter-server:/opt/server/database/20201006 .

(docker cp [container name]:[container 파일 경로] 복사 할 경로)

