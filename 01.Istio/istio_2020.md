istio를 사용하게 되면, 먼저 모든 트래픽을 istio를 통해 들어오게 됩니다.

먼저 AWS에서는 ALB를 alb-ingress-controller가 모니터링 하고 있다가 ingress에 대한 배포가 수행되면,
이에 맞게 ALB와 리스너를 구축하고 istio ingress로 모두 매핑시키게 됩니다.

 istio ingress는 istio ingressgateway(Proxy 역할)로 모든 트래픽을 전달하게 되면, istio service mesh로 라우팅하게 됩니다.

따라서 각 service gateway와 virtual service, destination rule에 대한 정의대로 트래픽들을 전달하면서
실제 구축된 서비스까지 트래픽을 전달하고 이 모든 과정을 istio pilot이 모니터링하게 되는 것입니다.

 
# istio를 세팅하도록 하겠습니다.

cd ~/environment

curl -L https://git.io/getLatestIstio | sh -

cd istio-*

sudo mv -v bin/istioctl /usr/local/bin/
 

tiller 계정의 rbac를 아래 명령어를 통해 수정해주세요

kubectl apply -f install/kubernetes/helm/helm-service-account.yaml
 

helm을 통해 istio를 설치하도록 하겠습니다.

helm install install/kubernetes/helm/istio-init --name istio-init --namespace istio-system
 

ALB를 통해 istio를 구성할 것이기 때문에 아래와 같이 수행해주세요.

helm install install/kubernetes/helm/istio --name istio --namespace istio-system --set global.configValidation=false --set sidecarInjectorWebhook.enabled=false --set grafana.enabled=true --set servicegraph.enabled=true --set global.k8sIngressSelector=ingressgateway --set ingress.enable=false --set gateways.enabled=true --set gateways.istio-ingressgateway.type=NodePort
 

아래 명령어로 설치 확인을 할 수 있습니다.

kubectl get crds --namespace istio-system | grep 'istio.io'
kubectl get all -n istio-system
 

이제 ingress를 구성하여 실제 alb가 생성되게 하고 트래픽이 외부에서 들어올 수 있도록 구성하겠습니다.

apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: istio-ingress
  namespace: istio-system
  annotations:
    kubernetes.io/ingress.class: alb
    alb.ingress.kubernetes.io/scheme: internet-facing
    alb.ingress.kubernetes.io/certificate-arn: arn:aws:acm:ap-northeast-2:{account-nubmer}:certificate/{id}
    alb.ingress.kubernetes.io/ssl-policy: ELBSecurityPolicy-2016-08
    alb.ingress.kubernetes.io/listen-ports: '[{"HTTP":80,"HTTPS": 443}]'
    alb.ingress.kubernetes.io/actions.ssl-redirect: '{"Type": "redirect", "RedirectConfig": { "Protocol": "HTTPS", "Port": "443", "StatusCode": "HTTP_301"}}'
    external-dns.alpha.kubernetes.io/hostname: cb-test-api.domain.shop, istio.domain.shop
spec:
  rules:
  - http:
        paths:
          - backend:
              serviceName: ssl-redirect
              servicePort: use-annotation
  - http:
        paths:
          - backend:
              serviceName: istio-ingressgateway
              servicePort: 80
 

alb가 만들어지고 ingress를 통해 istio-ingressgateway로 트래픽을 전달하게 됩니다.

 
 
이제 pilot을 통해 서비스로 트래픽이 전달 되도록 gateway를 만들어 주도록 하겠습니다.

 

그 전에 먼저, istio가 모니터링하고 제어할 수 있도록 namespace 제어권을 설정하겠습니다.

kubectl label namespace prd-api istio-injection=enabled
kubectl get ns -o jsonpath='{range .items[*]}{.metadata.name}{"\t"}{.metadata.labels}{"\t"}{"\n"}{end}'

 

만약 enabled 를 하지 않은 namespace에 대한 제어를 하려면 각 배포 시 다음과 같은 명령어를 수행해야 합니다.

istioctl kube-inject -f my-app.yaml
 

확실하게 하기 위해 deployment가 있는 폴더에서 아래 명령어를 수행해서 나온 파일로 수정해서 다시 배포해주도록 하겠습니다.


 

그리고 서비스 또한 Gateway와 Virtual Service를 통해 트래픽이 전달되기 때문에 ClusterIP형태로 배포해주겠습니다.

apiVersion: v1
kind: Service
metadata:
  name: cb-test-api
  namespace: prd-api
spec:
  ports:
    - port: 9000
      protocol: TCP
  selector:
    app: cb-test-api
 

이제 Gateway와 VitualService를 아래와 같이 작성해서 배포해주세요

apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: cb-test-api-gateway
  namespace: prd-api
spec:
  selector:
    istio: ingressgateway
  servers:
  - port:
      number: 80
      name: http
      protocol: HTTP
    hosts:
    - "cb-test-api.domain.shop"
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: cb-test-api-virtualservice
  namespace: prd-api
spec:
  gateways:
  - cb-test-api-gateway
  hosts:
  - "cb-test-api.domain.shop"
  http:
  - match:
    - uri:
        prefix: /
    route:
    - destination:
        host: cb-test-api
        port:
          number: 9000
 

URL로 접속해보면, 정상적으로 잘 되는것을 볼 수 있습니다. 


자 이제, istio 그라파나에서도 모니터링하는 화면을 보도록 아래와 같이 설정해주세요.

apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: istio-grafana-gateway
  namespace: istio-system
spec:
  selector:
    istio: ingressgateway
  servers:
  - port:
      number: 80
      name: http
      protocol: HTTP
    hosts:
    - "istio.domain.shop"
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: istio-grafana-virtualservice
  namespace: istio-system
spec:
  gateways:
  - istio-grafana-gateway
  hosts:
  - "istio.domain.shop"
  http:
  - match:
    - uri:
        prefix: /
    route:
    - destination:
        host: grafana
        port:
          number: 3000
 

배포 후 url로 접속하게 되면, 실제 request에 대한 Success Rate를 볼 수 있습니다.