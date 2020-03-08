호스트의 /db_data 디렉토리를 컨테이너의 /var/lib/mysql 디렉토리로 마운트
이제 데이터베이스 파일은 호스트의 /db_data 디렉토리에 저장되고 컨테이너를 삭제해도 데이터는 사라지지 않습니다. 
최신버전의 MySQL 이미지를 다운받고 다시 컨테이너를 실행할 때 동일한 디렉토리를 마운트 한다면 그대로 데이터를 사용할 수 있습니다.


version: '2'

services:
   db:
     image: mysql:5.7
     volumes:
       - db_data:/var/lib/mysql
     restart: always
     environment:
       MYSQL_ROOT_PASSWORD: wordpress
       MYSQL_DATABASE: wordpress
       MYSQL_USER: wordpress
       MYSQL_PASSWORD: wordpress

   wordpress:
     depends_on:
       - db
     image: wordpress:latest
     volumes:
       - wp_data:/var/www/html
     ports:
       - "8000:80"
     restart: always
     environment:
       WORDPRESS_DB_HOST: db:3306
       WORDPRESS_DB_PASSWORD: wordpress
volumes:
    db_data:
    wp_data:
