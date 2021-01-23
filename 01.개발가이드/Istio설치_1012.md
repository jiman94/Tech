# AWS EKS에 Istio 설치 및 삭제하기 - kubernetes에 istio 설치 및 삭제
 
1. 사전준비

1) AWS EKS 클러스터 설치(AWS EKS를 활용한 쿠버네티스 클러스터 구축 참고)
2) AWS EKS에 배포 파이프라인 구축(AWS EKS에서 CodePipeline을 활용한 스프링부트 서비스 배포 참고)
3) AWS EC2 생성 후 프라이빗 서브넷으로 구축된 쿠버네티스 클러스터와 연동(AWS EC2에 AWS EKS 쿠버네티스 클러스터 엑세스 권한 제공 참고)
  
2. AWS EKS 클러스터에 Istio 설치하기

이제 AWS EKS 쿠버네티스 클러스터에 Istio를 설치해보자.
모든 실습은 AWS EKS 와 연결되어 있는 별도의 EC2 인스턴스를 통해서 진행할 것이다(사전준비 3에서 생성한 EC2 인스턴스).
 
2-1. Istio 다운로드

1) 우선 최신버전의 Istio 설치 파일을 다운받자.

$ curl -L https://istio.io/downloadIstio | sh -

or 

$ curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.7.3 TARGET_ARCH=x86_64 sh -
 
2) 설치가 완료되었으면 설치된 디렉토리로 이동한다. 
$ cd istio-1.7.3
 
3) 다음으로 istioctl 클라이언트를 PATH에 추가하자

$ export PATH=$PWD/bin:$PATH
cp /bin/istioctl /bin

2-2. Istio 설치

$ istioctl install

$ istioctl install --manifests=manifests/
 
$ istioctl install --set profile=demo
 
$ istioctl profile list

2-3. Istio 설치 체크

$ kubectl get deploy -n istio-system


$ kubectl -n istio-system get IstioOperator installed-state -o yaml > installed-state.yaml

3. AWS EKS 클러스터에 Istio 삭제하기

#### AWS EKS 쿠버네티스 클러스터에서 Istio를 삭제해보자.
만약 Istio를 완벽하게 삭제하고 싶다면(Istio의 모든 리소스를 삭제하고 싶다면) 아래와 같이 명령어를 수행하면 된다.
$ istioctl x uninstall --purge
 