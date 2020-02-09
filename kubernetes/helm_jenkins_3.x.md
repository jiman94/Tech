# minikube에서 Jenkins 설치

### 1. namespace.yaml 수정

```yaml 
apiVersion: v1
kind: Namespace
metadata:
  name: pipeline
```

metadata > name 의 값을 사용하고자하는 namespace 의 이름으로 변경합니다.

### 2. Namespace 생성

kubectl apply -f namespace.yaml

### 3. Namespace 확인

kubectl get namespace
kubectl describe namespace pipeline

### 1. YAML 파일 가져오기

```yaml 
git clone https://github.com/rudasoft/pipeline.git
cd pipeline
```

### 2. jenkins-pv.yaml 수정

```yaml 
apiVersion: v1
kind: PersistentVolume
metadata:
  name: jenkins-pv
  namespace: pipeline
spec:
  storageClassName: jenkins-pv
  accessModes:
    - ReadWriteOnce
  capacity:
    storage: 20Gi
  persistentVolumeReclaimPolicy: Retain
  hostPath:
    path: /host_mnt/d/data/jenkins/
```

### 3. PersistentVolume 생성


```yaml 
kubectl create -f jenkins-pv.yaml
kubectl get pv --namespace=pipeline
kubectl describe pv jenkins-pv --namespace=pipeline
```

### 4. host 파일 수정

C:\Windows\System32\drivers\etc\hosts
localhost   jenkins.pipeline.internal 

### 5. jenkins-values.yaml 수정
```yarml 
master:
    adminUser: "admin"
    adminPassword: "admin"
```
### 6. Jenkins 설치


```yaml 
helm install jenkins stable/jenkins -f jenkins-values.yaml --namespace=pipeline
kubectl get pods --namespace=pipeline

helm install jenkins stable/jenkins -f jenkins-values.yaml --namespace=pipeline-ns

kubectl get pods --namespace=pipeline-
```

7. Service 생성

```yaml 
kubectl expose deployment jenkins --type=LoadBalancer --name=jenkins-svc --namespace=pipeline

kubectl get svc --namespace=pipeline
```

http://jenkins.pipeline.internal:8080 

