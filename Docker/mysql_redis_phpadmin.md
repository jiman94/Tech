```bash 
version: '3'

services:
  # MySQL
  mysql:
    image: mysql:5.7
    container_name: mysql_host
    environment:
        MYSQL_DATABASE: "chicor_db"
        MYSQL_USER: "chicor"
        MYSQL_PASSWORD: "chicor"
        MYSQL_ROOT_PASSWORD: "root"    
    command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
    volumes:
        - "./init/:/docker-entrypoint-initdb.d/"
        - "./mysql/data:/var/lib/mysql"
        - "./mysql/conf/mysqld.conf:/etc/mysql/mysql.conf.d/mysqld.cnf"
    ports:
        - 3306:3306
    networks:
        - mysqlnetwork       

  # phpMyAdmin
  phpmyadmin:
    container_name: test_phpmyadmin
    image: phpmyadmin/phpmyadmin
    environment:
        - PMA_ARBITRARY=1
        - PMA_HOSTS=mysql_host
        - PMA_USER=root
        - PMA_PASSWORD=root
    ports:
        - 8080:80
    networks:
        - mysqlnetwork

  redis-server:
    restart: always
    image: redis:4.0
    container_name: redis-server
    command: /bin/bash -c 'redis-server --appendonly yes'
    sysctls:
        - net.core.somaxconn=65535
    ports:
        - "6380:6379"
    volumes:
        - ./redis:/data
    networks:
        - mysqlnetwork

networks:
    mysqlnetwork:
      driver: bridge
```      
