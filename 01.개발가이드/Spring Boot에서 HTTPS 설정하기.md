# 키 형식
server.ssl.enabled=true
server.ssl.key-store-type=PKCS12
server.ssl.key-sotre=keystore.p12
server.ssl.key-store-password=본인의 패스워드 
server.ssl.key-alias=tomcat

# 만약 스프링 시큐리티를 사용중이라면 아래 설정도 포함
security.require-ssl=true


keytool -import -alias tomcat -file [키 파일] -keystore keystore.p12 -storepass [비밀번호]
