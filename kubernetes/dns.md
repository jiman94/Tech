쿠버네티스 DNS(kubernetes dns)

spec에 hostname과 subdomain을 지정했습니다.

apiVersion: apps/v1
kind: Deployment
metadata:
  name: kubernetes-simple-app
  labels:
    app: kubernetes-simple-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: kubernetes-simple-app
  template:
    metadata:
      labels:
        app: kubernetes-simple-app
    spec:
      hostname: appname
      subdomain: default-subdomain
      containers:
      - name: kubernetes-simple-app
        image: arisu1000/simple-container-app:latest
        ports:
        - containerPort: 8080


$ kubectl apply -f dns-deployment.yaml
$ kubectl get pods -o wide

$ kubectl exec kubernetes-simple-app-xxx nslookup appname.default-subdomain.default.svc.cluster.local

dns-test.yaml
apiVersion: v1
kind: Pod
metadata:
  namespace: default
  name: dns-test
spec:
  containers:
    - name: dns-test
      image: pilot/simple-container-app:latest
  dnsPolicy: ClusterFirst
  dnsConfig:
    nameservers:
      - 8.8.8.8
    searches:
      - default.svc.cluster.local
      - example.com
    options:
      - name: name01
        value: value01
      - name: name02

$ kubectl apply -f addon/dnsconfig-pod.yaml

$ kubectl exec dns-test cat /etc/resolv.conf
