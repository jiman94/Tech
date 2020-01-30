
C:\Program Files\Docker\Docker\resources\bin
D:\Project\tools
C:\ProgramData\chocolatey\bin
C:\Program Files\Kubernetes\Minikube
C:\Program Files\TortoiseGit\bin


1. Enabling Hyper-V
Open a PowerShell console as Administrator


2. Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All

PS C:\WINDOWS\system32> Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All

Path          :
Online        : True
RestartNeeded : False


3. minikube start --vm-driver=hyperv 

PS C:\WINDOWS\system32> minikube start --vm-driver=hyperv
* minikube v1.6.2 on Microsoft Windows 10 Pro 10.0.17763 Build 17763
* Selecting 'hyperv' driver from user configuration (alternates: [])
* Downloading VM boot image ...
    > minikube-v1.6.0.iso.sha256: 65 B / 65 B [--------------] 100.00% ? p/s 0s
    > minikube-v1.6.0.iso: 150.93 MiB / 150.93 MiB [-] 100.00% 5.01 MiB p/s 30s
* Creating hyperv VM (CPUs=2, Memory=2000MB, Disk=20000MB) ...
* Preparing Kubernetes v1.17.0 on Docker '19.03.5' ...
* Downloading kubeadm v1.17.0
* Downloading kubelet v1.17.0
* Pulling images ...
* Launching Kubernetes ...
* Waiting for cluster to come online ...
* Done! kubectl is now configured to use "minikube"
! C:\Program Files\Docker\Docker\resources\bin\kubectl.exe is version 1.15.5, and is incompatible with Kubernetes 1.17.0. You will need to update C:\Program Files\Docker\Docker\resources\bin\kubectl.exe or use 'minikube kubectl' to connect with this cluster
PS C:\WINDOWS\system32>

4. kubectl config use-context minikube

PS C:\WINDOWS\system32> kubectl config use-context minikube
Switched to context "minikube".

PS C:\WINDOWS\system32> kubectl get pods --context=minikube
No resources found.


5. minikube dashboard

PS C:\WINDOWS\system32> minikube dashboard
* Verifying dashboard health ...
* Launching proxy ...
* Verifying proxy health ...
* Opening http://127.0.0.1:51980/api/v1/namespaces/kubernetes-dashboard/services/http:kubernetes-dashboard:/proxy/ in your default browser...



Quickstart

PS C:\WINDOWS\system32> kubectl create deployment hello-minikube --image=k8s.gcr.io/echoserver:1.10
deployment.apps/hello-minikube created

PS C:\WINDOWS\system32> kubectl expose deployment hello-minikube --type=NodePort --port=8080
service/hello-minikube exposed

PS C:\WINDOWS\system32> kubectl get pod
NAME                              READY   STATUS    RESTARTS   AGE
hello-minikube-797f975945-z526f   1/1     Running   0          48s
PS C:\WINDOWS\system32> minikube service hello-minikube --url
http://172.18.64.250:30339
PS C:\WINDOWS\system32>


PS C:\WINDOWS\system32> kubectl delete services hello-minikube
service "hello-minikube" deleted

PS C:\WINDOWS\system32> kubectl delete deployment hello-minikube
deployment.apps "hello-minikube" deleted

PS C:\WINDOWS\system32> minikube stop
* Stopping "minikube" in hyperv ...
* Powering off "minikube" via SSH ...
* "minikube" stopped.

PS C:\WINDOWS\system32> minikube delete
* Successfully powered off Hyper-V. minikube driver -- hyperv
* Deleting "minikube" in hyperv ...
* The "minikube" cluster has been deleted.
* Successfully deleted profile "minikube"
PS C:\WINDOWS\system32>




Install with PowerShell.exe

https://chocolatey.org/docs/installation#non-administrative-install

Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))


https://github.com/kubernetes/minikube/releases/tag/v1.6.2

https://minikube.sigs.k8s.io/docs/reference/drivers/hyperv/
