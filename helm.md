

C:\Program Files\Docker\Docker\resources\bin
D:\Project\tools
C:\ProgramData\chocolatey\bin
C:\Program Files\Kubernetes\Minikube
C:\Program Files\TortoiseGit\bin

D:\aa>kubectl -n kube-system create sa tiller
serviceaccount/tiller created

D:\aa>kubectl create clusterrolebinding tiller --clusterrole cluster-admin --serviceaccount=kube-system:tiller
clusterrolebinding.rbac.authorization.k8s.io/tiller created



D:\aa>D:\Project\tools/helm.exe init --service-account tiller
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
