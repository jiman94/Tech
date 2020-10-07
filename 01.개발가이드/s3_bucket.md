#! /usr/bin/env bash
AWS_ACCESS_KEY_ID='***'
AWS_SECRET_ACCESS_KEY='***'
REGION='us-east-1' 
aws s3 mb "s3://$1"
aws s3 cp "s3://frombucket/testfile.txt" "s3://$1/testfile.txt"
