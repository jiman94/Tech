1. zipkin-scouter-storage를 포함하여 Zipkin server 실행하기



cd /tmp
SCOUTER_COLLECTOR_ADDR=127.0.0.1 \
SCOUTER_COLLECTOR_PORT=6100 \
SCOUTER_SERVICE_MAPS_OJB_TYPE= \
STORAGE_TYPE=scouter \
java -Dloader.path='zipkin-storage-scouter.jar,zipkin-storage-scouter.jar!lib' -Dspring.profiles.active=scouter -cp zipkin.jar org.springframework.boot.loader.PropertiesLauncher



최신버전의 zipkin을 다운로드 받는다.

wget -O zipkin.jar 'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec'

최신버전의 zipkin-storage-scouter를 다운로드 받는다.

wget -O zipkin-storage-scouter.jar 'https://search.maven.org/remote_content?g=io.github.scouter-project&a=zipkin-autoconfigure-storage-scouter-udp&v=LATEST&c=module'


아래 명령어로 zipkin server를 (scouter storage 옵션으로) 실행한다.
STORAGE_TYPE=scouter \
java -Dloader.path='zipkin-storage-scouter.jar,zipkin-storage-scouter.jar!lib' -Dspring.profiles.active=scouter -cp zipkin.jar org.springframework.boot.loader.PropertiesLauncher


환경변수를 통해 Scouter Collector를 지정하거나 특정 Tag값을 XLog의 확장 컬럼에 매핑하는 등의 추가적인 작업을 할 수 있다.
보다 상세한 옵션은 zipkin-scouter 문서를 참고한다. 