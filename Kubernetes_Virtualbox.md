0. 구성
본 포스팅에서는 마스터노드 한 개와 워커노드 두 개로 구성되도록 실습해보려고 합니다.

1. Virtualbox 설치
homebrew를 이용하여서 virtualbox를 설치해보도록 하겠습니다.
1
2
$ brew cask install virtualbox
$ brew cask install virtualbox-extension-pack

2. Ubuntu ISO 파일 다운로드
http://cdimage.ubuntu.com/ubuntu-server/bionic/daily/current/

3. VM 생성
메모리는 2048MB
디스크 10GB
프로세서에서 cpu갯수 2개

 

4. Ubuntu 설치
한글 설정
키보드 
디스크 분할 
기타 등등 설정 

5. Kubernetes & Docker 설치 전 설정
스왑 메모리 비활성화
1
2
swapoff -a && 
sed -i '/ swap / s/^/#/' /etc/fstab

6. Docker 설치
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
# 패키지 관리 도구 업데이트
$ sudo apt update && sudo apt-get update

# docker 설치에 필요한 라이브러리 설치
$ sudo apt-get install apt-transport-https ca-certificates curl software-properties-common -y

# gpg key 내려받기
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# 다운받은 key 확인
$ sudo apt-key fingerprint 0EBFCD88

# 도커 다운로드 링크 추가
$ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

# 패키지 관리 도구 업데이트
$ sudo apt-get update

# docker 설치
$ sudo apt-get update && sudo apt-get install \
  containerd.io=1.2.10-3 \
  docker-ce=5:19.03.4~3-0~ubuntu-$(lsb_release -cs) \
  docker-ce-cli=5:19.03.4~3-0~ubuntu-$(lsb_release -cs)

# docker 테스트
$ sudo docker run hello-world
Kubernetes가 권장하는 Docker 데몬 드라이버가 systemd이므로 Docker 데몬의 드라이버를 교체합니다.
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
$ sudo cat > /etc/docker/daemon.json <<EOF
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2"
}
EOF

또는 

 subo vi /etc/docker/daemon.json
{
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2"
}


$ sudo mkdir -p /etc/systemd/system/docker.service.d

$ sudo systemctl daemon-reload
$ sudo systemctl restart docker

7. Kubernetes 설치
먼저 패키지 리스트를 업데이트 합니다.
1
$ sudo apt-get update && sudo apt-get upgrade
kubeadm, kubelet, kubectl을 설치합니다.
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
$ curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -

$ cat <<EOF | sudo tee /etc/apt/sources.list.d/kubernetes.list
deb https://apt.kubernetes.io/ kubernetes-xenial main
EOF

$ sudo apt-get update

$ sudo apt-get install -y kubelet kubeadm kubectl

$ sudo apt-mark hold kubelet kubeadm kubectl

# 설치 완료 확인
$ kubeadm version

$ kubelet --version

$ kubectl version
마지막으로 계획하였던 master 노드와 worker 노도의 호스트 이름과 IP를 모두 등록해줍니다.
1
2
3
4
5
6
7
8
9
10
11
12
13
14
$ cat << EOF >> /etc/hosts
192.168.0.10 k8smaster
192.168.0.11 k8snode1
192.168.0.12 k8snode2
EOF

또는


sudo vi /etc/hosts
192.168.0.10 k8smaster
192.168.0.11 k8snode1
192.168.0.12 k8snode2
 

8. 워커 노드 VM 생성
위에서 부터 실행한던 VM은 닫아서 전원을 꺼줍니다.
Master에서 오른쪽 마우스 클릭하고 복제를 클릭해줍니다.

 
클릭하시면 위와 같이 나오게 창이 나오게 됩니다.
MAC주소 정책을 모든 네트워크 어댑터의 새 MAC 주소 생성으로 선택해주시고 계속을 클릭해주시면 됩니다.

복제 방식을 선택하라고 나오게 되는데요 완전한 복제를 해줍니다.
복제된 워커 노드의 ip와 hostname을 변경해줍니다.
먼저 hostname을 변경해줍니다.
1
hostnamectl set-hostname k8snode1
재부팅 후 ip와 hostname이 제대로 변경되었는지 아래 명령어를 통해서 확인해줍니다.
1
2
3
$ hostname

$ ifconfig
워커 노드2도 위와 동일하게 진행하며 hostname은 evanjin-node2로 해주고 address는 192.168.0.12로 해주시면 됩니다.
hostname는 제가 임의로 정한 것이므로 위에서 마스터 노드에서 정한 host들의 이름대로 해주시면 됩니다.

9. master 노드 설정
1
$ sudo kubeadm init --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=192.168.0.10
위의 명령어가 제대로 실행되게 되면 아래와 같이 나오게 됩니다.
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
Your Kubernetes control-plane has initialized successfully!

To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

Then you can join any number of worker nodes by running the following on each as root:

kubeadm join 192.168.0.10:6443 --token ix2ak9.nkg5p2smfkdke7hu \
    --discovery-token-ca-cert-hash sha256:cc1138bbb9a51bfaaf268a217fc55adeb1e49b38f2d0ae449009cd2716047324
kubeadm join부터 나오는 내용은 별도로 따로 복사해서 저장해둡니다.
다음으로 root 계정을 이용해서 kubectl을 실행할 수 있도록 환경변수를 설정해줍니다.
1
2
3
$ mkdir -p $HOME/.kube
$ sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
$ sudo chown $(id -u):$(id -g) $HOME/.kube/config

10. 워커 노드 설정
워커 노드에서는 위에서 kubeadm join부터 나왔던 내용을 그대로 복사해서 붙여넣고 실행해줍니다.
1
2
$ sudo kubeadm join 192.168.0.10:6443 --token ix2ak9.nkg5p2smfkdke7hu \
    --discovery-token-ca-cert-hash sha256:cc1138bbb9a51bfaaf268a217fc55adeb1e49b38f2d0ae449009cd2716047324
워커 노드들에 위의 명령어를 제대로 실행되었다면 마스터 노드에서 아래의 명령을 실행하면 추가된 워커 노드가 보이게됩니다.
1
2
3
4
5
6

jmryu@k8smaster:~$ sudo kubectl get nodes
NAME        STATUS     ROLES    AGE   VERSION
k8smaster   NotReady   master   13m   v1.17.3
k8swnode1   NotReady   <none>   25s   v1.17.3
k8swnode2   NotReady   <none>   3s    v1.17.3

11. Dashboard 설치
이 파트는 부가적인 부분으로 필요한 분만 설치하시면 됩니다.
kubernetes에서는 자체적인 Dashboard를 제공합니다.
kubernetes의 전반적인 상태를 UI로 보기 쉽게 제공합니다.
마스터 노드에서 아래의 명령어를 실행해줍니다.
1
$ sudo kubectl apply -f https://raw.githubusercontent.com/kubetm/kubetm.github.io/master/sample/practice/appendix/gcp-kubernetes-dashboard.yaml
다음으로 백그라운드에서 Proxy로 Dashboard를 띄우도록 하겠습니다.
1
2
$ sudo nohup kubectl proxy --port=8443 --address=192.168.0.10 --accept-hosts='^*$' >/dev/null 2>&1 &
http://192.168.0.10:8443/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/.
만약 로그인 페이지에서 skip으로 접속 안 될시에는 아래 명령어로 토큰 확인 후 접근하시면 됩니다.
1
$ sudo kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep admin-user | awk '{print $1}')

 
