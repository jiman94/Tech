# Windows 전용 패키지 매니저 Chocolatey 설치 및 사용하기


자체 운영 → 설정 관리 도구 → 가상 머신 → 클라우드 → PaaS → 도커 → 쿠버네티스 → 서비스메시


@powershell -NoProfile -ExecutionPolicy Bypass -Command "iex ((new-object net.webclient).DownloadString('https://chocolatey.org/install.ps1'))" && SET PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin


choco upgrade chocolatey

https://kubernetes.io/docs/tasks/tools/install-kubectl/

curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.17.0/bin/windows/amd64/kubectl.exe


choco install kubernetes-cli
Test to ensure the version you installed is up-to-date:

kubectl version --client
Navigate to your home directory:

cd %USERPROFILE%
Create the .kube directory:

mkdir .kube
Change to the .kube directory you just created:

cd .kube
Configure kubectl to use a remote Kubernetes cluster:

New-Item config -type file

