# 작성일 : 20200731
# Helm 설치 > Istio 설치 > bookinfo 설치 

1. k8s-SingleMaster-18

https://github.com/sysnet4admin/IaC/tree/master/k8s-SingleMaster-18.9_9_w_auto-compl

git clone https://github.com/sysnet4admin/IaC.git

cd /Users/mz03-jmryu/workspace-k8s/20200731/IaC/k8s-SingleMaster-18.9_9_w_auto-compl

```java
vagrant up 
vargrant ssh m-k8s 
```


```java
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

2. helm 설치 

https://helm.sh/docs/intro/install/


```java
[vagrant@m-k8s ~]$ curl https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get > get_helm.sh
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  7160  100  7160    0     0   5811      0  0:00:01  0:00:01 --:--:--  5816
[vagrant@m-k8s ~]$ chmod +x get_helm.sh
[vagrant@m-k8s ~]$ ./get_helm.sh
Downloading https://get.helm.sh/helm-v2.16.12-linux-amd64.tar.gz
```


3. Istio 다운로드 

https://istio.io/latest/docs/setup/getting-started/#download


```java
[vagrant@m-k8s ~]$  curl -L https://istio.io/downloadIstio | sh -
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   102  100   102    0     0    121      0 --:--:-- --:--:-- --:--:--   121
100  4277  100  4277    0     0   2864      0  0:00:01  0:00:01 --:--:-- 4176k

Downloading istio-1.7.2 from https://github.com/istio/istio/releases/download/1.7.2/istio-1.7.2-linux-amd64.tar.gz ...


[vagrant@m-k8s istio-1.7.2]$ export PATH=$PWD/bin:$PATH
```

```java
[vagrant@m-k8s istio-1.7.2]$  istioctl x precheck 

[vagrant@m-k8s istio-1.7.2]$ istioctl install --set profile=demo
Detected that your cluster does not support third party JWT authentication. Falling back to less secure first party JWT. See https://istio.io/docs/ops/best-practices/security/#configure-third-party-service-account-tokens for details.
✔ Istio core installed                                                                                                                                                                         
✔ Istiod installed                                                                                                                                                                                                                               
✔ Ingress gateways installed                                                                                                                                                                                                                                                                   
✔ Egress gateways installed                                                                                                                                                                                                                                                                    
✔ Installation complete                                                                                                                                                                                                                                                                   

```

4. pod 확인 

```java
[vagrant@m-k8s ~]$ kubectl get pod --namespace=istio-system
NAME                                    READY   STATUS    RESTARTS   AGE
istio-egressgateway-fbb7dc4f4-8pbkx     1/1     Running   0          16m
istio-ingressgateway-5f84fcdd69-2j4ck   1/1     Running   0          16m
istiod-77df9b78f8-tsbsk                 1/1     Running   0          17m
[vagrant@m-k8s ~]$ 
```

5. Sidecar injection 기능 활성화

```java

kubectl label namespace default istio-injection=enabled

[vagrant@m-k8s ~]$ kubectl get ns --show-labels
NAME              STATUS   AGE   LABELS
default           Active   86m   istio-injection=enabled
istio-system      Active   33m   istio-injection=disabled
kube-node-lease   Active   86m   <none>
kube-public       Active   86m   <none>
kube-system       Active   86m   <none>
[vagrant@m-k8s ~]$ 
```

6. Istio Gateway

kubectl get gateway

7. istio-ingressgateway 수정 

kubectl edit svc istio-ingressgateway -n istio-system

8.  istio-ingressgateway 내용 확인 


kubectl get svc istio-ingressgateway -n istio-system --show-labels

kubectl describe gateway bookinfo-gateway 





9. 아이피 확인 

```java
kubectl get svc istio-ingressgateway -n istio-system --show-labels

[vagrant@m-k8s ~]$ kubectl get svc istio-ingressgateway -n istio-system --show-labels
NAME                   TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)                                                                      AGE   LABELS
istio-ingressgateway   LoadBalancer   10.110.219.201   <pending>     15021:30103/TCP,80:31506/TCP,443:30439/TCP,31400:31299/TCP,15443:32046/TCP   30m   app=istio-ingressgateway,install.operator.istio.io/owning-resource-namespace=istio-system,install.operator.istio.io/owning-resource=installed-state,istio.io/rev=default,istio=ingressgateway,operator.istio.io/component=IngressGateways,operator.istio.io/managed=Reconcile,operator.istio.io/version=1.7.2,release=istio
[vagrant@m-k8s ~]$ 
```

10. 포트 확인

echo $(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')

31506


13. bookinfo 배치

```java
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
```

```java
$ kubectl get pods
NAME                              READY   STATUS            RESTARTS   AGE
details-v1-78d78fbddf-tj56d       0/2     PodInitializing   0          2m30s
productpage-v1-85b9bf9cd7-zg7tr   0/2     PodInitializing   0          2m29s
ratings-v1-6c9dbf6b45-5djtx       0/2     PodInitializing   0          2m29s
reviews-v1-564b97f875-dzdt5       0/2     PodInitializing   0          2m30s
reviews-v2-568c7c9d8f-p5wrj       1/2     Running           0          2m29s
reviews-v3-67b4988599-7nhwz       0/2     PodInitializing   0          2m29s
```

```java
[vagrant@m-k8s ~]$ kubectl exec -it $(kubectl get pod -l app=ratings -o jsonpath='{.items[0].metadata.name}') -c ratings -- curl productpage:9080/productpage | grep -o "<title>.*</title>"
<title>Simple Bookstore App</title>
```

# Istio GATEWAY_URL:

```java
[vagrant@m-k8s ~]$ export INGRESS_HOST=$(kubectl get po -l istio=ingressgateway -n istio-system -o jsonpath='{.items[0].status.hostIP}')
[vagrant@m-k8s ~]$ export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
[vagrant@m-k8s ~]$ echo "$GATEWAY_URL"
192.168.1.102:31506

[vagrant@m-k8s ~]$ export GATEWAY_URL=$INGRESS_HOST:$INGRESS_PORT


[vagrant@m-k8s ~]$  echo http://"$GATEWAY_URL/productpage"
http://192.168.1.102:31506/productpage

[vagrant@m-k8s ~]$ kubectl -n istio-system get service istio-ingressgateway -o jsonpath
error: template format specified but no template given
[vagrant@m-k8s ~]$ kubectl -n istio-system get service istio-ingressgateway 
NAME                   TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)                                                                      AGE
istio-ingressgateway   LoadBalancer   10.110.219.201   <pending>     15021:30103/TCP,80:31506/TCP,443:30439/TCP,31400:31299/TCP,15443:32046/TCP   24m
[vagrant@m-k8s ~]$ 

```



```java
kubectl get gateway
kubectl edit gateway bookinfo-gateway 


kubectl apply -f samples/bookinfo/networking/destination-rule-all.yaml

kubectl get destinationrules -o yaml

```


# cleanup 
```java
samples/bookinfo/platform/kube/cleanup.sh
```


$ istioctl dashboard kiali


Uninstall

$ istioctl manifest generate --set profile=demo | kubectl delete -f -

$ kubectl delete namespace istio-system


# 참조 

kubectl get all

kubectl get svc,deployment,pods -o wide




kubectl apply -f ./samples/addons/kiali.yaml
https://kiali.io/documentation/latest/quick-start/#_install_via_istio_addons


https://istio.io/latest/docs/ops/integrations/kiali/


[vagrant@m-k8s istio-1.7.2]$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.7/samples/addons/grafana.yaml
serviceaccount/grafana created
configmap/grafana created
service/grafana created
deployment.apps/grafana created
configmap/istio-grafana-dashboards created
configmap/istio-services-grafana-dashboards created
[vagrant@m-k8s istio-1.7.2]$ 
[vagrant@m-k8s istio-1.7.2]$ 
[vagrant@m-k8s istio-1.7.2]$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.7/samples/addons/jaeger.yaml
deployment.apps/jaeger created
service/tracing created
service/zipkin created
[vagrant@m-k8s istio-1.7.2]$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.7/samples/addons/prometheus.yaml
serviceaccount/prometheus created
configmap/prometheus created
clusterrole.rbac.authorization.k8s.io/prometheus created
clusterrolebinding.rbac.authorization.k8s.io/prometheus created
service/prometheus created
deployment.apps/prometheus created
[vagrant@m-k8s istio-1.7.2]$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.7/samples/addons/extras/zipkin.yaml
deployment.apps/zipkin created
service/tracing configured
service/zipkin configured
[vagrant@m-k8s istio-1.7.2]$ 



[vagrant@m-k8s istio-1.7.2]$ kubectl get pod -n istio-system
NAME                                    READY   STATUS    RESTARTS   AGE
grafana-75b5cddb4d-ts5h6                1/1     Running   0          5m34s
istio-egressgateway-fbb7dc4f4-8pbkx     1/1     Running   1          4h37m
istio-ingressgateway-5f84fcdd69-2j4ck   1/1     Running   1          4h37m
istiod-77df9b78f8-tsbsk                 1/1     Running   1          4h38m
jaeger-5795c4cf99-78nkg                 1/1     Running   0          5m20s
kiali-6c49c7d566-mxzbm                  1/1     Running   0          24m
prometheus-9d5676d95-m82f2              2/2     Running   0          5m3s
zipkin-556c4d54f5-nvmwp                 1/1     Running   0          4m44s
[vagrant@m-k8s istio-1.7.2]$ 


kubectl logs -f istio-ingressgateway-5f84fcdd69-2j4ck -n istio-system




[vagrant@m-k8s istio-1.7.2]$ istioctl proxy-status
NAME                                                   CDS        LDS        EDS        RDS          ISTIOD                      VERSION
details-v1-558b8b4b76-bfclf.default                    SYNCED     SYNCED     SYNCED     SYNCED       istiod-77df9b78f8-tsbsk     1.7.2
istio-egressgateway-fbb7dc4f4-8pbkx.istio-system       SYNCED     SYNCED     SYNCED     NOT SENT     istiod-77df9b78f8-tsbsk     1.7.2
istio-ingressgateway-5f84fcdd69-2j4ck.istio-system     SYNCED     SYNCED     SYNCED     SYNCED       istiod-77df9b78f8-tsbsk     1.7.2
productpage-v1-6987489c74-lt47h.default                SYNCED     SYNCED     SYNCED     SYNCED       istiod-77df9b78f8-tsbsk     1.7.2
ratings-v1-7dc98c7588-kz7zz.default                    SYNCED     SYNCED     SYNCED     SYNCED       istiod-77df9b78f8-tsbsk     1.7.2
reviews-v1-7f99cc4496-z8k5m.default                    SYNCED     SYNCED     SYNCED     SYNCED       istiod-77df9b78f8-tsbsk     1.7.2
reviews-v2-7d79d5bd5d-dp98z.default                    SYNCED     SYNCED     SYNCED     SYNCED       istiod-77df9b78f8-tsbsk     1.7.2
reviews-v3-7dbcdcbc56-5q5g6.default                    SYNCED     SYNCED     SYNCED     SYNCED       istiod-77df9b78f8-tsbsk     1.7.2
[vagrant@m-k8s istio-1.7.2]$ istioctl analyze --all-namespaces
Warn [IST0102] (Namespace kiali-operator) The namespace is not enabled for Istio injection. Run 'kubectl label namespace kiali-operator istio-injection=enabled' to enable it, or 'kubectl label namespace kiali-operator istio-injection=disabled' to explicitly mark it as not needing injection
Warn [IST0102] (Namespace kube-node-lease) The namespace is not enabled for Istio injection. Run 'kubectl label namespace kube-node-lease istio-injection=enabled' to enable it, or 'kubectl label namespace kube-node-lease istio-injection=disabled' to explicitly mark it as not needing injection
Info [IST0118] (Service grafana.istio-system) Port name service (port: 3000, targetPort: 3000) doesn't follow the naming convention of Istio port.
Error: Analyzers found issues when analyzing all namespaces.
See https://istio.io/docs/reference/config/analysis for more information about causes and resolutions.
[vagrant@m-k8s istio-1.7.2]$ 
