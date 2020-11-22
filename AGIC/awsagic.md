AWS AGIC
1. Cloud9 (aws 작업 ui 제공)
create name eksworkshop
t2.small
vpc
subnet : public 환경 이어야함
2. EC2
cloud9 ec2 우클릭 -> 인스턴스 설정 -> iam role -> eksworkshop-admin
3. Cloud9
우측 상단 설정표시 -> AWS SETTING -> AWS managed tempororay credentials turn off -> close Preferences
cloud9-setting
command
josm@mz.co.kr:~/environment $ rm -vf ${HOME}/.aws/credentials
josm@mz.co.kr:~/environment $ aws sts get-caller-identity
{
    "UserId": "AROAVW5LNXYSLL4XEW4EP:i-020b9298cc723718d",
    "Account": "392812150308",
    "Arn": "arn:aws:sts::392812150308:assumed-role/eksworkshop-admin/i-020b9298cc723718d"
}
josm@mz.co.kr:~/environment $ sudo curl --silent --location -o /usr/local/bin/kubectl \
>  https://amazon-eks.s3-us-west-2.amazonaws.com/1.14.6/2019-08-22/bin/linux/amd64/kubectl
josm@mz.co.kr:~/environment $  sudo chmod +x /usr/local/bin/kubectl
josm@mz.co.kr:~/environment $ sudo yum -y install jq gettext bash-completion
josm@mz.co.kr:~/environment $ curl --silent --location \
>  "https://github.com/weaveworks/eksctl/releases/download/latest_release/eksctl_$(uname -s)_amd64.tar.gz" \
>   | tar xz -C /tmp
josm@mz.co.kr:~/environment $   
josm@mz.co.kr:~/environment $ sudo mv -v /tmp/eksctl /usr/local/bin
‘/tmp/eksctl’ -> ‘/usr/local/bin/eksctl’
josm@mz.co.kr:~/environment $ eksctl version
0.31.0-rc.0
josm@mz.co.kr:~/environment $ cd ~/environment/
josm@mz.co.kr:~/environment $ git clone https://github.com/aws-samples/amazon-apigateway-ingress-controller-blog.git
eksctl create cluster --name=eksworkshop-blog  --nodes=3 --region=ap-northeast-2 #약 15분 오래걸림 eks cluster 생성
josm@mz.co.kr:~/environment $ kubectl get nodes
NAME                                                STATUS   ROLES    AGE     VERSION
ip-192-168-26-8.ap-northeast-2.compute.internal     Ready    <none>   3m53s   v1.17.11-eks-cfdc40
ip-192-168-39-75.ap-northeast-2.compute.internal    Ready    <none>   3m57s   v1.17.11-eks-cfdc40
ip-192-168-88-189.ap-northeast-2.compute.internal   Ready    <none>   3m54s   v1.17.11-eks-cfdc40
mz.co.kr:~/environment $ eksctl get nodegroup --cluster eksworkshop-eksctl

josm@mz.co.kr:~/environment $ aws configure set region ap-northeast-2

josm@mz.co.kr:~/environment $ aws cloudformation describe-stack-resources --stack-name $STACK_NAME | jq -r '.StackResources[] | select(.ResourceType=="AWS::IAM::Role") | .PhysicalResourceId'
eksctl-eksworkshop-blog-nodegroup-NodeInstanceRole-14UKVCUSKVDUG
josm@mz.co.kr:~/environment $ ROLE_NAME=$(aws cloudformation describe-stack-resources --stack-name $STACK_NAME | jq -r '.StackResources[] | select(.ResourceType=="AWS::IAM::Role") | .PhysicalResourceId')
josm@mz.co.kr:~/environment $ aws iam get-role --role-name $ROLE_NAME | jq -r  .Role.Arn
arn:aws:iam::392812150308:role/eksctl-eksworkshop-blog-nodegroup-NodeInstanceRole-14UKVCUSKVDUG
josm@mz.co.kr:~/environment $ vi amazon-apigateway-ingress-controller-blog//apigw-ingress-controller-blog/kube2iam-ingress-trust-policy.yml 
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    },
    {
      "Sid": "",
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::392812150308:role/eksctl-eksworkshop-blog-nodegroup-NodeInstanceRole-14UKVCUSKVDUG" # 위 출력물 삽입
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
josm@mz.co.kr:~/environment $ cd amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog
aws iam create-role --role-name kube2iam-ingress-role \
 --assume-role-policy-document file://kube2iam-ingress-trust-policy.yml

aws iam attach-role-policy --role-name kube2iam-ingress-role --policy-arn \
 arn:aws:iam::aws:policy/AutoScalingFullAccess  
aws iam attach-role-policy --role-name kube2iam-ingress-role --policy-arn \
 arn:aws:iam::aws:policy/AmazonAPIGatewayAdministrator
aws iam attach-role-policy --role-name kube2iam-ingress-role --policy-arn \
 arn:aws:iam::aws:policy/AmazonVPCFullAccess 
aws iam attach-role-policy --role-name kube2iam-ingress-role --policy-arn \
 arn:aws:iam::aws:policy/AWSCloudFormationFullAccess 
aws iam attach-role-policy --role-name kube2iam-ingress-role --policy-arn \
 arn:aws:iam::aws:policy/ElasticLoadBalancingFullAccess

cd ~/environment
curl https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get > get_helm.sh
chmod +x get_helm.sh
./get_helm.sh

cd -
kubectl apply -f helm-rbac.yml
helm init --service-account tiller

helm install \
  --set rbac.create=true \
  --set host.iptables=true \
  --set host.interface=eni+ \
  --set=extraArgs.auto-discover-base-arn= \
  stable/kube2iam

josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ kubectl get pods
NAME                            READY   STATUS    RESTARTS   AGE
vehement-snail-kube2iam-9pkws   1/1     Running   0          17s
vehement-snail-kube2iam-gtqq4   1/1     Running   0          17s
vehement-snail-kube2iam-lzftw   1/1     Running   0          17s

josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ kubectl logs vehement-snail-kube2iam-9pkws
time="2020-10-29T03:58:33Z" level=info msg="base ARN autodetected, arn:aws:iam::392812150308:role/"
time="2020-10-29T03:58:33Z" level=info msg="Listening on port 8181"
josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ kubectl logs vehement-snail-kube2iam-gtqq4
time="2020-10-29T03:58:32Z" level=info msg="base ARN autodetected, arn:aws:iam::392812150308:role/"
time="2020-10-29T03:58:32Z" level=info msg="Listening on port 8181"
josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ kubectl logs vehement-snail-kube2iam-lzftw
time="2020-10-29T03:58:32Z" level=info msg="base ARN autodetected, arn:aws:iam::392812150308:role/"
time="2020-10-29T03:58:32Z" level=info msg="Listening on port 8181"
josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ 

cd apigw-ingress-controller-blog/AmazonAPIGWHelmChart
helm install --debug ./amazon-apigateway-ingress-controller \
 --set image.repository="karthikk296d/aws-apigw-ingress-controller"

kubectl get pods
 NAME                                           READY   STATUS    RESTARTS   AGE
ranting-mite-amzn-apigw-ingress-controller-0   1/1     Running   0          34s
vehement-snail-kube2iam-9pkws                  1/1     Running   0          117m
vehement-snail-kube2iam-gtqq4                  1/1     Running   0          117m
vehement-snail-kube2iam-lzftw                  1/1     Running   0          117m


kubectl apply -f book-deployment.yml
kubectl apply -f book-service.yml 
kubectl apply -f author-deployment.yml
kubectl apply -f author-service.yml 

josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ kubectl get pods
NAME                                           READY   STATUS    RESTARTS   AGE
author-deployment-f6db476d4-rzjfs              1/1     Running   0          32s
book-deployment-6575446c9d-wc2tp               1/1     Running   0          33s
ranting-mite-amzn-apigw-ingress-controller-0   1/1     Running   0          2m5s
vehement-snail-kube2iam-9pkws                  1/1     Running   0          119m
vehement-snail-kube2iam-gtqq4                  1/1     Running   0          119m
vehement-snail-kube2iam-lzftw                  1/1     Running   0          119m

josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ kubectl get svc
NAME                                                         TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)   AGE
authorservice                                                ClusterIP   10.100.147.191   <none>        80/TCP    33s
bookservice                                                  ClusterIP   10.100.99.200    <none>        80/TCP    52s
kubernetes                                                   ClusterIP   10.100.0.1       <none>        443/TCP   5h6m
ranting-mite-amzn-apigw-ingress-controller-manager-service   ClusterIP   10.100.163.89    <none>        443/TCP   2m24

josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ aws iam create-user --user-name apigw-user
{
    "User": {
        "Path": "/",
        "UserName": "apigw-user",
        "UserId": "AIDAVW5LNXYSN6XEEBK25",
        "Arn": "arn:aws:iam::392812150308:user/apigw-user",
        "CreateDate": "2020-10-29T05:58:32Z"
    }
}
vi api_ingress.yml

apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  annotations:
    apigateway.ingress.kubernetes.io/client-arns: arn:aws:iam::392812150308:user/apigw-user
    apigateway.ingress.kubernetes.io/stage-name: prod
    kubernetes.io/ingress.class: apigateway
  name: api-95d8427d
  namespace: default
spec:
  rules:
  - http:
      paths:
      - backend:
          serviceName: bookservice
          servicePort: 80
        path: /api/book
      - backend:
          serviceName: authorservice
          servicePort: 80
        path: /api/author

kubectl apply -f api_ingress.yml

josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ kubectl get ingress
NAME           HOSTS   ADDRESS   PORTS   AGE
api-95d8427d   *                 80      19s

josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ kubectl get pods
NAME                                           READY   STATUS    RESTARTS   AGE
api-95d8427d-reverse-proxy-667976bd48-84stf    1/1     Running   0          35s
api-95d8427d-reverse-proxy-667976bd48-j2rsz    1/1     Running   0          35s
api-95d8427d-reverse-proxy-667976bd48-lzg94    1/1     Running   0          35s
author-deployment-f6db476d4-rzjfs              1/1     Running   0          4m46s
book-deployment-6575446c9d-wc2tp               1/1     Running   0          4m47s
ranting-mite-amzn-apigw-ingress-controller-0   1/1     Running   0          6m19s
vehement-snail-kube2iam-9pkws                  1/1     Running   0          123m
vehement-snail-kube2iam-gtqq4                  1/1     Running   0          123m
vehement-snail-kube2iam-lzftw                  1/1     Running   0          123m

kubectl logs ranting-mite-amzn-apigw-ingress-controller-0 -f

josm@mz.co.kr:~/environment/amazon-apigateway-ingress-controller-blog/apigw-ingress-controller-blog (master) $ aws iam create-access-key --user-name apigw-user
{
    "AccessKey": {
        "UserName": "apigw-user",
        "AccessKeyId": "AKIAVW5LNXYSAV2P7N2C",
        "Status": "Active",
        "SecretAccessKey": "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",
        "CreateDate": "2020-10-29T06:26:29Z"
    }
}

local$ aws configure --profile apigw-user
AWS Access Key ID [None]: AKIAVW5LNXYSAV2P7N2C
AWS Secret Access Key [None]: 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
Default region name [None]: ap-northeast-2
Default output format [None]: json

mz01-josm@MZ01-JOSM Desktop % aws sts get-session-token
{
    "Credentials": {
        "AccessKeyId": "ASIAVW5LNXYSOGN5MLLY",
        "SecretAccessKey": "5AzbeU9qenpAFQzgZ6/L/B1301uSyUQRXbzfmZbu",
        "SessionToken": "IQoJb3JpZ2luX2VjEOj//////////wEaDmFwLW5vcnRoZWFzdC0yIkcwRQIhALQ1deRGHzCv2Z4OuYZNvrZ4h6IM/cVXoPa2rwnjm7RQAiB68kq9u6/on+DIo/jV0MoLB4IKNF70T6KeTyu1utQhyirrAQhBEAEaDDM5MjgxMjE1MDMwOCIM1eaPoUs1+n1ferU/KsgBZJelTU4eUKRrG/nYkRg1MexgHkyh6W0gjekOlC5jQmRhcT+uoKOk7zbHgiO57yzLt5y5ovA1ZN3dd24AT50pAMosaAKu0x89kMyvqPB77XFwT7Iczo9geQRHLI59KDzETlrWwHettysn7ATpU9zhOvpZ7w86VgvIP+lfUmqozCH32WCyCacq529iTUF05BdwdXeC4kgbf7+m0/6c6l7+VbsAJ+10bs5DylAfOrIjcjHK9MZDTv5N8CsVpKE6V+qFnmSjLXV4h2swp/bp/AU6mAGSA/hQY95XRgHJp6V22PxPorGq11L1iFtx20OfWBA21ohsP1T5O7IxEFp8TRSjNpwkO6z3GBPM4Bg+TMuCAl7rJpRNVL+MrY6QuBcZgQnKimF3US7t98CMdDlasbBFhzYW0+a73WvfteK/3nEm4oAnLlrLtXWZWsFO81WUxqDdsvsT5eA/B9nHuD1BAmeV9v4Qxr9vdJKI1g==",
        "Expiration": "2020-10-29T20:19:51+00:00"
    }
}
Clear 방안