
0. 준비 
for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl delete -f $i; done
cd /home/jmryu/istio-1.5.0/samples/bookinfo/platform/kube
./cleanup.sh 


1. Install Istio

curl -L https://git.io/getLatestIstio | sh -
cd istio-1.5.0/

2. Istio CRD 설치 (Custom Resource Definitions)

for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl apply -f $i; done


3. Istio 파일생성 

helm template \
--namespace istio-system \
--set tracing.enabled=true \
--set global.mtls.enabled=true \
--set grafana.enabled=true \
--set kiali.enabled=true \
--set servicegraph.enabled=true \
install/kubernetes/helm/istio \
> ./istioFex.yaml

3. Istio를 k8s 클러스터 위에 올리기 

kubectl apply -f istioFex.yaml

4. 확인 
- pod 상태가 모두 Running또는 Complete라면 성공

kubectl get pod --namespace=istio-system

5. Sidecar injection 기능 활성화
- Istio는 Pod에 envoy proxy를 sidecar 패턴으로 삽입하여, 트래픽을 컨트롤 하는 구조입니다.
- application 서비스들은 default namespace로 올릴 예정이니 해당 namespace에 라벨을 추가해줍니다.

kubectl label namespace default istio-injection=enabled

kubectl get ns --show-labels

6. bookinfo 배치

kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml
kubectl get pod
kubectl get svc


7. Istio Gateway
- 서비스가 전부 ClusterIP로 외부에 노출되어있지 않습니다.

kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml

kubectl get gateway

kubectl get svc istio-ingressgateway -n istio-system --show-labels

- gateway <pending>상태
- 클러스터에서 로드밸런서를 지원하지 않기 때문
- AWS나 Google은 지원한다고 하는데 테스트해보진 않음.)
- 이런 경우에는 ingressgateway를 NodePort로 변경해서 접근하면 됩니다.


8. LoadBalancer -> NodePort 로 변경 
- ingressgateway를 외부에 노출 

jmryu@k8smaster:~/istio-1.5.0$ kubectl edit svc istio-ingressgateway -n istio-system
service/istio-ingressgateway edited
jmryu@k8smaster:~/istio-1.5.0$ 

9. describe svc istio-ingressgateway
- 해결 되지 않음. 

jmryu@k8smaster:~/istio-1.5.0$ kubectl describe svc istio-ingressgateway -n istio-system
Name:                     istio-ingressgateway
Namespace:                istio-system
Labels:                   app=istio-ingressgateway
                          chart=gateways
                          heritage=Tiller
                          istio=ingressgateway
                          release=release-name
Annotations:              kubectl.kubernetes.io/last-applied-configuration:
                            {"apiVersion":"v1","kind":"Service","metadata":{"annotations":{},"labels":{"app":"istio-ingressgateway","chart":"gateways","heritage":"Til...
Selector:                 app=istio-ingressgateway,istio=ingressgateway,release=release-name
Type:                     NodePort
IP:                       10.107.162.180
Port:                     status-port  15020/TCP
TargetPort:               15020/TCP
NodePort:                 status-port  32440/TCP
Endpoints:                <none>
Port:                     http2  80/TCP
TargetPort:               80/TCP
NodePort:                 http2  31380/TCP
Endpoints:                <none>
Port:                     https  443/TCP
TargetPort:               443/TCP
NodePort:                 https  31390/TCP
Endpoints:                <none>
Port:                     tcp  31400/TCP
TargetPort:               31400/TCP
NodePort:                 tcp  31400/TCP
Endpoints:                <none>
Port:                     https-kiali  15029/TCP
TargetPort:               15029/TCP
NodePort:                 https-kiali  31279/TCP
Endpoints:                <none>
Port:                     https-prometheus  15030/TCP
TargetPort:               15030/TCP
NodePort:                 https-prometheus  32033/TCP
Endpoints:                <none>
Port:                     https-grafana  15031/TCP
TargetPort:               15031/TCP
NodePort:                 https-grafana  30581/TCP
Endpoints:                <none>
Port:                     https-tracing  15032/TCP
TargetPort:               15032/TCP
NodePort:                 https-tracing  30184/TCP
Endpoints:                <none>
Port:                     tls  15443/TCP
TargetPort:               15443/TCP
NodePort:                 tls  32204/TCP
Endpoints:                <none>
Session Affinity:         None
External Traffic Policy:  Cluster
Events:
  Type    Reason  Age   From                Message
  ----    ------  ----  ----                -------
  Normal  Type    51s   service-controller  LoadBalancer -> NodePort
jmryu@k8smaster:~/istio-1.5.0$ 

10. 아이피 확인 

jmryu@k8smaster:~/istio-1.5.0$ kubectl get svc istio-ingressgateway -n istio-system --show-labels
NAME                   TYPE       CLUSTER-IP       EXTERNAL-IP   PORT(S)                                                                                                                                      AGE    LABELS
istio-ingressgateway   NodePort   10.107.162.180   <none>        15020:32440/TCP,80:31380/TCP,443:31390/TCP,31400:31400/TCP,15029:31279/TCP,15030:32033/TCP,15031:30581/TCP,15032:30184/TCP,15443:32204/TCP   135m   app=istio-ingressgateway,chart=gateways,heritage=Tiller,istio=ingressgateway,release=release-name
jmryu@k8smaster:~/istio-1.5.0$ 


11. 포트 확인

jmryu@k8smaster:~/istio-1.5.0$ echo $(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
31380
jmryu@k8smaster:~/istio-1.5.0$ 

12. 접속이 되지 않는다. 

10.107.162.180:31380/productpage

13. Apply default destination rules
Istio를 사용하여 Bookinfo 버전 라우팅을 제어하려면 subset이라고 하는 사용가능 버전을 정의해줘야합니다.

# mutual TLS를 활성화하지 않은 경우
$ kubectl apply -f samples/bookinfo/networking/destination-rule-all.yaml

# [선택] mutual TLS를 활성화시킨경우
$ kubectl apply -f samples/bookinfo/networking/destination-rule-all-mtls.yaml

14. Virtual service
- 라운드로빈형식으로 돌아가는 버전을 하나로 고정시키려면,
- Virtual Service를 만들어 라우팅 규칙을 정의해야 합니다.

kubectl apply -f samples/bookinfo/networking/virtual-service-all-v1.yaml

15. Route based on user identity
- 사용자 신원에 따라 라우트시키는 방법

kubectl apply -f samples/bookinfo/networking/virtual-service-reviews-test-v2.yaml

kubectl get pod -n istio-system -l istio=ingressgateway



# 

kubectl label namespace default istio-injection=enabled

kubectl get ns --show-labels

kubectl describe deployment istio-pilot -n istio-system
