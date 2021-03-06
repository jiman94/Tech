# istio

Google, Lyft, IBM에서 내놓은 서비스 메쉬(Service Mesh) 솔루션입니다.
서비스 메쉬는 마이크로서비스아키텍처(MSA) 구조에서 각 서비스들간의 트래픽을 제어하는 역할을 합니다.

### istio 설치

kubectl apply -f install/kubernetes/istio-demo.yaml

kubectl get pods -n istio-system

### istio의 기본 컴포넌트들은 controlZ 웹 화면

kubectl로 9876포트를 포트포워드 걸어두고 웹으로 접속하면 관련 화면이 보인다.

kubectl port-forward -n istio-system pods/istio-citadel-xxxx 9876:9876

https://github.com/istio/istio/releases 

./bin/istioctl proxy-status

./bin/istioctl proxy-config clusters -n istio-system istio-ingressgateway-xxx.istio-system

 

### 주요 기능
서비스간의 트래픽이나 API 호출을 컨트롤

통신사이의 트래픽을 암호화하고 인증과 권한제어가 가능함.

트래픽 정책이나 자원 제어

서비스들에 대한 tracing, monitoring, logging을 자동으로 수행

### 아키텍처

 

이스티오의 구조는 논리적으로 data plane과 control plane 두 개의 영역으로 구분되어 있습니다.

data plane : 실제 데이터 트래픽이 돌아다니는 영역.

control plane : 트래픽 경로를 설정하고 관리하는 용도로 사용. Mixer의 정책을 설정하고 텔레메트리를 수집하는 용도로 사용.

 

### 주요 구성 요소
####  Envoy

lyft사에서 개발한 C++로 개발된 고성능 프록시. 이스티오에서는 엔보이의 확장판을 사용합니다.

이스티오를 사용해서 드나드는 모든 트래픽은 envoy를 거치게 됩니다. 이스티오는 엔보이의 다음 기능을 많이 활용합니다.

동적 서비스 디스커버리

로드밸런싱

TLS 인증서 처리

HTTP/2, gRPC 프록시

서킷브레이커

헬스체크

트래픽을 스테이지 단계별로 나눠서 보내는 기능(Staged rollouts with %-based traffic split)

실패 삽입

풍부한 메트릭

쿠버네티스 pod에 사이드카 형태로 추가되어서 배포됩니다. 배포되고 나면 믹서(mixer)에서 활용가능한 다양한 통계 정보를 제공해 줍니다.

사이드카 형태이기 때문에 기존 컨테이너나 코드를 재작성할 필요없이 그대로 이용할 수 있습니다.

 

### Mixer

Mixer는 플랫폼 독립적인 컴포넌트입니다. 전체 서비스메쉬에서 접근 정책을 관리하고 엔보이 프록시와 다른 서비스들에서 모니터링 데이터를 수집합니다.

 

### Pilot

Pilot은 엔보이에 대한 서비스 디스커버리, 라우팅(A/B 테스트, 카나리 배포)용 트래픽 관리 기능, 타임아웃, 재시도, 서킷브레이커 같은 기능들을 제공합니다.

istio용 라우팅 설정을 엔보이 전용 설정으로 변경해서 넣고 실행중인 사이드카에 그걸 적용시키는 역할을 합니다. 


 
이렇게 envoy 설정하는걸 추상화 시켜놨기 때문에 istio는 하나의 설정으로 kubernetes, consol, nomad같은 다양한 환경에서 사용할 수 있습니다.

 

### Citadel

citadel은 내장된 인증 관리를 이용해서 서비스간, 엔드유저간 인증을 강화하는 역할을 합니다.

citadel을 이용해서 누가 서비스에 접근할수 있는지를 제어할 수 있습니다.

 

### Galley

istio의 설정을 validation, ingestion, processing, distribution 하는 역할을 합니다. 



### exec
```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: test-route
spec:
  hosts:
  - "*"
  gateways:
  - my-gateway
  http:
  - match:
    - uri:
        prefix: "/"
    route:
    - destination:
        host: nginx.default.svc.cluster.local

apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: my-gateway
  namespace: default
spec:
  selector:
    app: istio-ingressgateway
  servers:
  - port:
      number: 80
      name: http
      protocol: HTTP
    hosts:
    - "*"
```
$ kubectl get pods --all-namespaces -o jsonpath='{range .items[*]}{.metadata.name}{"\t"}{.metadata.labels.app}{"\n"}{end}' | grep ingressgateway
istio-ingressgateway-xxxx    istio-ingressgateway
 
kubectl run nginx --image=nginx --port=80
kubectl expose deploy nginx --port 30080 --target-port 80

kubectl get svc istio-ingressgateway -n istio-system

kubectl get svc -n istio-system

kubectl exec -n istio-system -it istio-ingressgateway-xxxbash

