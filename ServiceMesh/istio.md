서비스 메쉬란 애플리케이션의 다양한 부분들이 서로 데이터를 공유하는 방식을 제어하는 방법
서비스 간 커뮤니케이션이 마이크로서비스를 가능하게 하는 핵심이다.
서비스 간 커뮤니케이션을 통제하는 로직을 통해 인프라 계층에 추상화
애플리케이션 앞에 프록시 사용
 

 
서비스 메쉬에서는 요청이 자체 인프라 계층의 프록시를 통해 마이크로서비스 간에 라우팅
프록시는 서비스와 함께 실행 되므로 sidecar 라고 한다.
 
 

istio

구조
Envoy: Service 앞단의 Network Layer로 서비스의 인/아웃 트래픽을 컨트롤한다.
Control Plane: 모든 Network Layer를 컨트롤한다.
Pliot: Routing, Service Discovery 등 서비스 간 통신 기능을 담당
Mixer: Metric, Monitoring 등의 기능을 담당
Istio-Auth: 서비스 간 인증을 담당(TLS, Auth 등)
 

동작 방식
Control Plane에 서비스 정보, 라우팅 정보 등을 등록한다.
Control Plane은 등록된 정보를 Envoy에 전달한다.
Envoy 전달받은 정보를 이용하여 서비스 간 통신을 제어한다.
통신 흐름: ServiceA -> EnvoyA-> EnvoyB -> ServiceB
 

주요기능
Service Discovery
Fault Injection
Circuit Breaking
Load Balancing
인스턴스 별로 되는 트래픽양 조절가능(Canary, Bluegreen에 활용가능)
MSA통합된 Metric, Log 수집가능
MSA간 인증, TLS등
 
spring cloud에서도 istio기능을 하는것이 있지만
spring cloud느는 소프트웨어 관점에서 사용하기때문에 유지 보수, 언어에 문제점이 있지만
istio는 인프라 영역에서 동작하기에 언어에 상관 없고 유지 보수가 쉽다
