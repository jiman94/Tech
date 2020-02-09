Envoy Proxy 소개

https://www.envoyproxy.io/
https://www.envoyproxy.io/docs/envoy/latest/start/start

should be easy to determine the source of the problem."

“네트워크는 애플리케이션에 투명해야(transparent) 합니다. 네트워크나 애플리케이션에 문제가 발생했을때 문제의 원인을 쉽게 파악할수 있어야 합니다.”


#### envoy 주요 기능
L7 프록시가 주요 기능이지만 핵심 부분은 L3/L4 프록시입니다.

HTTP L7 필터 레이어를 제공합니다.

HTTP/2 를 기본 지원합니다. 물론, HTTP/1.1도 지원합니다.

HTTP L7 라우팅을 지원합니다.

gRPC를 지원합니다.

MongoDB L7을 지원합니다.

DynamoDB L7을 지원합니다.

서비스 디스커버리(Service Discovery)와 설정을 동적으로 변경하는 것이 가능합니다.

헬스 체킹 지원.

자동 재시도, 서킷브레이킹, 전역 비율 제한, 요청 셰도잉, 이상 탐지 등을 제공합니다.

프론트/엣지 프록시 지원

관리용으로 다양한 통계 정보 제공.

