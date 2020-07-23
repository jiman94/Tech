#! /bin/bash

docker build -t pilot_dev_mobile .

docker run --name pilot-mobile -p 80:80 -d pilot_dev_mobile
