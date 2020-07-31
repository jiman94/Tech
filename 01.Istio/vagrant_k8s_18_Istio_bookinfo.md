# 작성일 : 20200731
# Helm 설치 > Istio 설치 > bookinfo 설치 

0. k8s-SingleMaster-18

https://github.com/sysnet4admin/IaC/tree/master/k8s-SingleMaster-18.9_9_w_auto-compl

git clone https://github.com/sysnet4admin/IaC.git

cd /Users/mz03-jmryu/workspace-k8s/20200731/IaC/k8s-SingleMaster-18.9_9_w_auto-compl

vagrant up 

vargrant ssh m-k8s 

mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

1. helm 설치 

curl https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get > get_helm.sh
chmod +x get_helm.sh
./get_helm.sh

kubectl create -f install/kubernetes/helm/helm-service-account.yaml
helm init --service-account tiller

2. 재설치 ( 삭제 )

for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl delete -f $i; done
cd /home/jmryu/istio-1.5.2/samples/bookinfo/platform/kube
./cleanup.sh 

3. Istio 다운로드 

https://istio.io/latest/docs/setup/getting-started/#download

curl -L https://istio.io/downloadIstio | sh -

$ cd istio-1.6.6
$ export PATH=$PWD/bin:$PATH

$ istioctl install --set profile=demo
✔ Istio core installed
✔ Istiod installed
✔ Egress gateways installed
✔ Ingress gateways installed
✔ Addons installed
✔ Installation complete

$ kubectl label namespace default istio-injection=enabled

- 1.5.2 버전으로 다운 받기  
curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.5.2 sh -

helm install install/kubernetes/helm/istio-init --name istio-init --namespace istio-system

curl https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get > get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh


4. Istio CRD 설치 (Custom Resource Definitions)

/home/vagrant/istio-1.5.2

for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl apply -f $i; done

5. Istio 파일생성 

helm template \
--namespace istio-system \
--set tracing.enabled=true \
--set global.mtls.enabled=true \
--set grafana.enabled=true \
--set kiali.enabled=true \
--set servicegraph.enabled=true \
install/kubernetes/helm/istio \
> ./istioFex.yaml

# AWS 일경우 aws-load-balancer-internal

helm template \
--name=istio \
--namespace istio-system \
--set tracing.enabled=true \
--set global.mtls.enabled=false \
--set grafana.enabled=true \
--set kiali.enabled=true \
--set servicegraph.enabled=true \
--set gateways.istio-ingressgateway.serviceAnnotations."service.beta.kubernetes.io/aws-load-balancer-internal"="0.0.0.0/0" \
install/kubernetes/helm/istio \
> ./istioFex.yaml

kubectl apply -f istioFex.yaml
kubectl label namespace default istio-injection=enabled
kubectl get ns --show-labels

kubectl apply -f istioFex.yaml

6. pod 확인 

kubectl get pod --namespace=istio-system

NAME                                    READY   STATUS    RESTARTS   AGE
grafana-b54bb57b9-z4xtv                 1/1     Running   0          29m
istio-egressgateway-94dc79cb6-pqkpv     1/1     Running   0          29m
istio-ingressgateway-6c5c854fbc-7htck   1/1     Running   0          29m
istio-tracing-9dd6c4f7c-k6pxc           1/1     Running   0          29m
istiod-5d8d9dd979-mlc4w                 1/1     Running   0          30m
kiali-d45468dc4-bn2gf                   1/1     Running   0          29m
prometheus-9c4967f7d-92tjb     

7. Sidecar injection 기능 활성화

kubectl label namespace default istio-injection=enabled

[vagrant@m-k8s istio-1.6.6]$ kubectl get ns --show-labels
NAME              STATUS   AGE    LABELS
default           Active   137m   <none>
istio-system      Active   29m    istio-injection=disabled
kube-node-lease   Active   137m   <none>
kube-public       Active   137m   <none>
kube-system       Active   137m   <none>

8. Istio Gateway

kubectl get gateway

9. istio-ingressgateway 

kubectl edit svc istio-ingressgateway -n istio-system

10. describe svc istio-ingressgateway


11. 아이피 확인 

kubectl get svc istio-ingressgateway -n istio-system --show-labels
NAME                   TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)                                                                      AGE   LABELS
istio-ingressgateway   LoadBalancer   10.100.167.157   <pending>     15021:31190/TCP,80:30883/TCP,443:32320/TCP,31400:31567/TCP,15443:32405/TCP   25m   app=istio-ingressgateway,install.operator.istio.io/owning-resource-namespace=istio-system,install.operator.istio.io/owning-resource=installed-state,istio=ingressgateway,operator.istio.io/component=IngressGateways,operator.istio.io/managed=Reconcile,operator.istio.io/version=1.6.6,release=istio
[vagrant@m-k8s istio-1.6.6]$ 

12. 포트 확인

echo $(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')

31380


13. bookinfo 배치

$ kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml
service/details created
serviceaccount/bookinfo-details created
deployment.apps/details-v1 created
service/ratings created
serviceaccount/bookinfo-ratings created
deployment.apps/ratings-v1 created
service/reviews created
serviceaccount/bookinfo-reviews created
deployment.apps/reviews-v1 created
deployment.apps/reviews-v2 created
deployment.apps/reviews-v3 created
service/productpage created
serviceaccount/bookinfo-productpage created
deployment.apps/productpage-v1 created

$ kubectl get services

NAME          TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)    AGE
details       ClusterIP   10.0.0.212      <none>        9080/TCP   29s
kubernetes    ClusterIP   10.0.0.1        <none>        443/TCP    25m
productpage   ClusterIP   10.0.0.57       <none>        9080/TCP   28s
ratings       ClusterIP   10.0.0.33       <none>        9080/TCP   29s
reviews       ClusterIP   10.0.0.28       <none>        9080/TCP   29s

and

$ kubectl get pods
NAME                              READY   STATUS            RESTARTS   AGE
details-v1-78d78fbddf-tj56d       0/2     PodInitializing   0          2m30s
productpage-v1-85b9bf9cd7-zg7tr   0/2     PodInitializing   0          2m29s
ratings-v1-6c9dbf6b45-5djtx       0/2     PodInitializing   0          2m29s
reviews-v1-564b97f875-dzdt5       0/2     PodInitializing   0          2m30s
reviews-v2-568c7c9d8f-p5wrj       1/2     Running           0          2m29s
reviews-v3-67b4988599-7nhwz       0/2     PodInitializing   0          2m29s


$ kubectl exec -it $(kubectl get pod -l app=ratings -o jsonpath='{.items[0].metadata.name}') -c ratings -- curl productpage:9080/productpage | grep -o "<title>.*</title>"

<title>Simple Bookstore App</title>

# Istio gateway:

$ kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml

gateway.networking.istio.io/bookinfo-gateway created
virtualservice.networking.istio.io/bookinfo created

$ istioctl analyze
✔ No validation issues found when analyzing namespace: default.

$ kubectl get svc istio-ingressgateway -n istio-system
NAME                   TYPE           CLUSTER-IP       EXTERNAL-IP     PORT(S)                                      AGE
istio-ingressgateway   LoadBalancer   172.21.109.129   130.211.10.121  80:31380/TCP,443:31390/TCP,31400:31400/TCP   17h


# Istio GATEWAY_URL:

export INGRESS_HOST=$(kubectl get po -l istio=ingressgateway -n istio-system -o jsonpath='{.items[0].status.hostIP}')

export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')

$ export GATEWAY_URL=$INGRESS_HOST:$INGRESS_PORT
$ echo $GATEWAY_URL

$ echo http://$GATEWAY_URL/productpage

http://192.168.1.103:30883/productpage


$ istioctl dashboard kiali


Uninstall

$ istioctl manifest generate --set profile=demo | kubectl delete -f -

$ kubectl delete namespace istio-system


# 참조 

kubectl get all

kubectl get svc,deployment,pods -o wide


[vagrant@m-k8s istio-1.6.6]$ kubectl get svc,deployment,pods -o wide
NAME                  TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)    AGE    SELECTOR
service/details       ClusterIP   10.103.169.188   <none>        9080/TCP   90m    app=details
service/kubernetes    ClusterIP   10.96.0.1        <none>        443/TCP    3h9m   <none>
service/productpage   ClusterIP   10.97.94.255     <none>        9080/TCP   90m    app=productpage
service/ratings       ClusterIP   10.103.201.169   <none>        9080/TCP   90m    app=ratings
service/reviews       ClusterIP   10.97.247.34     <none>        9080/TCP   90m    app=reviews

NAME                             READY   UP-TO-DATE   AVAILABLE   AGE   CONTAINERS    IMAGES                                                    SELECTOR
deployment.apps/details-v1       1/1     1            1           90m   details       docker.io/istio/examples-bookinfo-details-v1:1.16.2       app=details,version=v1
deployment.apps/productpage-v1   1/1     1            1           90m   productpage   docker.io/istio/examples-bookinfo-productpage-v1:1.16.2   app=productpage,version=v1
deployment.apps/ratings-v1       1/1     1            1           90m   ratings       docker.io/istio/examples-bookinfo-ratings-v1:1.16.2       app=ratings,version=v1
deployment.apps/reviews-v1       1/1     1            1           90m   reviews       docker.io/istio/examples-bookinfo-reviews-v1:1.16.2       app=reviews,version=v1
deployment.apps/reviews-v2       1/1     1            1           90m   reviews       docker.io/istio/examples-bookinfo-reviews-v2:1.16.2       app=reviews,version=v2
deployment.apps/reviews-v3       1/1     1            1           90m   reviews       docker.io/istio/examples-bookinfo-reviews-v3:1.16.2       app=reviews,version=v3

NAME                                  READY   STATUS    RESTARTS   AGE   IP               NODE     NOMINATED NODE   READINESS GATES
pod/details-v1-558b8b4b76-f9krh       1/1     Running   0          71m   172.16.103.135   w2-k8s   <none>           <none>
pod/productpage-v1-6987489c74-xrwfd   1/1     Running   0          71m   172.16.103.137   w2-k8s   <none>           <none>
pod/ratings-v1-7dc98c7588-64s69       1/1     Running   0          71m   172.16.221.131   w1-k8s   <none>           <none>
pod/reviews-v1-7f99cc4496-jdkx7       1/1     Running   0          71m   172.16.103.136   w2-k8s   <none>           <none>
pod/reviews-v2-7d79d5bd5d-qndpm       1/1     Running   0          71m   172.16.132.6     w3-k8s   <none>           <none>
pod/reviews-v3-7dbcdcbc56-654hr       1/1     Running   0          71m   172.16.221.132   w1-k8s   <none>           <none>
[vagrant@m-k8s istio-1.6.6]$ 
