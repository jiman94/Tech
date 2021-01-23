
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml



kubectl create namespace argocd

helm repo add argo https://argoproj.github.io/argo-helm

helm install argocd argo/argo-cd \
-f https://gist.githubusercontent.com/pcrete/250896d4ff90ce2afa496c9f515b6be5/raw/0057742a532e8dfd578c139385537cd39de07f03/argocd-values.yaml \
--version 2.6.0 \
--namespace argocd


kubectl port-forward service/argocd-server -n argocd 8080:443

http://localhost:8080 

username: admin

kubectl get pods -n argocd -l app.kubernetes.io/name=argocd-server -o name | cut -d'/' -f 2


#


ArgoCD 대시 보드 및 배포 된 웹 응용 프로그램이 필요합니다
K8S 렌즈 IDE 를 사용 하여 클러스터를 훨씬 쉽게 작동 할 수 있습니다.

1. ArgoCD
Kubernetes 용 선언적 GitOps 지속적 배포 도구입니다. argoproj.github.io/argo-cd

1.1. 설정

kubectl create namespace argocd

helm repo add argo https://argoproj.github.io/argo-helm

kubectl port-forward service/argocd-server -n argocd 8080:443 

http://localhost:8080

kubectl create namespace argocd
helm repo add argo https://argoproj.github.io/argo-helm

helm install argocd argo/argo-cd \
-f https://gist.githubusercontent.com/pcrete/250896d4ff90ce2afa496c9f515b6be5/raw/0057742a532e8dfd578c139385537cd39de07f03/argocd-values.yaml \
--version 2.6.0 \
--namespace argocd


로그인 
username:
admin 
비밀번호 
kubectl get pods -n argocd -l app.kubernetes.io/name=argocd-server -o name | cut -d’/’ -f 2

1.2. ArgoCD CLI

ArgoCD는 매니페스트를 동기화
git 커밋 변경 사항에 해당하는 Kubernetes 리소스를 배포

3. GitLab CI를 사용한 CI / CD 파이프 라인

전체 파이프 라인을 설명하기 위해 만든 파이썬 웹 응용 프로그램 과는 Kubernetes 클러스터에서 실행되도록을 용기 화. 더 많은 컨텍스트를 위해 프로젝트 에서 dockerfile 을 체크 아웃 할 수 있습니다 . 애플리케이션을 컨테이너로 구축하면 GitLab CI가 단순히 명령을 실행 하여 코드를 통합하고 레지스트리로 푸시 할 수있는 CI / CD 파이프 라인을 용이하게합니다 .docker build

3.1. 지속적인 통합

파이프 라인이 어떻게 구성되는지 살펴 보겠습니다. 먼저 작업 실행 단계 (예 : 빌드, 테스트 및 배포) gitlab-ci.yml를 정의 하는 파일을 저장소의 루트 디렉터리에 만들어야합니다 .

위의 예에서 볼 수 있듯이 docker 이미지는 빌드되고 이미지 버전 (커밋 sha가있는 브랜치 이름)으로 태그가 지정된 다음 latest캐싱에 재사용 할 태그로 다시 태그 가 지정됩니다.

또한 환경 변수를 사용하고 있음을 알 수 있습니다 . 이러한 변수는 작업을 이식 가능하게 만들기 위해 동적이며 하드 코딩 된 여러 값을 방지하기위한 모범 사례입니다. 특히 GitLab에서 제공하는 내장 또는 미리 정의 된 변수 가 있습니다. 여기 에서 전체 목록을 찾을 수 있습니다 . 마지막으로 프로젝트 저장소에서 저장되고 여러 프로젝트와 공유 할 수있는 그룹 수준 변수 를 정의 합니다. 쉬운 업데이트 를 위해 자격 증명 과 전역 구성 을 저장하는 것이 가장 좋습니다 . 예를 들면

Docker 이미지를 레지스트리에 푸시하기위한 CI_REGISTRY 자격 증명.
매니페스트 저장소를 업데이트하기위한 SSH_PRIVATE.

3.2. 지속적인 전달

GitLab CI가 ArgoCD를 만나는 곳입니다.

시작하기 전에이 그림으로 돌아가 보겠습니다. 취소 선 텍스트는 CI 부분에서 수행 한 작업을 나타냅니다.

Kubernetes 매니페스트에서 방명록 앱을 배포하는 ArgoCD 소개에서 와 마찬가지로 ArgoCD는 Kustomize 및 Helm과 같은 다양한 유형의 템플릿 도구 도 지원합니다 . 여기서는 템플릿 또는 K8 리소스 모음을 정의하는 Helm을 사용합니다. 구성 가능한 값 파일을 사용하여 다양한 유형의 앱 또는 배포 단계를 유연하게 나타낼 수 있습니다.

투구 차트
설정하려면 다음 명령을 실행하여 webapp-chart 라는 helm 차트를 만듭니다 helm create webapp-chart.. 프로젝트는 다음과 같이 구성됩니다.

Helm에 익숙하지 않다면 " 10 분 안에 Helm 차트를 만드는 방법 "게시물을 읽어 보면 좋은 출발점이 될 것입니다.

Chart.yaml파일을 보면 차트 정보와 애플리케이션 버전을 설명합니다. 이 appVersion매개 변수는 새 버전이 푸시 될 때 CI가 업데이트되는 위치입니다. 다음으로, values.yaml파일은 템플릿에 전달할 선언 변수입니다. 예를 들어 deployment.yaml 템플릿이 컨테이너 이미지의 값을 사용 repository하고 appVersion정의 하는 방법을 확인할 수 있습니다 .


이제 다음과 같은 패턴을 따르는 두 개의 저장소 가 설정되었습니다.

"하나는 앱 소스 코드 용, 다른 하나는 매니페스트 용", 5 가지 GitOps 모범 사례

매니페스트 단계 업데이트
우리는 이름이 다른 작업, 추가 다시 GitLab CI 파이프 라인에 돌아올 update_manifest에서 deploy무대. 그러면 Helm 매니페스트 저장소에 대한 새로운 변경 사항이 복제되고 커밋됩니다. 을 업데이트하기 위해 경량의 명령 줄 YAML 프로세서 인 YQappVersion 를 적용 하여 using 경로 표현식 을 체계적으로 편집합니다 .Chart.yaml


매니페스트 업데이트를 위해 전역 변수의 SSH_PRIVATE_KEY에 해당하는 공개 키인 배포 키 가 webapp-chart repo로 설정되어 CI 파이프 라인에 대한 쓰기 액세스를 허용 합니다.

파이프 라인
GitLab은 해당 파이프 라인에 대해 실행 된 작업을 시각화하는 그래프를 제공합니다. 다음은 두 단계 (빌드 및 배포)의 모습입니다. 또한, 배포 단계, tag_latest_image및 update_manifest병렬로 실행된다.


4. ArgoCD에서 Kubernetes로
argocd app create다시 실행 하여 자동화 된 동기화 정책 , 정리 , 자가 복구가 활성화 된 ArgoCD 애플리케이션 리소스를 설정합니다 .


kubectl create namespace hello-gitops

argocd app create webapp \
--repo https://gitlab.com/gitops-argocd-demo/webapp-chart.git \
--path . \
--dest-server https://kubernetes.default.svc \
--dest-namespace hello-gitops \
--sync-policy automated \
--auto-prune \
--self-heal


Unable to create application: application spec is invalid: InvalidSpecError: repository not accessible: unexpected client error: unexpected requesting "https://gitlab.com/gitops-argocd-demo/webapp-chart.git%20/info/refs?service=git-upload-pack" status code: 302



Cluster에 Argo CD 설치

# argocd 네임스페이스 생성 

$ kubectl create namespace argocd
  
# Argo CD 배포

$ kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

# Argo CD API Server에서 외부 통신을 할 수 있도록 Argo CD의 Service의 Type을 Load Balancer로 변경(AWS에서는 Classic Load Balancer로 배포됨)

$ kubectl patch svc argocd-server -n argocd -p '{"spec": {"type": "LoadBalancer"}}'
쿠버네티스 클러스터 내부에 Argo CD의 리소스를 배포하기 위해 먼저 argocd라는 네임스페이스를 만들었고, 이 네임스페이스 안에 Argo CD를 배포하였다.
또한 Argo CD API Server가 있는데, 이 서버가 대시보드를 제공한다.
따라서 해당 Pod의 Service를 통해 외부 접속을 해야 하는데 이를 위해서 Service의 타입을 LoadBalancer로 변경해준다.(Default는 ClusterIP로 설정되어있음)
Argo CD는 Argo CD만의 CLI(Command Line Interface)를 제공한다.
이 CLI를 통해 Argo CD를 제어할 수 있다.
Argo CD CLI는 위에서 설명한 Argo CD API Server에게 명령을 전달한다.
이 포스트에서는 Argo CD CLI를 통해 초기 ADMIN 계정의 패스워드를 변경할 것이다.
따라서 Argo CD CLI도 설치한다. (MacOS를 기준으로 설명한다.)

$ brew tap argoproj/tap 
$ brew install argoproj/tap/argocd

# 설치 확인을 위해 argocd 명령어 실행

$ argocd
argocd controls a Argo CD server

Use "argocd [command] --help" for more information about a command.
혹시 MacOS가 아닌 유저 or 리눅스 유저인 경우 아래 링크를 참고한다.

Installation - Argo CD - Declarative GitOps CD for Kubernetes

 You can download the latest Argo CD version from the latest release page of this repository, which will include the…

argoproj.github.io
Argo CD CLI 설치를 완료했으면 초기 ADMIN의 계정 설정을 진행해보자.
아래의 커맨드를 따라 진행한다.

ARGOCD_SERVER=`kubectl get pods -n argocd -l app.kubernetes.io/name=argocd-server -o name | cut -d'/' -f 2` 
ARGOCD_SERVER_HOST=`kubectl get svc argocd-server -o json -n argocd | jq -r '.status.loadBalancer.ingress[0].hostname'`
# argocd 커맨드라인 인터페이스는 HTTP2/gRPC 프로토콜을 통해 통신하므로 
# 로드밸런서 혹은 외부 통신을 담당하는 라우터가 HTTP2/gRPC 프로토콜을 완벽히 지원해야함.
# 하지만 AWS에서 제공하는 ALB 혹은 Classic LB는 HTTP2/gRPC 프로토콜을 완벽하게 지원하지 않기 때문에
# --grpc-web 이라는 옵션을 추가적으로 붙여줘야 함.
$ argocd login <ARGOCD_SERVER_HOST>:80 --grpc-web # AWS EKS 혹은 HTTP2/gRPC 프로토콜을 완벽히 지원해 주지 않는 로드밸런서만 해당
# 그 외의 경우
$ argocd login <ARGOCD_SERVER_HOST>
WARNING: server certificate had error: x509: certificate is valid for localhost, argocd-server, argocd-server.argocd, argocd-server.argocd.svc, argocd-server.argocd.svc.cluster.local, 
not a123456dd12ab11baba0a123a1234567-1234567890.ap-northeast-2.elb.amazonaws.com. Proceed insecurely (y/n)? y
Username: admin
Password: # ARGOCD_SERVER와 동일
'admin' logged in successfully
Context 'a123456dd12ab11baba0a123a1234567-1234567890.ap-northeast-2.elb.amazonaws.com:80' updated
# -------------------------------
# default 계정 정보는 아래와 같음
# username : admin
# password : ARGOCD_SERVER와 동일
# -------------------------------
# Argo CD 비밀번호 변경

$ argocd account update-password
*** Enter current password: # ARGOCD_SERVER와 동일
*** Enter new password: # 변경할 비밀번호 입력
*** Confirm new password: # 변경할 비밀번호 재입력(확인)
이제 초기 계정 설정을 마쳤으니, 브라우저를 통해 Load Balancer의 엔드포인트로 접속하여 Argo CD 대시보드로 들어가 로그인을 해보자.
만약 엔드포인트를 찾기 어렵다면 간단히 아래 커맨드를 통해 확인할 수 있다.

$ kubectl get svc argocd-server -n argocd -o json | jq -r '.status.loadBalancer.ingress[0].hostname'

브라우저를 통해 접속하면 아래와 같이 대시보드 로그인 화면을 확인할 수 있다.
한번 로그인을 진행해보자.
로그인을 진행하고 처음 들어가면 이러한 화면이 보일 것이다.
여기에서 실제 쿠버네티스 클러스터 내부에 배포될 서비스들을 생성하고 관리하게 된다.
그러기에 앞서 이전에 이야기 했던 것처럼 Argo CD는 특정 원격 저장소(GitOps Repository)를 알고 있어야 한다.
따라서 우선적으로 특정 원격 저장소(GitOps Repository)를 등록해 주어야 한다.
아래 그림을 따라 진행한다.

왼쪽 메뉴에서 두번째 아이콘을 클릭하여 ‘Repositories’를 클릭한다.
여기서는 HTTPS를 통해 Repository와 연결할 것이기 때문에 ‘Connect Repo Using Https’를 클릭한다.
해당 포스트에서 테스트를 위해 사용할 GitOps Repository이다. 해당 url을 복사한다.
‘Repository URL’에 복사해둔 GitOps Repository의 url을 붙여넣기 한다. 해당 Repository는 Public이기 때문에 username과 password를 입력하지 않았다.
만약 자신의 Private GitOps Repository 가 있다면 위 그림과 같이 해당 Repo에 접근 권한이 있는 계정의 정보를 입력한다.
정상적으로 연결이 완료 되었다면 위와 같이 확인할 수 있다.
이렇게 간단히 특정 원격 저장소(GitOps Repository)를 등록했다.
이제 실제 서비스를 Argo CD를 통해 배포해보자.
왼쪽 메뉴 중 첫번째 아이콘을 클릭하여 초기 화면으로 돌아온 후 상단의 ‘New APP’ 버튼을 클릭한다.
‘New APP’ 버튼을 클릭하면 볼 수 있는 화면. 이 화면의 input box & select box 만으로 쿠버네티스 클러스터에 서비스를 배포할 수 있다.
새로운 앱을 배포하기 위해 위와 같이 insert box와 select box를 설정한다. 배포할 서비스의 이름은 hello-world이고 이 서비스의 배포를 위해서 GitOps Repo의 /resources/base 디렉터리를 참조한다.
실제 서비스가 배포될 목적지(Destination)가 어디인지 설정한다. 기본값은 Argo CD가 설치된 쿠버네티스 클러스터 내부이다. 목적지를 설정하고나서 상단의 ‘Create’버튼을 클릭하면 서비스 배포를 위한 환경이 구성된다.
서비스 배포를 위해 구성된 환경(App)의 모습. 이 App을 클릭하면 상세 화면으로 이동할 수 있다.