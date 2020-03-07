Mac 에서 kubernetes Parallels 을  이용해 설치

0. 구성
 마스터노드 한 개 와 워커노드 두 개로 구성

1. Parallels 설치

2. VM 생성

메모리는 2048MB 
프로세서에서 cpu갯수를 1개로

3. Ubuntu 설치

k8smaster  (10.211.55.9) 
k8sworker1 (10.211.55.10) 
k8sworker2 (10.211.55.11) 


4. SSH 설치 

service --status-all | grep +
[ + ]  ssh

sudo apt-get install openssh-server 

sudo netstat -antp


5. SSH 접속 

ifconfig 

enp0s5: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 10.211.55.9  netmask 255.255.255.0  broadcast 10.211.55.255


enp0s5: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 10.211.55.10  netmask 255.255.255.0  broadcast 10.211.55.255        

enp0s5: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 10.211.55.11  netmask 255.255.255.0  broadcast 10.211.55.255


ssh jmryu@10.211.55.9
ssh jmryu@10.211.55.10
ssh jmryu@10.211.55.11

6. Kubernetes & Docker 설치 전 설정
스왑 메모리 비활성화

sudo swapoff -a
sudo sed -i '2s/^/#/' /etc/fstab


sudo vi /etc/docker/daemon.json

{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2"
}


7. Docker 설치

sudo apt update && sudo apt-get update

sudo apt-get install apt-transport-https ca-certificates curl software-properties-common -y

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo apt-key fingerprint 0EBFCD88

sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

sudo apt-get update

sudo apt-get update && sudo apt-get install \
  containerd.io=1.2.10-3 \
  docker-ce=5:19.03.4~3-0~ubuntu-$(lsb_release -cs) \
  docker-ce-cli=5:19.03.4~3-0~ubuntu-$(lsb_release -cs)

8. docker 테스트

sudo docker run hello-world

sudo mkdir -p /etc/systemd/system/docker.service.d

sudo systemctl daemon-reload

sudo systemctl restart docker

9. Kubernetes 설치

sudo apt-get update && sudo apt-get upgrade

curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -

cat <<EOF | sudo tee /etc/apt/sources.list.d/kubernetes.list
deb https://apt.kubernetes.io/ kubernetes-xenial main
EOF

sudo apt-get update

sudo apt-get install -y kubelet kubeadm kubectl

sudo apt-mark hold kubelet kubeadm kubectl

kubeadm version

kubelet --version

kubectl version


10.  

sudo usermod -a -G docker jiman



8. 워커 노드 VM 생성

hostnamectl set-hostname k8smaster
hostnamectl set-hostname k8sworker1
hostnamectl set-hostname k8sworker2

sudo vi /etc/hostname 
k8smaster  (10.211.55.9) 
k8snode1 (10.211.55.10) 
k8snode2 (10.211.55.11) 

sudo vi /etc/hosts
10.211.55.9 k8smaster
10.211.55.10 k8snode1
10.211.55.11 k8snode2


sudo vi /usr/lib/systemd/system/docker.service


9. master 노드 설정
스왑 메모리 비활성화

sudo swapoff -a
sudo sed -i '2s/^/#/' /etc/fstab


sudo kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=10.211.55.9


mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

10. 워커 노드 설정

sudo kubeadm join 192.168.0.9:6443 --token ix2ak9.nkg5p2smfkdke7hu \
    --discovery-token-ca-cert-hash sha256:cc1138bbb9a51bfaaf268a217fc55adeb1e49b38f2d0ae449009cd2716047324

kubectl get nodes


11. Dashboard 설치


12. 정보 확인 

jiman@k8smaster:~$ sudo kubectl get nodes
NAME        STATUS     ROLES    AGE     VERSION
k8smaster   NotReady   master   10m     v1.17.3
k8snode1    NotReady   <none>   4m30s   v1.17.3
k8snode2    NotReady   <none>   4m27s   v1.17.3
jiman@k8smaster:~$ sudo kubectl apply -f https://raw.githubusercontent.com/kubetm/kubetm.github.io/master/sample/practice/appendix/gcp-kubernetes-dashboard.yaml
secret/kubernetes-dashboard-certs created
serviceaccount/kubernetes-dashboard created
role.rbac.authorization.k8s.io/kubernetes-dashboard-minimal created
rolebinding.rbac.authorization.k8s.io/kubernetes-dashboard-minimal created
deployment.apps/kubernetes-dashboard created
service/kubernetes-dashboard created
clusterrolebinding.rbac.authorization.k8s.io/kubernetes-dashboard created
jiman@k8smaster:~$ 




jiman@k8smaster:~$ sudo kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=10.211.55.9
W0307 14:21:51.672725   25752 validation.go:28] Cannot validate kube-proxy config - no validator is available
W0307 14:21:51.673317   25752 validation.go:28] Cannot validate kubelet config - no validator is available
[init] Using Kubernetes version: v1.17.3
[preflight] Running pre-flight checks
[preflight] Pulling images required for setting up a Kubernetes cluster
[preflight] This might take a minute or two, depending on the speed of your internet connection
[preflight] You can also perform this action in beforehand using 'kubeadm config images pull'

[kubelet-start] Writing kubelet environment file with flags to file "/var/lib/kubelet/kubeadm-flags.env"
[kubelet-start] Writing kubelet configuration to file "/var/lib/kubelet/config.yaml"
[kubelet-start] Starting the kubelet
[certs] Using certificateDir folder "/etc/kubernetes/pki"
[certs] Generating "ca" certificate and key
[certs] Generating "apiserver" certificate and key
[certs] apiserver serving cert is signed for DNS names [k8smaster kubernetes kubernetes.default kubernetes.default.svc kubernetes.default.svc.cluster.local] and IPs [10.96.0.1 10.211.55.9]
[certs] Generating "apiserver-kubelet-client" certificate and key
[certs] Generating "front-proxy-ca" certificate and key
[certs] Generating "front-proxy-client" certificate and key
[certs] Generating "etcd/ca" certificate and key
[certs] Generating "etcd/server" certificate and key
[certs] etcd/server serving cert is signed for DNS names [k8smaster localhost] and IPs [10.211.55.9 127.0.0.1 ::1]
[certs] Generating "etcd/peer" certificate and key
[certs] etcd/peer serving cert is signed for DNS names [k8smaster localhost] and IPs [10.211.55.9 127.0.0.1 ::1]
[certs] Generating "etcd/healthcheck-client" certificate and key
[certs] Generating "apiserver-etcd-client" certificate and key
[certs] Generating "sa" key and public key
[kubeconfig] Using kubeconfig folder "/etc/kubernetes"
[kubeconfig] Writing "admin.conf" kubeconfig file
[kubeconfig] Writing "kubelet.conf" kubeconfig file
[kubeconfig] Writing "controller-manager.conf" kubeconfig file
[kubeconfig] Writing "scheduler.conf" kubeconfig file
[control-plane] Using manifest folder "/etc/kubernetes/manifests"
[control-plane] Creating static Pod manifest for "kube-apiserver"
[control-plane] Creating static Pod manifest for "kube-controller-manager"
W0307 14:24:43.251075   25752 manifests.go:214] the default kube-apiserver authorization-mode is "Node,RBAC"; using "Node,RBAC"
[control-plane] Creating static Pod manifest for "kube-scheduler"
W0307 14:24:43.251862   25752 manifests.go:214] the default kube-apiserver authorization-mode is "Node,RBAC"; using "Node,RBAC"
[etcd] Creating static Pod manifest for local etcd in "/etc/kubernetes/manifests"
[wait-control-plane] Waiting for the kubelet to boot up the control plane as static Pods from directory "/etc/kubernetes/manifests". This can take up to 4m0s
[apiclient] All control plane components are healthy after 20.001845 seconds
[upload-config] Storing the configuration used in ConfigMap "kubeadm-config" in the "kube-system" Namespace
[kubelet] Creating a ConfigMap "kubelet-config-1.17" in namespace kube-system with the configuration for the kubelets in the cluster
[upload-certs] Skipping phase. Please see --upload-certs
[mark-control-plane] Marking the node k8smaster as control-plane by adding the label "node-role.kubernetes.io/master=''"
[mark-control-plane] Marking the node k8smaster as control-plane by adding the taints [node-role.kubernetes.io/master:NoSchedule]
[bootstrap-token] Using token: 3h18xp.glp0rrumft448yio
[bootstrap-token] Configuring bootstrap tokens, cluster-info ConfigMap, RBAC Roles
[bootstrap-token] configured RBAC rules to allow Node Bootstrap tokens to post CSRs in order for nodes to get long term certificate credentials
[bootstrap-token] configured RBAC rules to allow the csrapprover controller automatically approve CSRs from a Node Bootstrap Token
[bootstrap-token] configured RBAC rules to allow certificate rotation for all node client certificates in the cluster
[bootstrap-token] Creating the "cluster-info" ConfigMap in the "kube-public" namespace
[kubelet-finalize] Updating "/etc/kubernetes/kubelet.conf" to point to a rotatable kubelet client certificate and key
[addons] Applied essential addon: CoreDNS
[addons] Applied essential addon: kube-proxy

Your Kubernetes control-plane has initialized successfully!

To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

Then you can join any number of worker nodes by running the following on each as root:

kubeadm join 10.211.55.9:6443 --token 3h18xp.glp0rrumft448yio \
    --discovery-token-ca-cert-hash sha256:7dfe886d2edb66792f7d5114bb22752ba39436193af966abb433f2c4ef0e0edb 
jiman@k8smaster:~$ 


jmryu@k8snode2:~$ sudo kubeadm join 10.211.55.9:6443 --token 3h18xp.glp0rrumft448yio \
>     --discovery-token-ca-cert-hash sha256:7dfe886d2edb66792f7d5114bb22752ba39436193af966abb433f2c4ef0e0edb
W0307 14:30:49.403719   22604 join.go:346] [preflight] WARNING: JoinControlPane.controlPlane settings will be ignored when control-plane flag is not set.
[preflight] Running pre-flight checks
[preflight] Reading configuration from the cluster...
[preflight] FYI: You can look at this config file with 'kubectl -n kube-system get cm kubeadm-config -oyaml'
[kubelet-start] Downloading configuration for the kubelet from the "kubelet-config-1.17" ConfigMap in the kube-system namespace
[kubelet-start] Writing kubelet configuration to file "/var/lib/kubelet/config.yaml"
[kubelet-start] Writing kubelet environment file with flags to file "/var/lib/kubelet/kubeadm-flags.env"
[kubelet-start] Starting the kubelet
[kubelet-start] Waiting for the kubelet to perform the TLS Bootstrap...

This node has joined the cluster:
* Certificate signing request was sent to apiserver and a response was received.
* The Kubelet was informed of the new secure connection details.

Run 'kubectl get nodes' on the control-plane to see this node join the cluster.

jmryu@k8snode2:~$ 



jmryu@k8snode1:~$ sudo kubeadm join 10.211.55.9:6443 --token 3h18xp.glp0rrumft448yio \
>     --discovery-token-ca-cert-hash sha256:7dfe886d2edb66792f7d5114bb22752ba39436193af966abb433f2c4ef0e0edb
W0307 14:30:42.154056   22673 join.go:346] [preflight] WARNING: JoinControlPane.controlPlane settings will be ignored when control-plane flag is not set.
[preflight] Running pre-flight checks
[preflight] Reading configuration from the cluster...
[preflight] FYI: You can look at this config file with 'kubectl -n kube-system get cm kubeadm-config -oyaml'
[kubelet-start] Downloading configuration for the kubelet from the "kubelet-config-1.17" ConfigMap in the kube-system namespace
[kubelet-start] Writing kubelet configuration to file "/var/lib/kubelet/config.yaml"
[kubelet-start] Writing kubelet environment file with flags to file "/var/lib/kubelet/kubeadm-flags.env"
[kubelet-start] Starting the kubelet
[kubelet-start] Waiting for the kubelet to perform the TLS Bootstrap...

This node has joined the cluster:
* Certificate signing request was sent to apiserver and a response was received.
* The Kubelet was informed of the new secure connection details.

Run 'kubectl get nodes' on the control-plane to see this node join the cluster.

jmryu@k8snode1:~$ 


jiman@k8smaster:~$ kubectl get pods --all-namespaces 
NAMESPACE     NAME                                    READY   STATUS    RESTARTS   AGE
kube-system   coredns-6955765f44-6qvmx                0/1     Pending   0          20m
kube-system   coredns-6955765f44-hfz5h                0/1     Pending   0          20m
kube-system   etcd-k8smaster                          1/1     Running   0          20m
kube-system   kube-apiserver-k8smaster                1/1     Running   0          20m
kube-system   kube-controller-manager-k8smaster       1/1     Running   0          20m
kube-system   kube-proxy-5jwl6                        1/1     Running   0          14m
kube-system   kube-proxy-6st5m                        1/1     Running   0          20m
kube-system   kube-proxy-m6lfc                        1/1     Running   0          14m
kube-system   kube-scheduler-k8smaster                1/1     Running   0          20m
kube-system   kubernetes-dashboard-56d9989b67-2pt9q   0/1     Pending   0          9m52s
jiman@k8smaster:~$ 


jiman@k8smaster:~$ kubectl get all
NAME                 TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)   AGE
service/kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP   16m
jiman@k8smaster:~$ kubectl get no
NAME        STATUS     ROLES    AGE   VERSION
k8smaster   NotReady   master   16m   v1.17.3
k8snode1    NotReady   <none>   10m   v1.17.3
k8snode2    NotReady   <none>   10m   v1.17.3
jiman@k8smaster:~$ kubectl get nodes -o wide
NAME        STATUS     ROLES    AGE   VERSION   INTERNAL-IP    EXTERNAL-IP   OS-IMAGE             KERNEL-VERSION      CONTAINER-RUNTIME
k8smaster   NotReady   master   16m   v1.17.3   10.211.55.9    <none>        Ubuntu 18.04.4 LTS   4.15.0-88-generic   docker://19.3.7
k8snode1    NotReady   <none>   11m   v1.17.3   10.211.55.10   <none>        Ubuntu 18.04.4 LTS   4.15.0-88-generic   docker://19.3.7
k8snode2    NotReady   <none>   11m   v1.17.3   10.211.55.11   <none>        Ubuntu 18.04.4 LTS   4.15.0-88-generic   docker://19.3.7
jiman@k8smaster:~$ kubectl get namespace
NAME              STATUS   AGE
default           Active   18m
kube-node-lease   Active   18m
kube-public       Active   18m
kube-system       Active   18m
jiman@k8smaster:~$ 
jiman@k8smaster:~$ 
jiman@k8smaster:~$ kubectl api-resources 
NAME                              SHORTNAMES   APIGROUP                       NAMESPACED   KIND
bindings                                                                      true         Binding
componentstatuses                 cs                                          false        ComponentStatus
configmaps                        cm                                          true         ConfigMap
endpoints                         ep                                          true         Endpoints
events                            ev                                          true         Event
limitranges                       limits                                      true         LimitRange
namespaces                        ns                                          false        Namespace
nodes                             no                                          false        Node
persistentvolumeclaims            pvc                                         true         PersistentVolumeClaim
persistentvolumes                 pv                                          false        PersistentVolume
pods                              po                                          true         Pod
podtemplates                                                                  true         PodTemplate
replicationcontrollers            rc                                          true         ReplicationController
resourcequotas                    quota                                       true         ResourceQuota
secrets                                                                       true         Secret
serviceaccounts                   sa                                          true         ServiceAccount
services                          svc                                         true         Service
mutatingwebhookconfigurations                  admissionregistration.k8s.io   false        MutatingWebhookConfiguration
validatingwebhookconfigurations                admissionregistration.k8s.io   false        ValidatingWebhookConfiguration
customresourcedefinitions         crd,crds     apiextensions.k8s.io           false        CustomResourceDefinition
apiservices                                    apiregistration.k8s.io         false        APIService
controllerrevisions                            apps                           true         ControllerRevision
daemonsets                        ds           apps                           true         DaemonSet
deployments                       deploy       apps                           true         Deployment
replicasets                       rs           apps                           true         ReplicaSet
statefulsets                      sts          apps                           true         StatefulSet
tokenreviews                                   authentication.k8s.io          false        TokenReview
localsubjectaccessreviews                      authorization.k8s.io           true         LocalSubjectAccessReview
selfsubjectaccessreviews                       authorization.k8s.io           false        SelfSubjectAccessReview
selfsubjectrulesreviews                        authorization.k8s.io           false        SelfSubjectRulesReview
subjectaccessreviews                           authorization.k8s.io           false        SubjectAccessReview
horizontalpodautoscalers          hpa          autoscaling                    true         HorizontalPodAutoscaler
cronjobs                          cj           batch                          true         CronJob
jobs                                           batch                          true         Job
certificatesigningrequests        csr          certificates.k8s.io            false        CertificateSigningRequest
leases                                         coordination.k8s.io            true         Lease
endpointslices                                 discovery.k8s.io               true         EndpointSlice
events                            ev           events.k8s.io                  true         Event
ingresses                         ing          extensions                     true         Ingress
ingresses                         ing          networking.k8s.io              true         Ingress
networkpolicies                   netpol       networking.k8s.io              true         NetworkPolicy
runtimeclasses                                 node.k8s.io                    false        RuntimeClass
poddisruptionbudgets              pdb          policy                         true         PodDisruptionBudget
podsecuritypolicies               psp          policy                         false        PodSecurityPolicy
clusterrolebindings                            rbac.authorization.k8s.io      false        ClusterRoleBinding
clusterroles                                   rbac.authorization.k8s.io      false        ClusterRole
rolebindings                                   rbac.authorization.k8s.io      true         RoleBinding
roles                                          rbac.authorization.k8s.io      true         Role
priorityclasses                   pc           scheduling.k8s.io              false        PriorityClass
csidrivers                                     storage.k8s.io                 false        CSIDriver
csinodes                                       storage.k8s.io                 false        CSINode
storageclasses                    sc           storage.k8s.io                 false        StorageClass
volumeattachments                              storage.k8s.io                 false        VolumeAttachment
jiman@k8smaster:~$ 




# Docker CE 설치
## 리포지터리 설정
### apt가 HTTPS 리포지터리를 사용할 수 있도록 해주는 패키지 설치
apt-get update && apt-get install -y \
  apt-transport-https ca-certificates curl software-properties-common gnupg2

### Docker의 공식 GPG 키 추가
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -

### Docker apt 리포지터리 추가.
add-apt-repository \
  "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) \
  stable"

## Docker CE 설치.
apt-get update && apt-get install -y \
  containerd.io=1.2.10-3 \
  docker-ce=5:19.03.4~3-0~ubuntu-$(lsb_release -cs) \
  docker-ce-cli=5:19.03.4~3-0~ubuntu-$(lsb_release -cs)

# 데몬 설정.
cat > /etc/docker/daemon.json <<EOF
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2"
}
EOF

mkdir -p /etc/systemd/system/docker.service.d

# Docker 재시작.
systemctl daemon-reload
systemctl restart docker


modprobe overlay
modprobe br_netfilter

# 요구되는 sysctl 파라미터 설정, 이 설정은 재부팅 간에도 유지된다.
cat > /etc/sysctl.d/99-kubernetes-cri.conf <<EOF
net.bridge.bridge-nf-call-iptables  = 1
net.ipv4.ip_forward                 = 1
net.bridge.bridge-nf-call-ip6tables = 1
EOF

sysctl --system


# 선행 조건 설치
apt-get update
apt-get install -y software-properties-common

add-apt-repository ppa:projectatomic/ppa
apt-get update

# CRI-O 설치
apt-get install -y cri-o-1.15

# 선행 조건 설치
apt-get update
apt-get install -y software-properties-common

add-apt-repository ppa:projectatomic/ppa
apt-get update

# CRI-O 설치
apt-get install -y cri-o-1.15


cat > /etc/modules-load.d/containerd.conf <<EOF
overlay
br_netfilter
EOF

modprobe overlay
modprobe br_netfilter

# 요구되는 sysctl 파라미터 설정, 이 설정은 재부팅에서도 유지된다.
cat > /etc/sysctl.d/99-kubernetes-cri.conf <<EOF
net.bridge.bridge-nf-call-iptables  = 1
net.ipv4.ip_forward                 = 1
net.bridge.bridge-nf-call-ip6tables = 1
EOF

sysctl --system



# containerd 설치
## 리포지터리 설정
### apt가 HTTPS로 리포지터리를 사용하는 것을 허용하기 위한 패키지 설치
apt-get update && apt-get install -y apt-transport-https ca-certificates curl software-properties-common

### Docker의 공식 GPG 키 추가
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -

### Docker apt 리포지터리 추가.
add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) \
    stable"

## containerd 설치
apt-get update && apt-get install -y containerd.io

# containerd 설정
mkdir -p /etc/containerd
containerd config default > /etc/containerd/config.toml

# containerd 재시작
systemctl restart containerd

