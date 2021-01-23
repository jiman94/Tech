# istio 개념정리

## Concepts

### Istio 란

클라우드 플랫폼은 클라우드 플랫폼을 사용하는 조직에 다양한 이점을 제공합니다. 그러나 클라우드를 채택하면 개발자 팀에 긴장감을 줄 수 있다는 사실을 부인할 수 없습니다. 개발자는 이식성을 위해 마이크로 서비스를 사용해야 하며 운영자는 초대형 하이브리드 및 다중 클라우드 배포를 관리합니다. Istio 를 사용하면 서비스를 연결(connect), 보안(secure), 제어(control) 및 관찰(observe) 할 수 있습니다.

높은 수준에서 Istio 는 이러한 배포의 복잡성을 줄이고 개발 팀의 부담을 덜어줍니다. 기존 분산 응용 프로그램에 투명하게 레이어하는 완전 개방형 소스 서비스 메쉬입니다. 또한 로깅 플랫폼이나 원격 측정 또는 정책 시스템에 통합 할 수 있는 API 를 포함한 플랫폼입니다. Istio 의 다양한 기능 세트를 사용하면 분산 마이크로 서비스 아키텍처를 성공적으로 효율적으로 실행할 수 있으며 마이크로 서비스의 보안, 연결 및 모니터링을 위한 일관된 방법을 제공합니다.

#### 서비스 메쉬란

Istio 는 모놀리식 응용 프로그램이 분산 마이크로 서비스 아키텍처로 전환함에 따라 개발자와 운영자가 직면한 문제를 해결합니다. 방법을 확인하려면 Istio 의 서비스 메쉬를 자세히 살펴 보는 것이 좋습니다.

서비스 메쉬라는 용어는 이러한 애플리케이션을 구성하는 마이크로 서비스 네트워크와 그 사이의 상호 작용을 설명하는 데 사용됩니다. 서비스 메쉬가 크기와 복잡성이 커짐에 따라 이해와 관리가 어려워 질 수 있습니다. 요구 사항에는 검색, 로드밸런싱, 장애 복구, 메트릭 및 모니터링이 포함될 수 있습니다. 서비스 메쉬에는 A/B 테스트, 카나리 릴리스, 속도 제한, 액세스 제어 및 종단 간 인증과 같은 더욱 복잡한 운영 요구 사항이 있는 경우가 많습니다.

Istio 는 서비스 메쉬 전체에 대한 행동 통찰력과 작동 제어를 제공하여 마이크로 서비스 애플리케이션의 다양한 요구 사항을 충족시키는 완벽한 솔루션을 제공합니다.

#### Istio 를 왜 사용하는가

Istio 를 사용하면 서비스 코드를 변경하지 않고도 로드밸런싱, 서비스 대 서비스 인증, 모니터링 등을 통해 배포된 서비스 네트워크를 쉽게 만들 수 있습니다. 사용자 환경 전체에 특별한 사이드카 프록시를 배치하여 마이크로 서비스 간의 모든 네트워크 통신을 가로채는 Istio 지원을 추가 한 다음 다음과 같은 컨트롤 플레인 기능을 사용하여 Istio 를 구성하고 관리합니다.

- HTTP, gRPC, WebSocket 및 TCP 트래픽에 대한 자동 로드밸런싱
- 풍부한 라우팅 규칙, 재시도, 페일 오버 및 오류 삽입을 통해 트래픽 동작을 세부적으로 제어합니다.
- 액세스 제어, 속도 제한 및 할당량을 지원하는 플러그 가능한 정책 계층 및 구성 API
- 클러스터 입구 및 출구를 포함하여 클러스터 내의 모든 트래픽에 대한 자동 메트릭, 로그 및 추적
- 강력한 ID 기반 인증 및 권한 부여를 통해 클러스터에서 서비스 간 통신을 보안합니다.

Istio 는 확장성을 염두에 두고 설계 되었으며 다양한 배치 요구를 충족시킵니다.

#### 핵심 기능

Istio 는 서비스 네트워크 전반에 걸쳐 여러 가지 주요 기능을 균일하게 제공합니다.

##### 트래픽 관리 (Traffic management)

> 서비스 간 트래픽 및 API 호출의 흐름을 지능적으로 제어하고 다양한 테스트를 수행하며 적색/검정색 배포로 점진적으로 업그레이드합니다.

Istio 의 쉬운 규칙 구성 및 트래픽 라우팅을 통해 서비스 간의 트래픽 및 API 호출 흐름을 제어 할 수 있습니다. Istio 는 회로 차단기, timeout 및 재시도와 같은 서비스 수준 속성 구성을 단순화하고 A/B 테스트, Canary 롤아웃 및 백분율 기반 트래픽 분할로 단계적 출시와 같은 중요한 작업을 설정하는 데 편리합니다.

트래픽 및 가시적인 장애 복구 기능을 보다 잘 파악할 수 있으므로 문제가 발생하기 전에 문제를 파악하고 보다 안정적으로 호출할 수 있으며 네트워크 상황에 관계없이 네트워크를 보다 강력하게 사용할 수 있습니다.

##### 보안 (Security)

> 서비스 간의 관리된 인증, 권한 부여 및 암호화를 통해 서비스를 자동으로 보호합니다.

Istio 의 보안 기능 덕분에 개발자는 응용 프로그램 수준의 보안에 집중할 수 있습니다. Istio 는 기본 보안 통신 채널을 제공하고 규모에 따른 서비스 통신의 인증, 권한 부여 및 암호화를 관리합니다. Istio 를 사용하면 기본적으로 서비스 통신이 보호되므로 다양한 프로토콜과 런타임에서 일관되게 정책을 시행 할 수 있습니다.

Istio 는 Kubernetes (또는 인프라) 네트워크 정책과 함께 플랫폼 독립적인 반면, 네트워크 및 응용 프로그램 계층에서 포드 간 (pod-to-pod) 또는 서비스 간 (service-to-service) 통신을 보호하는 기능을 포함하여 훨씬 더 큰 이점이 있습니다.

##### 감시 (Observability)

Istio 의 강력한 추적, 모니터링 및 로깅은 서비스 메쉬 배포에 대한 깊은 통찰력을 제공합니다. Istio 의 모니터링 기능을 통해 서비스 성능이 업스트림 및 다운 스트림에 어떻게 영향을 미치는지, 그리고 사용자 정의 대시 보드는 모든 서비스 성능에 대한 가시성을 제공하고 그 성능이 다른 프로세스에 어떤 영향을 미치는지 볼 수 있도록 합니다.

Istio 의 Mixer 구성 요소는 정책 제어 및 원격 측정 수집을 담당합니다. 백엔드 추상화 및 중개, Istio 의 나머지 부분을 개별 인프라 백엔드의 구현 세부 사항으로부터 격리 시키며 운영자에게 메쉬와 인프라 백엔드 간의 모든 상호 작용에 대한 세분화 된 제어권을 제공합니다.

##### 플랫폼 지원 (Platform support)

Istio 는 플랫폼에 독립적이며 Cloud, On-Premise, Kubernetes, Mesos 등과 같은 다양한 환경에서 실행되도록 설계되었습니다. Kubernetes 또는 Consul 와 함께 Nomad 에 Istio 를 배포 할 수 있습니다. Istio 는 현재 다음을 지원합니다.

- Kubernetes 에서의 서비스 배포
- Consul 에 등록 된 서비스
- 개별 가상 컴퓨터에서 실행되는 서비스

##### 통합 및 사용자 정의 (Integration and customization)

Istio 의 정책 적용 구성 요소는 접근 제어 목록(access control list, ACL), 로깅(logging), 모니터링(monitoring), 할당량(quotas), 감사(auditing) 등에 대한 기존 솔루션과 통합되도록 확장 및 사용자 정의 할 수 있습니다.

#### 아키텍처 (Architecture)

Istio 서비스 메쉬는 논리적으로 데이터 플레인`Data Plane`과 컨트롤 플레인`Control Plane`으로 나뉩니다.

- 데이터 플레인은 사이드카로 배치된 일련의 인텔리전트 프록시 (Envoy)로 구성됩니다. 이러한 프록시는 범용 정책 및 원격 측정 허브인 Mixer 와 함께 마이크로 서비스 간의 모든 네트워크 통신을 중재하고 제어합니다.

- 컨트롤 플레인은 프록시를 관리 및 구성하여 트래픽을 라우팅합니다. 또한 컨트롤 플레인은 정책을 적용하고 원격 측정(telemetry)을 수집하도록 Mixer를 구성합니다.

다음 다이어그램은 각 평면을 구성하는 다양한 구성 요소를 보여줍니다.

![Istio Architecture](https://istio.io/docs/concepts/what-is-istio/arch.svg)

##### Envoy

Istio 는 확장 버전의 [Envoy](https://envoyproxy.github.io/envoy/) 프록시를 사용합니다. Envoy 는 C++로 개발된 고성능 프록시로서 서비스 메쉬의 모든 서비스에 대한 모든 인-바운드 및 아웃-바운드 트래픽을 조정합니다. Istio 는 Envoy 의 많은 내장 기능을 활용합니다. 예를 들면 다음과 같습니다.

- 동적 서비스 발견 (Dynamic service discovery)
- 로드밸런싱 (Load balancing)
- TLS 종료 (TLS termination)
- HTTP/2 and gRPC proxies
- 회로 차단기 (Circuit breakers)
- 헬스 체크 (Health checks)
- 백분율 기반 트래픽 분할로 단계적 롤아웃 (Staged rollouts with %-based traffic split)
- 오류 주입 (Fault injection)
- 풍부한 측정 항목 (Rich metrics)

Envoy 는 동일한 Kubernetes pod 의 관련 서비스에 사이드카로 배치됩니다. 이 배포를 통해 Istio 는 트래픽 동작에 대한 풍부한 신호를 특성으로 추출할 수 있습니다. Istio 는 Mixer 에서 이러한 속성을 사용하여 정책 결정을 시행하고 이를 모니터링 시스템에 보내 전체 메쉬의 동작에 대한 정보를 제공 할 수 있습니다.

사이드카 프록시 모델을 사용하면 코드를 다시 작성할 필요 없이 기존 배포에 Istio 기능을 추가 할 수 있습니다. 이 접근법을 선택한 이유에 대한 자세한 내용은 [Design Goals](https://istio.io/docs/concepts/what-is-istio/#design-goals) 에서 확인할 수 있습니다.

> 참고
> Envoy는 대규모 최신 서비스 지향 아키텍처 용으로 설계된 L7 프록시 및 통신 버스입니다.
> TLS는 전송 계층 보안 (Transport Layer Security)의 약자로 SSL의 뒤를 잇는 표준입니다.

##### Mixer

Mixer 는 플랫폼에 독립적인 구성 요소입니다. Mixer 는 서비스 메쉬 전체에서 액세스 제어 및 사용 정책을 시행하고 Consul 프록시 및 기타 서비스에서 원격 측정 데이터를 수집합니다. 프록시는 요청 수준 속성을 추출하여 평가를 위해 Mixer 로 보냅니다. 이 속성 추출 및 정책 평가에 대한 자세한 내용은 [Mixer 구성 설명서](https://istio.io/docs/concepts/policies-and-telemetry/#configuration-model)에서 확인할 수 있습니다.

Mixer 에는 유연한 플러그인 모델이 포함되어 있습니다. 이 모델을 통해 Istio 는 다양한 호스트 환경 및 인프라 백엔드와 인터페이스 할 수 있습니다. 따라서 Istio 는 이러한 세부 사항에서 Consul 프록시 및 Istio 관리 서비스를 추상화합니다.

##### Pilot

Pilot 은 Envoy 사이드카에 대한 서비스 검색, 지능형 라우팅 (예 : A/B 테스트, Canary 배치 등) 및 복원력 (timeout, 재시도, 회로 차단기 등)을 위한 트래픽 관리 기능을 제공합니다.

##### Citadel

Citadel 은 내장된 ID 및 자격 증명 관리를 통해 강력한 서비스 대 서비스 및 최종 사용자 인증을 제공합니다. Citadel 을 사용하여 서비스 메쉬에서 암호화 되지 않은 트래픽을 업그레이드 할 수 있습니다. Citadel 을 사용하면 운영자가 네트워크 제어가 아닌 서비스 ID 를 기반으로 정책을 시행 할 수 있습니다. 릴리스 0.5 부터는 Istio 의 권한 부여 기능을 사용하여 서비스에 액세스 할 수 있는 사용자를 제어 할 수 있습니다.

##### Galley

Galley 는 다른 Istio 컨트롤 플레인 구성 요소를 대신하여 사용자가 작성한 Istio API 구성의 유효성을 검사합니다. 시간이 지남에 따라 Galley 는 Istio 의 최상위 구성 처리, 처리 및 배포 구성 요소로서의 책임을 대신 수행합니다. Istio 구성 요소의 나머지 부분을 기본 플랫폼 (예 : Kubernetes)에서 사용자 구성을 얻는 세부 사항으로부터 격리시키는 작업을 담당하게됩니다.

#### 디자인 목표

몇 가지 주요 디자인 목표는 Istio 의 아키텍처에 대한 정보를 제공합니다. 이러한 목표는 시스템이 규모와 성능이 우수한 서비스를 처리 할 수 있게 하는 데 필수적입니다.

**투명성 극대화 (Maximize Transparency)**
Istio 를 채택하려면 운영자 또는 개발자가 시스템에서 실제 가치를 얻기 위해 가능한 최소한의 작업을 수행해야합니다. 이를 위해 Istio 는 자동으로 서비스 사이의 모든 네트워크 경로에 자신을 삽입 할 수 있습니다. Istio 는 사이드카 프록시를 사용하여 트래픽을 캡처하고 가능한 경우 배치 된 응용 프로그램 코드를 변경하지 않고 해당 프록시를 통해 트래픽을 라우팅하기 위해 네트워킹 계층을 자동으로 프로그래밍합니다. Kubernetes 에서는 프록시를 포드에 주입하고 `iptables` 규칙을 프로그래밍하여 트래픽을 캡처합니다. 사이드카 프록시가 주입되고 트래픽 라우팅이 프로그래밍되면 Istio 는 모든 트래픽을 중재 할 수 있습니다. 이 원칙은 성과에도 적용됩니다. Istio 를 배포에 적용하면 운영자는 제공되는 기능에 대한 리소스 비용을 최소한으로 증가시킬 수 있습니다. 구성 요소와 API 는 모두 성능과 규모를 고려하여 설계해야합니다.

**증분 (Incrementality)**
운영자와 개발자가 Istio 가 제공하는 기능에 더 많이 의존하게 됨에 따라 시스템은 필요에 따라 확장되어야 합니다. 새로운 기능을 계속 추가하는 동안 정책 시스템을 확장하고 다른 정책 및 제어 소스와 통합하고 분석을 위해 다른 시스템에 메쉬 동작에 대한 신호를 전파하는 기능이 가장 필요합니다. 정책 런타임은 다른 서비스를 연결하기위한 표준 확장 메커니즘을 지원합니다. 또한 메쉬가 생성하는 새로운 신호를 기반으로 정책을 적용 할 수 있도록 어휘를 확장 할 수 있습니다.

**이식성 (Portability)**
Istio 의 생태계(ecosystem)는 다양합니다. Istio 는 최소의 노력으로 모든 클라우드 또는 사내 구축 환경에서 실행해야 합니다. 새 환경에 Istio 기반 서비스를 포팅하는 작업은 간단해야 합니다. Istio 를 사용하면 여러 환경에 배포된 단일 서비스를 운영 할 수 있습니다. 예를 들어 중복성을 위해 여러 클라우드에 배포 할 수 있습니다.

**정책 통일성 (Policy Uniformity)**
서비스 간 API 호출에 정책을 적용하면 메쉬 동작을 크게 제어 할 수 있습니다. 그러나 반드시 API 레벨에서 표현되지 않는 자원에 정책을 적용하는 것도 중요합니다. 예를 들어, ML 교육 작업에서 소비한 CPU 양에 할당량을 적용하는 것이 작업을 시작한 통화에 할당량을 적용하는 것보다 유용합니다. 이를 위해 Istio 는 정책 시스템을 프록시 사이드카에 끼워넣기 보다는 자체 API 와 별도의 서비스로 유지 관리하여 서비스가 필요에 따라 직접 통합 할 수 있도록 합니다.

### 트래픽 관리 (Traffic Management)

> 이 페이지는 트래픽 관리 원칙의 이점을 포함하여 Istio 에서 트래픽 관리가 작동하는 방식에 대한 개요를 제공합니다. 그리고 여러분이 이미 [Istio 가 무엇인가](https://istio.io/docs/concepts/what-is-istio/)를 읽었고 Istio 의 고수준 아키텍처에 익숙하다고 가정합니다.

Istio 의 트래픽 관리 모델을 사용하면 트래픽 흐름과 인프라 확장이 분리되어 Pilot을 통해 특정 pods/VM 이 트래픽을 수신하는 것보다 트래픽을 따르기 원하는 규칙을 지정할 수 있습니다. 나머지는 Pilot 및 지능적인 Envoy 프록시가 고려합니다. 예를 들어 Pilot을 통해 특정 서비스에 대한 트래픽의 5%가 Canary 배포 크기와 상관없이 Canary 버전으로 이동하거나 요청 콘텐츠에 따라 특정 버전으로 트래픽을 전송하도록 지정할 수 있습니다.

![Traffic Management with Istio](https://istio.io/docs/concepts/traffic-management/TrafficManagementOverview.svg)

인프라 스케일링에서 트래픽 흐름을 분리하면 Istio 는 애플리케이션 코드 외부에 있는 다양한 트래픽 관리 기능을 제공 할 수 있습니다. A/B 테스트, 점진적 롤아웃 및 Canary 릴리스에 대한 동적 요청 라우팅 뿐만 아니라 타임 아웃, 재시도, 회로 차단기 및 결함 주입을 사용하여 장애 복구를 처리하여 서비스 전반의 장애 복구 정책 호환성을 테스트합니다. 이러한 기능은 모두 서비스 메쉬에 배치 된 Envoy 사이드카/프록시를 통해 실현됩니다.

#### Pilot and Envoy

Istio 에서 트래픽 관리에 사용되는 핵심 구성 요소는 특정 Istio 서비스 메쉬에 배포된 모든 Envoy 프록시 인스턴스를 관리하고 구성하는 **Pilot** 입니다. Pilot을 사용하면 Envoy 프록시간에 트래픽을 라우팅하고 제한 시간, 재시도, 회로 차단기와 같은 오류 복구 기능을 구성하는 데 사용할 규칙을 지정할 수 있습니다. 또한 메쉬에있는 모든 서비스의 정식 모델을 유지하고이 모델을 사용하여 Envoy 인스턴스가 검색 서비스를 통해 메쉬의 다른 Envoy 인스턴스에 대해 알릴 수 있습니다.

각 Envoy 인스턴스는 Pilot에서 가져온 정보와 정기적 상태 확인을 통해 수집된 다른 인스턴스의 로드밸런싱 정보를 관리합니다. 이로써 지정된 라우팅 규칙을 따라 대상 인스턴스간에 트래픽을 지능적으로 배포 할 수 있습니다.

Pilot은 Istio 서비스 메쉬 전체에 배치된 Envoy 인스턴스의 수명주기를 담당합니다.

![Pilot Architecture](https://istio.io/docs/concepts/traffic-management/PilotAdapters.svg)

위의 그림에서 볼 수 있듯이 Pilot 은 기본 플랫폼과 독립적인 메쉬에서 서비스의 표준 표현을 유지합니다. Pilot의 플랫폼 별 어댑터는 이 정식 모델을 적절하게 채우는 역할을 담당합니다. 예를 들어 Pilot 의 Kubernetes 어댑터는 포드 등록 정보, 수신 리소스 및 트래픽 관리 규칙을 저장하는 타사 리소스의 변경을 위해 Kubernetes API 서버를 감시하는 데 필요한 컨트롤러를 구현합니다. 이 데이터는 표준 표현으로 변환됩니다. 그런 다음 정식 표현을 기반으로 특유의 구성이 생성됩니다.

Pilot을 사용하면 [서비스 검색](https://www.envoyproxy.io/docs/envoy/latest/api-v1/cluster_manager/sds)`service discovery`, [로드밸런싱 풀](https://www.envoyproxy.io/docs/envoy/latest/configuration/cluster_manager/cds) 및 [라우팅 테이블](https://www.envoyproxy.io/docs/envoy/latest/configuration/http_conn_man/rds)에 대한 동적 업데이트가 가능합니다.

[Pilot 규칙 구성](https://istio.io/docs/reference/config/istio.networking.v1alpha3/)을 통해 고급 트래픽 관리 규칙을 지정할 수 있습니다. 이러한 규칙은 낮은 수준의 구성으로 변환되어 Envoy 인스턴스에 배포됩니다.

#### 라우팅 요청 (Request routing)

전술한 바와 같이, 메쉬에서의 서비스의 정규 표현은 Pilot에 의해 유지된다. 서비스의 Istio 모델은 기본 플랫폼 (Kubernetes, Mesos, Cloud Foundry, etc.)에서 어떻게 표현되는지 독립적입니다. 플랫폼 특정 어댑터(Platform-specific adapters)는 플랫폼에서 찾은 메타 데이터의 다양한 필드로 내부 모델 표현을 채우는 역할을 담당합니다.

Istio는 버전(`v1`, `v2`)별 또는 환경(`staging`, `prod`)별로 서비스 인스턴스를 세분화 하는 버전 개념을 도입합니다. 이러한 변형은 반드시 다른 API 버전일 필요는 없습니다. 동일한 서비스에 반복적으로 변경하거나 다른 환경 (prod, staging, dev, etc.)에 배포 할 수 있습니다. 이를 사용하는 일반적인 시나리오에는 A/B 테스트 또는 Canary 롤아웃이 포함됩니다. Istio의 [트래픽 라우팅 규칙](https://istio.io/docs/concepts/traffic-management/#rule-configuration)은 서비스 버전을 참조하여 서비스 간의 트래픽을 추가로 제어 할 수 있습니다.

##### 서비스 간 통신 (Communication between services)

![Service Versions](https://istio.io/docs/concepts/traffic-management/ServiceModel_Versions.svg)

위의 그림에서 볼 수 있듯이 서비스의 클라이언트는 서로 다른 버전의 서비스에 대해 알지 못합니다. 서비스의 hostname/IP주소를 사용하여 서비스에 계속 액세스 할 수 있습니다. Envoy 사이드카/프록시는 클라이언트와 서비스 간의 모든 요청/응답을 가로 채고 전달합니다.

*Envoy는 Pilot을 사용하여 지정한 라우팅 규칙을 기반으로 서비스 버전의 실제 선택을 동적으로 결정합니다.* 이 모델을 사용하면 애플리케이션 코드(code)가 종속된 서비스의 진화(개선)와 분리되는 동시에 다른 이점도 얻을 수 있습니다 ([Mixer](https://istio.io/docs/concepts/policies-and-telemetry/) 참조). 라우팅 규칙을 통해 Envoy는 헤더, 소스(source)/대상(destination)과 관련된 태그 and/or 각 버전에 할당된 가중치와 같은 조건을 기반으로 버전을 선택할 수 있습니다.

또한 Istio는 동일한 서비스 버전의 여러 인스턴스에 대한 트래픽에 대한 로드밸런싱을 제공합니다. 자세한 내용은 [검색 및 로드밸런싱](https://istio.io/docs/concepts/traffic-management/#discovery-and-load-balancing)`Discovery and Load Balancing`을 참조하십시오.

Istio는 DNS를 제공하지 않습니다. 응용 프로그램은 기본 플랫폼 (kube-dns, mesos-dns 등)에있는 DNS 서비스를 사용하여 FQDN을 확인할 수 있습니다.

> 참조
> FQDN: 전체 주소 도메인 네임(Fully Qualified Domain Name, FQDN)은 호스트 이름과 도메인 이름을 포함한 전체 도메인 이름을 일컫는 용어이다. 절대 도메인 네임(absolute domain name)이라고도 한다.

##### 수신 및 발신 (Ingress and egress)

Istio는 서비스 메쉬로 들어오고 나가는 모든 트래픽이 Envoy 프록시를 통해 이동한다고 가정합니다. 서비스 앞에 Envoy 프록시를 배치하면 사용자 대면 서비스를 위해 A / B 테스트를 수행하고 Canary 서비스를 배포 할 수 있습니다. 마찬가지로 Envoy 사이드카를 통해 트래픽을 외부 웹 서비스 (예 :지도 API 또는 비디오 서비스 API에 액세스)로 라우팅하면 timeout, 재시도 및 회로 차단기와 같은 오류 복구 기능을 추가하고 이러한 서비스에 연결에서 자세한 측정 항목을 얻을 수 있습니다.

![Request Flow](https://istio.io/docs/concepts/traffic-management/ServiceModel_RequestFlow.svg)

#### 디스커버리와 로드밸런싱 (Discovery and load balancing)

Istio는 서비스 메쉬의 서비스 인스턴스 간의 트래픽을 로드밸런싱 합니다.

Istio는 응용 프로그램에서 서비스의 Pods/VM을 추적하는 서비스 레지스트리의 존재를 가정합니다. 또한 서비스의 새 인스턴스가 자동으로 서비스 레지스트리에 등록되고 비정상 인스턴스가 자동으로 제거된다고 가정합니다. Kubernetes 및 Mesos와 같은 플랫폼은 이미 컨테이너 기반 응용 프로그램을 위한 이러한 기능을 제공하며 VM 기반 응용 프로그램을 위한 많은 솔루션이 있습니다.

Pilot은 서비스 레지스트리에서 정보를 사용하고 플랫폼 독립적인 서비스 검색 인터페이스를 제공합니다. 메쉬의 Envoy 인스턴스는 서비스 검색을 수행하고 이에 따라 부하 분산 풀을 동적으로 업데이트합니다.

![Discovery and Load Balancing](https://istio.io/docs/concepts/traffic-management/LoadBalancing.svg)

위 그림에서와 같이 메쉬의 서비스는 DNS 이름을 사용하여 서로 액세스합니다. 서비스에 바인딩 된 모든 HTTP 트래픽은 Envoy를 통해 자동으로 재 라우팅됩니다. Envoy는 로드밸런싱 풀의 인스턴스 간 트래픽을 분산시킵니다. Envoy는 여러 정교한 로드밸런스 알고리즘을 지원하지만 Istio는 현재 라운드 로빈(round robin), 무작위(random) 및 가중치가 가장 낮은 요청(weighted least request) 세 가지 로드밸런스 모드를 허용합니다.

부하 분산 외에 Envoy는 풀의 각 인스턴스의 상태를 주기적으로 확인합니다. Envoy는 회로 차단기 패턴을 따라 상태 검사 API 호출의 실패율을 기준으로 인스턴스를 비정상 또는 정상으로 분류합니다. 즉, 주어진 인스턴스에 대한 상태 확인 실패 수가 미리 지정된 임계 값을 초과하면 로드밸런싱 풀에서 제거(eject)됩니다. 마찬가지로 통과된 상태 확인 수가 미리 지정한 임계 값을 초과하면 인스턴스가 다시 로드밸런싱 풀에 추가됩니다. Envoy의 실패 처리 기능에 대한 자세한 내용은 [handling-failures](https://istio.io/docs/concepts/traffic-management/#handling-failures)를 참조하십시오.

서비스는 HTTP 503 (Service unavailable) 으로 상태 검사에 응답하여 로드를 능동적으로 차단할 수 있습니다. 이러한 경우 서비스 인스턴스가 호출자의로드밸런싱 풀에서 즉시 제거됩니다.

#### 실패 처리 (Handling failures)

Envoy는 응용 프로그램의 서비스에서 활용할 수있는 즉시 사용 가능한 옵트 인 오류 복구 기능을 제공합니다. 특징은 다음과 같습니다.

- 타임아웃
- 시간 제한 예산 및 재시도 간의 가변 지터가있는 제한된 재시도
- 동시 연결 수 및 업스트림 서비스 요청 수 제한
- 로드밸런싱 풀의 각 구성원에 대한 활성 (정기) 상태 검사
- 세분화 된 회로 차단기 (수동 상태 검사) - 부하 분산 풀의 인스턴스별로 적용

이러한 기능은 Istio의 트래픽 관리 규칙을 통해 런타임에 동적으로 구성 할 수 있습니다.

재 시도 간의 지터는 재부팅이 과부하 된 업스트림 서비스에 미치는 영향을 최소화하며, timeout 예산은 호출 서비스가 예측 가능한 시간 프레임 내에서 응답 (성공 / 실패)을 얻도록 보장합니다.

능동 및 수동 상태 확인 (위의 4 및 5)의 조합은로드밸런싱 풀에서 비정상적인 인스턴스에 액세스 할 가능성을 최소화합니다. 플랫폼 수준의 상태 확인 (예 : Kubernetes 또는 Mesos에서 지원)과 함께 사용하면 응용 프로그램이 비정상적인 포드 / 컨테이너 / VM을 서비스 메쉬에서 신속하게 추출하여 요청 실패를 최소화하고 대기 시간에 미치는 영향을 최소화 할 수 있습니다.

이러한 기능을 함께 사용하면 서비스 메쉬가 장애가 발생한 노드를 허용하고 로컬 화 된 장애가 계단식 불안정성에서 다른 노드로 연결되는 것을 방지 할 수 있습니다.

##### 미세 조정 (Fine tuning)

Istio의 트래픽 관리 규칙을 사용하면 모든 발신자에게 적용되는 서비스 및 버전 별 오류 복구에 대한 기본값을 설정할 수 있습니다. 그러나 서비스 사용자는 특정 HTTP 헤더를 통해 요청 수준 재정의를 제공하여 timeout 및 다시 시도 기본값을 재정의 할 수도 있습니다. Envoy 프록시 구현에서 헤더는 각각 x-envoy-upstream-rq-timeout-ms 및 x-envoy-max-retries입니다.

##### 오류 처리 FAQ (Failure handling FAQ)

Q: Istio에서 실행 중일 때 응용 프로그램이 여전히 오류를 처리합니까?

> 예.
> Istio는 메쉬의 서비스 안정성과 가용성을 향상시킵니다. 그러나 응용 프로그램은 오류 (오류)를 처리하고 적절한 대체 작업을 수행해야합니다. 예를 들어로드밸런싱 풀의 인스턴스가 모두 실패하면 Envoy는 HTTP 503을 반환합니다. 업스트림 서비스에서 HTTP 503 오류 코드를 처리하는 데 필요한 폴백 논리를 구현하는 것은 응용 프로그램의 책임입니다.

Q: Envoy의 오류 복구 기능은 내결함성 라이브러리 (예 : [Hystrix](https://github.com/Netflix/Hystrix))를 이미 사용하는 응용 프로그램을 중단시키지 않습니까?

> 아니오.
> Envoy는 애플리케이션에 완전히 투명합니다. Envoy가 반환 한 실패 응답은 호출이 만들어진 업스트림 서비스가 반환 한 실패 응답과 구별되지 않습니다.

Q: 응용 프로그램 수준 라이브러리와 Envoy를 동시에 사용할 때 오류가 어떻게 처리됩니까?

> 동일한 대상 서비스에 대한 두 가지 오류 복구 정책이 주어지면 오류가 발생할 때 두 가지 중 더 제한적인 방아쇠가 트리거됩니다. 예를 들어 Envoy에서 설정 한 timeout와 응용 프로그램의 라이브러리에서 다른 시간 제한 두 가지가 있습니다. 이 예제에서, Envoy에서 10 초의 시간 제한을 구성하는 동안 응용 프로그램이 서비스에 대한 API 호출에 대해 5 초의 시간 제한을 설정하면 응용 프로그램의 timeout가 먼저 시작됩니다. 마찬가지로, Envoy의 회로 차단기가 응용 프로그램의 회로 차단기 전에 트리거되면 서비스에 대한 API 호출은 Envoy에서 503을 얻습니다.

#### 오류 주입 (Fault injection)

Envoy 사이드/프록시는 Istio에서 실행되는 서비스에 여러 가지 [장애 복구 메커니즘](https://istio.io/docs/concepts/traffic-management/#handling-failures)을 제공하지만 응용 프로그램의 전체적인 장애 복구 기능을 테스트하는 것은 여전히 ​​필수적입니다. 잘못 구성된 장애 복구 정책 (예 : 서비스 호출간에 호환되지 않거나 제한적인 제한 시간)은 응용 프로그램에서 중요한 서비스가 계속해서 사용 불가능하게되어 사용자 환경이 좋지 않을 수 있습니다.

Istio는 포드를 죽이거나 TCP 레이어에서 패킷을 지연 또는 손상시키는 대신 네트워크에 프로토콜 별 오류 삽입을 가능하게합니다. 이론적 근거는 응용 프로그램 계층에서 관찰 된 오류가 네트워크 수준 오류와 관계없이 동일하며 응용 프로그램 계층에서 더 중요한 오류 (예 : HTTP 오류 코드)를 주입하여 응용 프로그램의 복원력을 행사할 수 있다는 것입니다.

특정 조건과 일치하는 요청에 오류가 삽입되도록 구성 할 수 있습니다. 오류의 영향을 받아야하는 요청 비율을 더 제한 할 수 있습니다. 두 가지 유형의 결함 (지연 및 중단)을 주입 할 수 있습니다. 지연은 타이밍 장애, 증가 된 네트워크 대기 시간 또는 과부하 된 업스트림 서비스를 모방합니다. 중단은 업스트림 서비스의 실패를 모방 한 크래시 실패입니다. 일반적으로 중단은 HTTP 오류 코드 또는 TCP 연결 실패의 형태로 나타납니다.

#### 규칙 구성 (Rule configuration)

Istio는 애플리케이션 배포시 다양한 서비스에서 API 호출 및 레이어 -4 트래픽 흐름을 제어하는 ​​간단한 구성 모델을 제공합니다. 구성 모델을 사용하면 회로 차단기, 시간 제한 및 재시도 같은 서비스 수준 속성을 구성 할 수있을뿐 아니라 Canary 롤아웃, A / B 테스트, % 기반 트래픽 분할로 단계적 롤아웃 등과 같은 일반적인 연속 배포 작업을 설정할 수 있습니다 .

Istio에는 VirtualService, DestinationRule, ServiceEntry 및 Gateway의 네 가지 트래픽 관리 구성 리소스가 있습니다.

- [VirtualService](https://istio.io/docs/reference/config/istio.networking.v1alpha3/#VirtualService)는 Istio 서비스 메쉬 내에서 서비스 요청이 라우팅되는 방식을 제어하는 ​​규칙을 정의합니다.
- [DestinationRule](https://istio.io/docs/reference/config/istio.networking.v1alpha3/#DestinationRule)은 VirtualService 라우팅이 발생한 후 요청에 적용 할 정책 집합을 구성합니다.
- [ServiceEntry](https://istio.io/docs/reference/config/istio.networking.v1alpha3/#ServiceEntry)는 일반적으로 Istio 서비스 메쉬 외부의 서비스에 대한 요청을 활성화하는 데 사용됩니다.
- [Gateway](https://istio.io/docs/reference/config/istio.networking.v1alpha3/#Gateway)는 HTTP / TCP 트래픽에 대한로드 밸런서를 구성합니다. 가장 일반적으로 메쉬의 가장자리에서 작동하여 응용 프로그램의 수신 트래픽을 활성화합니다.

예를 들어 다음과 같이 VirtualService 구성을 사용하여 검토 서비스의 들어오는 트래픽을 버전 "v1"로 100 % 전송하는 간단한 규칙을 구현할 수 있습니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: reviews
spec:
  hosts:
  - reviews
  http:
  - route:
    - destination:
        host: reviews
        subset: v1
</code></pre>

이 구성은 리뷰 필드 (호스트 필드에 지정된)로 전송 된 트래픽을 기본 리뷰 서비스 인스턴스의 하위 집합으로 라우팅해야한다고 말합니다. 경로 하위 집합은 해당 대상 규칙 구성에서 정의 된 하위 집합의 이름을 지정합니다.

부분 집합은 버전 별 인스턴스를 식별하는 하나 이상의 레이블을 지정합니다. 예를 들어 Istio의 Kubernetes 배포에서 "version : v1"은 "version : v1"레이블이 포함 된 포드 만 트래픽을 수신 함을 나타냅니다.

`DestinationRule`에서 추가 정책을 추가 할 수 있습니다. 예를 들어 다음 정의에서는 무작위로드밸런싱 모드를 사용하도록 지정합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: reviews
spec:
  host: reviews
  trafficPolicy:
    loadBalancer:
      simple: RANDOM
  subsets:
  - name: v1
    labels:
      version: v1
  - name: v2
    labels:
      version: v2
</code></pre>

룰은 `kubectl` 명령을 사용하여 구성 할 수 있습니다. 예를 보려면 [요청 라우팅 구성 태스크](https://istio.io/docs/tasks/traffic-management/request-routing/)를 참조하십시오.

##### 가상 서비스 (Virtual Services)

VirtualService는 Istio 서비스 메쉬 내에서 서비스 요청이 라우팅되는 방식을 제어하는 ​​규칙을 정의합니다. 예를 들어, 가상 서비스는 요청을 다른 버전의 서비스로 또는 요청한 것과 완전히 다른 서비스로 라우팅 할 수 있습니다. 요청은 요청 소스 및 대상, HTTP 경로 및 헤더 필드 및 개별 서비스 버전과 관련된 가중치를 기반으로 라우팅 될 수 있습니다.

###### 규칙 대상 (Rule destinations)

라우팅 규칙은 VirtualService 구성에 지정된 하나 이상의 요청 대상 호스트에 해당합니다. 이러한 호스트는 실제 대상 작업 부하와 동일하거나 다를 수 있으며 메쉬의 실제 라우팅 가능한 서비스와 일치하지 않을 수도 있습니다. 예를 들어 내부 메쉬 이름 리뷰 나 host bookinfo.com을 사용하여 리뷰 서비스 요청에 대한 라우팅 규칙을 정의하려면 VirtualService가 hosts 필드를 다음과 같이 설정할 수 있습니다.

<pre><code>
hosts:
  - reviews
  - bookinfo.com
</code></pre>

`host` 필드는 암시 적 또는 명시 적으로 하나 이상의 FQDN (정규화 된 도메인 이름)을 지정합니다. 위의 짧은 이름 검토는 구현 특정 FQDN으로 암시 적으로 확장됩니다. 예를 들어, Kubernetes 환경에서 전체 이름은 VirtualSevice의 클러스터와 네임 스페이스에서 파생됩니다 (예 : reviews.default.svc.cluster.local).

###### 버전 간 트래픽 분할 (Splitting traffic between versions)

각 경로 규칙은 규칙이 활성화 될 때 호출 할 하나 이상의 가중 백엔드를 식별합니다. 각 백엔드는 버전을 레이블을 사용하여 표현할 수있는 대상 서비스의 특정 버전에 해당합니다. 지정된 레이블이있는 등록 된 인스턴스가 여러 개있는 경우 서비스에 구성된로드밸런스 정책 또는 기본적으로 라운드 로빈에 따라 라우트됩니다.

예를 들어 다음 규칙은 검토 서비스에 대한 트래픽의 25 %를 'v2'라벨이있는 인스턴스로, 나머지 75 %는 'v1'로 라우팅합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: reviews
spec:
  hosts:
    - reviews
  http:
  - route:
    - destination:
        host: reviews
        subset: v1
      weight: 75
    - destination:
        host: reviews
        subset: v2
      weight: 25
</code></pre>

###### timeout 및 재시도 (Timeouts and retries)

기본적으로 HTTP 요청 시간 제한은 15 초이지만 다음과 같이 경로 규칙에서 재정의 할 수 있습니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
    - ratings
  http:
  - route:
    - destination:
        host: ratings
        subset: v1
    timeout: 10s
</code></pre>

경로 규칙에서 HTTP 요청에 대한 재시도 횟수를 지정할 수도 있습니다. 최대 재시도 횟수 또는 기본 또는 재 지정 제한 시간 내에 시도 할 수있는 횟수는 다음과 같이 설정할 수 있습니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
    - ratings
  http:
  - route:
    - destination:
        host: ratings
        subset: v1
    retries:
      attempts: 3
      perTryTimeout: 2s
</code></pre>

요청 timeout 및 재시도는 [요청 별로 재정의 될 수 있습니다.](https://istio.io/docs/concepts/traffic-management/#fine-tuning)

[timeout 제어](https://istio.io/docs/tasks/traffic-management/request-timeouts)의 예제는 요청 timeout 태스크를 참조하십시오.

###### 오류 주입 (Injecting faults)

경로 규칙은 HTTP 요청을 규칙의 해당 요청 대상으로 전달하는 동안 삽입 할 하나 이상의 오류를 지정할 수 있습니다. 오류는 지연되거나 중단 될 수 있습니다.

다음 예제는 등급 마이크로 서비스의 "v1"버전에 대한 요청의 10 %에서 5 초 지연을 도입합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
  - ratings
  http:
  - fault:
      delay:
        percent: 10
        fixedDelay: 5s
    route:
    - destination:
        host: ratings
        subset: v1
</code></pre>

다른 유형의 결함 인 중단을 사용하여 요청을 조기에 종료 할 수 있습니다. 예를 들어, 실패를 시뮬레이션합니다.

다음 예에서는 등급 서비스 "v1"에 대한 요청의 10 %에 대해 HTTP 400 오류 코드를 반환합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
  - ratings
  http:
  - fault:
      abort:
        percent: 10
        httpStatus: 400
    route:
    - destination:
        host: ratings
        subset: v1
</code></pre>

지연 및 중단 오류가 함께 사용되는 경우가 있습니다. 예를 들어, 다음 규칙은 검토 서비스 "v2"에서 등급 서비스 "v1"까지의 모든 요청을 5 초 지연 한 다음 그 중 10 %를 중단합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
  - ratings
  http:
  - match:
    - sourceLabels:
        app: reviews
        version: v2
    fault:
      delay:
        fixedDelay: 5s
      abort:
        percent: 10
        httpStatus: 400
    route:
    - destination:
        host: ratings
        subset: v1
</code></pre>

작동중인 결함 주입을 보려면 [결함 주입 작업](https://istio.io/docs/tasks/traffic-management/fault-injection/)을 참조하십시오.

###### 조건부 규칙 (Conditional rules)

규칙은 다음과 같은 특정 조건과 일치하는 요청에만 적용되도록 선택적으로 자격을 부여 할 수 있습니다.

1. 워크로드 레이블을 사용하여 특정 클라이언트 작업 부하로 제한하십시오. 예를 들어 규칙은 검토 서비스를 구현하는 작업 부하 (포드)의 호출에만 적용된다는 것을 나타낼 수 있습니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
  - ratings
  http:
  - match:
      sourceLabels:
        app: reviews
    ...
</code></pre>

sourceLabels의 값은 서비스 구현에 따라 다릅니다. 예를 들어, Kubernetes에서는 해당 Kubernetes 서비스의 포드 선택기에서 사용되는 레이블과 동일 할 것입니다.

위의 예는 리뷰 서비스 버전 "v2"의 호출에만 적용되도록 추가로 개선 될 수 있습니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
  - ratings
  http:
  - match:
    - sourceLabels:
        app: reviews
        version: v2
    ...
</code></pre>

2. HTTP 헤더를 기반으로 규칙을 선택하십시오. 예를 들어 다음 규칙은 "jason"문자열이 포함 된 사용자 지정 "최종 사용자"헤더를 포함하는 경우 들어오는 요청에만 적용됩니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: reviews
spec:
  hosts:
    - reviews
  http:
  - match:
    - headers:
        end-user:
          exact: jason
    ...
</code></pre>

규칙에 두 개 이상의 머리글이 지정되면 적용 할 규칙에 대해 해당 머리글이 모두 일치해야합니다.

3. Select rule based on request URI. For example, the following rule only applies to a request if the URI path starts with /api/v1:

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: productpage
spec:
  hosts:
    - productpage
  http:
  - match:
    - uri:
        prefix: /api/v1
    ...
</code></pre>

###### 여러 일치 조건 (Multiple match conditions)

여러 일치 조건을 동시에 설정할 수 있습니다. 그러한 경우 중첩에 따라 AND 또는 OR 의미가 적용됩니다.

여러 조건이 단일 일치 절에 중첩되어 있으면 조건이 AND됩니다. 예를 들어 클라이언트 워크로드가 "리뷰 : v2"이고 "jason"이 포함 된 사용자 정의 "최종 사용자"헤더가 요청에있는 경우에만 다음 규칙이 적용됩니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
  - ratings
  http:
  - match:
    - sourceLabels:
        app: reviews
        version: v2
      headers:
        end-user:
          exact: jason
    ...
</code></pre>

If instead, the condition appear in separate match clauses, then only one of the conditions applies (OR semantics):

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: ratings
spec:
  hosts:
  - ratings
  http:
  - match:
    - sourceLabels:
        app: reviews
        version: v2
    - headers:
        end-user:
          exact: jason
    ...
</code></pre>

이 규칙은 클라이언트 워크로드가 "검토 : v2"이거나 "jason"이 포함 된 사용자 정의 "최종 사용자"헤더가 요청에있는 경우에 적용됩니다.

###### 상위 (Precedence)

지정된 대상에 대해 여러 규칙이있는 경우 해당 규칙은 VirtualService에 표시된 순서대로 평가됩니다. 즉, 목록의 첫 번째 규칙이 가장 높은 우선 순위를 갖습니다.

**우선 순위가 중요한 이유는 무엇입니까?**
특정 서비스에 대한 라우팅 스토리가 순전히 가중치 기반 일 때마다 단일 규칙으로 지정할 수 있습니다. 반면에 다른 조건 (특정 사용자의 요청과 같은)이 트래픽을 라우팅하는 데 사용되는 경우 라우팅을 지정하는 데 하나 이상의 규칙이 필요합니다. 규칙을 올바른 순서로 평가할 수 있도록 규칙 우선 순위를 신중하게 고려해야합니다.

일반화 된 라우트 스펙의 공통 패턴은 다양한 조건과 일치하는 하나 이상의 상위 우선 순위 룰을 제공 한 다음 마지막으로 일치 조건이없는 단일 가중 기반 룰을 제공하여 다른 모든 경우에 대해 트래픽의 가중치 분배를 제공합니다.

예를 들어 다음 VirtualService에는 "foo"라는 값의 헤더가 포함 된 리뷰 서비스 요청이 모두 "v2"인스턴스로 전송되도록 지정하는 두 가지 규칙이 있습니다. 나머지 요청은 모두 'v1'로 전송됩니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: reviews
spec:
  hosts:
  - reviews
  http:
  - match:
    - headers:
        Foo:
          exact: bar
    route:
    - destination:
        host: reviews
        subset: v2
  - route:
    - destination:
        host: reviews
        subset: v1
</code></pre>

헤더 기반 규칙의 우선 순위가 높습니다. 이 값이 낮 으면 특정 일치 조건이없는 가중치 기반 규칙이 일치하는 "Foo"헤더가 포함 된 요청을 포함하여 모든 트래픽을 "v1"로 라우팅하기 위해 먼저 평가되므로이 규칙은 예상대로 작동하지 않습니다. 들어오는 요청에 적용되는 규칙이 발견되면 규칙이 실행되고 규칙 평가 프로세스가 종료됩니다. 그래서 하나 이상의 규칙이있을 때 각 규칙의 우선 순위를 신중하게 고려하는 것이 중요합니다.

##### 대상 규칙 (Destination rules)

`DestinationRule`은 `VirtualService` 라우팅이 발생한 후 요청에 적용 할 정책 집합을 구성합니다. 이들은 서비스 소유자가 작성하여 회로 차단기,로드 밸런서 설정, TLS 설정 및 기타 설정을 설명합니다.

`DestinationRule`은 해당 대상 호스트의 주소 지정 가능한 `subets`, 즉 명명 된 버전을 정의합니다. 이러한 하위 집합은 트래픽의 특정 버전에 트래픽을 보낼 때 VirtualService 라우트 사양에 사용됩니다.

다음 `DestinationRule`은 검토 서비스에 대한 정책 및 하위 집합을 구성합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: reviews
spec:
  host: reviews
  trafficPolicy:
    loadBalancer:
      simple: RANDOM
  subsets:
  - name: v1
    labels:
      version: v1
  - name: v2
    labels:
      version: v2
    trafficPolicy:
      loadBalancer:
        simple: ROUND_ROBIN
  - name: v3
    labels:
      version: v3
</code></pre>

이 예에서 여러 개의 정책 (기본값 및 v2 관련)을 단일 DestinationRule 구성에 지정할 수 있습니다.

###### 회로 차단기 (Circuit breakers)

간단한 회로 차단기는 연결 및 요청 제한과 같은 여러 조건을 기반으로 설정할 수 있습니다.

예를 들어 다음 `DestinationRule`은 리뷰 서비스 버전 "v1"백엔드에 대한 연결 수를 100으로 제한합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: reviews
spec:
  host: reviews
  subsets:
  - name: v1
    labels:
      version: v1
    trafficPolicy:
      connectionPool:
        tcp:
          maxConnections: 100
</code></pre>

회로 차단기 제어 데모는 [회로 차단 작업](https://istio.io/docs/tasks/traffic-management/circuit-breaking/)을 참조하십시오.

###### 규칙 평가 (Rule evaluation)

경로 규칙과 마찬가지로 DestinationRule에 정의 된 정책은 특정 호스트와 연관됩니다. 그러나 하위 집합과 관련된 경우 활성화는 경로 규칙 평가 결과에 따라 달라집니다.

규칙 평가 프로세스의 첫 번째 단계는 요청 된 호스트에 해당하는 VirtualService의 경로 규칙이있는 경우 해당 경로 규칙을 평가하여 현재 요청이 라우팅 될 대상 서비스의 하위 집합 (특정 버전을 의미)을 결정합니다. 다음으로, 선택된 서브 세트에 대응하는 정책 세트가 평가된다면, 그 서브 세트가 적용되는지를 판정한다.

> NOTE
> 알고리즘의 한 가지 미묘한 점은 특정 하위 집합에 대해 정의 된 정책은 해당 하위 집합이 명시 적으로 라우팅되는 경우에만 적용된다는 것입니다. 예를 들어 검토 서비스에 대해 정의 된 유일한 규칙으로 다음 구성을 고려하십시오. 즉, 해당 VirtualService 정의에 경로 규칙이 없음을 의미합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: reviews
spec:
  host: reviews
  subsets:
  - name: v1
    labels:
      version: v1
    trafficPolicy:
      connectionPool:
        tcp:
          maxConnections: 100
</code></pre>

리뷰 서비스에 대해 정의 된 특정 경로 규칙이 없으므로 기본 라운드 로빈 라우팅 동작이 적용됩니다. "v1"이 유일한 실행중인 버전 인 경우 항상 "v1"인스턴스를 호출 할 수 있습니다. 그럼에도 불구하고 기본 라우팅이 더 낮은 수준에서 수행되기 때문에 위의 정책이 호출되지 않습니다. 규칙 평가 엔진은 최종 목적지를 인식하지 못하므로 하위 집합 정책을 요청과 일치시킬 수 없습니다.

위의 예를 다음 두 가지 방법 중 하나로 수정할 수 있습니다. 트래픽 정책을 `DestinationRule`의 한 수준 위로 이동하여 모든 버전에 적용 할 수 있습니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: reviews
spec:
  host: reviews
  trafficPolicy:
    connectionPool:
      tcp:
        maxConnections: 100
  subsets:
  - name: v1
    labels:
      version: v1
</code></pre>

또는 VirtualService 정의에서 서비스의 올바른 경로 규칙을 정의하는 것이 더 좋습니다. 예를 들어 '리뷰 : v1'에 대한 간단한 경로 규칙을 추가합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: reviews
spec:
  hosts:
  - reviews
  http:
  - route:
    - destination:
        host: reviews
        subset: v1
</code></pre>

기본 Istio 동작은 임의의 규칙을 설정하지 않고 모든 소스에서 모든 버전의 대상 서비스로 편리하게 트래픽을 전송하지만 버전 차별이 필요한대로 규칙이 필요할 것입니다. 따라서 처음부터 모든 서비스에 대한 기본 규칙을 설정하는 것이 일반적으로 Istio의 모범 사례로 간주됩니다.

##### 서비스 항목 (Service entries)

[ServiceEntry](https://istio.io/docs/reference/config/istio.networking.v1alpha3/#ServiceEntry)는 Istio가 내부적으로 유지 보수하는 항목을 서비스 레지스트리에 추가하는 데 사용됩니다. Istio 서비스 메쉬 외부의 서비스 요청을 가능하게하는 데 가장 일반적으로 사용됩니다. 예를 들어 다음 `ServiceEntry`를 사용하여 `* .foo.com` 도메인에서 호스팅되는 서비스에 대한 외부 호출을 허용 할 수 있습니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: ServiceEntry
metadata:
  name: foo-ext-svc
spec:
  hosts:
  - *.foo.com
  ports:
  - number: 80
    name: http
    protocol: HTTP
  - number: 443
    name: https
    protocol: HTTPS
</code></pre>

ServiceEntry의 대상은 hosts 필드를 사용하여 지정됩니다.이 필드는 정규화 된 또는 와일드 카드 도메인 이름이 될 수 있습니다. 메쉬에서 서비스에 액세스하도록 허용 된 하나 이상의 서비스로 구성된 흰색 목록 집합을 나타냅니다.

`ServiceEntry`는 외부 서비스 구성에만 국한되지 않습니다. 메쉬 내부 또는 메쉬 외부의 두 가지 유형이 있습니다. 메쉬 내부 항목은 다른 모든 내부 서비스와 비슷하지만 메쉬에 서비스를 명시 적으로 추가하는 데 사용됩니다. 이를 사용하여 관리되지 않는 인프라 (예 : Kubernetes 기반 서비스 메쉬에 추가 된 VM)를 포함하도록 서비스 메쉬를 확장하는 과정에서 서비스를 추가 할 수 있습니다. 메쉬 외부 항목은 메쉬 외부의 서비스를 나타냅니다. 상호 TLS 인증은 사용할 수 없으며 정책 적용은 내부 서비스 요청의 경우와 마찬가지로 서버 쪽이 아니라 클라이언트 쪽에서 수행됩니다.

서비스 항목은 일치하는 hosts를 사용하는 서비스를 참조하는 한 가상 서비스 및 대상 규칙과 함께 잘 작동합니다. 예를 들어 다음 규칙을 위 `ServiceEntry` 규칙과 함께 사용하여 `bar.foo.com` 에서 외부 서비스에 대한 호출에 대해 10 초의 시간 제한을 설정할 수 있습니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: bar-foo-ext-svc
spec:
  hosts:
    - bar.foo.com
  http:
  - route:
    - destination:
        host: bar.foo.com
    timeout: 10s
</code></pre>

트래픽을 리디렉션하고 전달하는 규칙, 재시도, timeout 및 오류 주입 정책을 정의하는 규칙은 모두 외부 대상에 지원됩니다. 그러나 외부 서비스의 여러 버전에 대한 개념이 없기 때문에 가중 (버전 기반) 라우팅은 불가능합니다.

외부 서비스 액세스에 대한 자세한 내용은 [egress 작업](https://istio.io/docs/tasks/traffic-management/egress/)을 참조하십시오.

##### 게이트웨이 (Gateways)

[게이트웨이](https://istio.io/docs/reference/config/istio.networking.v1alpha3/#Gateway)는 HTTP / TCP 트래픽에 대한 로드 밸런서를 구성합니다. 가장 일반적으로 메쉬의 가장자리에서 작동하여 응용 프로그램의 수신 트래픽을 활성화합니다.

Kubernetes Ingress와 달리 Istio Gateway는 L4-L6 기능 만 구성합니다 (예 : 노출 할 포트, TLS 구성). 그런 다음 사용자는 표준 Istio 규칙을 사용하여 VirtualService를 바인딩하여 게이트웨이에 들어오는 TCP 트래픽은 물론 HTTP 요청을 제어 할 수 있습니다.

예를 들어 다음의 간단한 게이트웨이는 bookinfo.com 호스트의 외부 HTTPS 트래픽을 메쉬에 넣을 수 있도록로드 밸런서를 구성합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: bookinfo-gateway
spec:
  servers:
  - port:
      number: 443
      name: https
      protocol: HTTPS
    hosts:
    - bookinfo.com
    tls:
      mode: SIMPLE
      serverCertificate: /tmp/tls.crt
      privateKey: /tmp/tls.key
</code></pre>

해당 라우트를 구성하려면 구성의 게이트웨이 필드를 사용하여 동일한 호스트에 대해 `VirtualService`를 정의하고 게이트웨이에 바인딩 해야 합니다.

<pre><code>
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: bookinfo
spec:
  hosts:
    - bookinfo.com
  gateways:
  - bookinfo-gateway # <---- bind to gateway
  http:
  - match:
    - uri:
        prefix: /reviews
    route:
    ...
</code></pre>

완전한 수신 게이트웨이 예제는 [ingress 작업](https://istio.io/docs/tasks/traffic-management/ingress/)을 참조하십시오.

주로 입구 트래픽을 관리하는 데 사용되지만 게이트웨이를 사용하여 완전히 내부 또는 송신 프록시를 모델링 할 수도 있습니다. 위치에 관계없이 모든 게이트웨이를 동일한 방식으로 구성하고 제어 할 수 있습니다. 자세한 내용은 [Gateway reference](https://istio.io/docs/reference/config/istio.networking.v1alpha3/#Gateway)를 참조하십시오.

