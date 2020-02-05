
# helm 
> 차트(chart) : 쿠버네티스 애플리케이션을 만들기위해 필요한 정보들의 묶음입니다.
> 컨피그(config) : 배포 가능한 객체들을 만들기 위해 패키지된 차트에 넣어서 사용할 수 있는 설정들을 가지고 있습니다.
> 릴리즈(release) : 특정 컨피그를 이용해서 실행중인 차트의 인스턴스 입니다.


C:\Program Files\Docker\Docker\resources\bin
D:\Project\tools
C:\ProgramData\chocolatey\bin
C:\Program Files\Kubernetes\Minikube
C:\Program Files\TortoiseGit\bin

D:\aa>kubectl -n kube-system create sa tiller
serviceaccount/tiller created

D:\aa>kubectl create clusterrolebinding tiller --clusterrole cluster-admin --serviceaccount=kube-system:tiller
clusterrolebinding.rbac.authorization.k8s.io/tiller created



helm init --service-account tiller
Creating C:\Users\ryu\.helm
Creating C:\Users\ryu\.helm\repository
Creating C:\Users\ryu\.helm\repository\cache
Creating C:\Users\ryu\.helm\repository\local
Creating C:\Users\ryu\.helm\plugins
Creating C:\Users\ryu\.helm\starters
Creating C:\Users\ryu\.helm\cache\archive
Creating C:\Users\ryu\.helm\repository\repositories.yaml
Adding stable repo with URL: https://kubernetes-charts.storage.googleapis.com
Adding local repo with URL: http://127.0.0.1:8879/charts
$HELM_HOME has been configured at C:\Users\ryu\.helm.
Error: error installing: the server could not find the requested resource

D:\aa>
