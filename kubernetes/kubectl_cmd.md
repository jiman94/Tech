 kubectl get nodes --show-labels

 kubectl get pods -o wide


kubectl get pods --show-labels

kubectl get pods --selector owner=michael

kubectl label pods labelex owner=michael

kubectl get pods --show-labels

kubectl config view # 병합된 kubeconfig 설정을 표시한다.


ubectl config view -o jsonpath='{.users[].name}'    # 첫 번째 사용자 출력
kubectl config view -o jsonpath='{.users[*].name}'    # 사용자 리스트 조회
kubectl config get-contexts                          # 컨텍스트 리스트 출력
kubectl config current-context              # 현재 컨텍스트 출력



Kubectl 컨텍스트와 설정 
kubectl이 통신하고 설정 정보를 수정하는 쿠버네티스 클러스터를 지정한다. 설정 파일에 대한 자세한 정보는 kubeconfig를 이용한 클러스터 간 인증 문서를 참고한다.

kubectl config view # 병합된 kubeconfig 설정을 표시한다.

# 동시에 여러 kubeconfig 파일을 사용하고 병합된 구성을 확인한다
KUBECONFIG=~/.kube/config:~/.kube/kubconfig2

kubectl config view

# e2e 사용자의 암호를 확인한다
kubectl config view -o jsonpath='{.users[?(@.name == "e2e")].user.password}'

kubectl config view -o jsonpath='{.users[].name}'    # 첫 번째 사용자 출력
kubectl config view -o jsonpath='{.users[*].name}'    # 사용자 리스트 조회
kubectl config get-contexts                          # 컨텍스트 리스트 출력
kubectl config current-context              # 현재 컨텍스트 출력
kubectl config use-context my-cluster-name  # my-cluster-name를 기본 컨텍스트로 설정

# 기본 인증을 지원하는 새로운 사용자를 kubeconf에 추가한다
kubectl config set-credentials kubeuser/foo.kubernetes.com --username=kubeuser --password=kubepassword

# 해당 컨텍스트에서 모든 후속 kubectl 커맨드에 대한 네임스페이스를 영구적으로 저장한다
kubectl config set-context --current --namespace=ggckad-s2

# 특정 사용자와 네임스페이스를 사용하는 컨텍스트 설정
kubectl config set-context gce --user=cluster-admin --namespace=foo \
  && kubectl config use-context gce

kubectl config unset users.foo                       # foo 사용자 삭제
Kubectl apply
apply는 쿠버네티스 리소스를 정의하는 파일을 통해 애플리케이션을 관리한다. kubectl apply를 실행하여 클러스터에 리소스를 생성하고 업데이트한다. 이것은 프로덕션 환경에서 쿠버네티스 애플리케이션을 관리할 때 권장된다. Kubectl Book을 참고한다.

오브젝트 생성
쿠버네티스 매니페스트는 JSON이나 YAML로 정의된다. 파일 확장자는 .yaml , .yml, .json 이 사용된다.

kubectl apply -f ./my-manifest.yaml            # 리소스(들) 생성
kubectl apply -f ./my1.yaml -f ./my2.yaml      # 여러 파일로 부터 생성
kubectl apply -f ./dir                         # dir 내 모든 매니페스트 파일에서 리소스(들) 생성
kubectl apply -f https://git.io/vPieo          # url로부터 리소스(들) 생성
kubectl create deployment nginx --image=nginx  # nginx 단일 인스턴스를 시작

# "Hello World"를 출력하는 잡(Job) 생성
kubectl create job hello --image=busybox -- echo "Hello World"

# 매분마다 "Hello World"를 출력하는 크론잡(CronJob) 생성
kubectl create cronjob hello --image=busybox   --schedule="*/1 * * * *" -- echo "Hello World"    

kubectl explain pods                           # 파드 매니페스트 문서를 조회


# 여러 개의 키로 시크릿 생성
cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Secret
metadata:
  name: mysecret
type: Opaque
data:
  password: $(echo -n "s33msi4" | base64 -w0)
  username: $(echo -n "jane" | base64 -w0)
EOF

리소스 조회 및 찾기
# 기본 출력을 위한 Get 커맨드
kubectl get services                          # 네임스페이스 내 모든 서비스의 목록 조회
kubectl get pods --all-namespaces             # 모든 네임스페이스 내 모든 파드의 목록 조회
kubectl get pods -o wide                      # 해당하는 네임스페이스 내 모든 파드의 상세 목록 조회
kubectl get deployment my-dep                 # 특정 디플로이먼트의 목록 조회
kubectl get pods                              # 네임스페이스 내 모든 파드의 목록 조회
kubectl get pod my-pod -o yaml                # 파드의 YAML 조회

# 상세 출력을 위한 Describe 커맨드
kubectl describe nodes my-node
kubectl describe pods my-pod

# Name으로 정렬된 서비스의 목록 조회
kubectl get services --sort-by=.metadata.name

# 재시작 횟수로 정렬된 파드의 목록 조회
kubectl get pods --sort-by='.status.containerStatuses[0].restartCount'

# PersistentVolumes을 용량별로 정렬해서 조회
kubectl get pv --sort-by=.spec.capacity.storage

# app=cassandra 레이블을 가진 모든 파드의 레이블 버전 조회
kubectl get pods --selector=app=cassandra -o \
  jsonpath='{.items[*].metadata.labels.version}'

# 예를 들어 'ca.crt'와 같이 점이 있는 키값을 검색한다
kubectl get configmap myconfig \
  -o jsonpath='{.data.ca\.crt}'

# 모든 워커 노드 조회 (셀렉터를 사용하여 'node-role.kubernetes.io/master'
# 으로 명명된 라벨의 결과를 제외)
kubectl get node --selector='!node-role.kubernetes.io/master'

# 네임스페이스의 모든 실행 중인 파드를 조회
kubectl get pods --field-selector=status.phase=Running

# 모든 노드의 외부IP를 조회
kubectl get nodes -o jsonpath='{.items[*].status.addresses[?(@.type=="ExternalIP")].address}'

# 특정 RC에 속해있는 파드 이름의 목록 조회
# "jq" 커맨드는 jsonpath를 사용하는 매우 복잡한 변환에 유용하다. https://stedolan.github.io/jq/ 에서 확인할 수 있다.
sel=${$(kubectl get rc my-rc --output=json | jq -j '.spec.selector | to_entries | .[] | "\(.key)=\(.value),"')%?}
echo $(kubectl get pods --selector=$sel --output=jsonpath={.items..metadata.name})

# 모든 파드(또는 레이블을 지원하는 다른 쿠버네티스 오브젝트)의 레이블 조회
kubectl get pods --show-labels

# 어떤 노드가 준비됐는지 확인
JSONPATH='{range .items[*]}{@.metadata.name}:{range @.status.conditions[*]}{@.type}={@.status};{end}{end}' \
 && kubectl get nodes -o jsonpath="$JSONPATH" | grep "Ready=True"

# 파드에 의해 현재 사용되고 있는 모든 시크릿 목록 조회
kubectl get pods -o json | jq '.items[].spec.containers[].env[]?.valueFrom.secretKeyRef.name' | grep -v null | sort | uniq

# 모든 파드의 초기화 컨테이너(initContainer)의 컨테이너ID 목록 조회
# 초기화 컨테이너(initContainer)를 제거하지 않고 정지된 모든 컨테이너를 정리할 때 유용하다.
kubectl get pods --all-namespaces -o jsonpath='{range .items[*].status.initContainerStatuses[*]}{.containerID}{"\n"}{end}' | cut -d/ -f3

# 타임스탬프로 정렬된 이벤트 목록 조회
kubectl get events --sort-by=.metadata.creationTimestamp

# 매니페스트가 적용된 경우 클러스터의 현재 상태와 클러스터의 상태를 비교한다.
kubectl diff -f ./my-manifest.yaml

# 노드에 대해 반환된 모든 키의 마침표로 구분된 트리를 생성한다.
# 복잡한 중첩 JSON 구조 내에서 키를 찾을 때 유용하다.
kubectl get nodes -o json | jq -c 'path(..)|[.[]|tostring]|join(".")'

# 파드 등에 대해 반환된 모든 키의 마침표로 구분된 트리를 생성한다.
kubectl get pods -o json | jq -c 'path(..)|[.[]|tostring]|join(".")'
리소스 업데이트
kubectl set image deployment/frontend www=image:v2               # "frontend" 디플로이먼트의 "www" 컨테이너 이미지를 업데이트하는 롤링 업데이트
kubectl rollout history deployment/frontend                      # 현 리비전을 포함한 디플로이먼트의 이력을 체크
kubectl rollout undo deployment/frontend                         # 이전 디플로이먼트로 롤백
kubectl rollout undo deployment/frontend --to-revision=2         # 특정 리비전으로 롤백
kubectl rollout status -w deployment/frontend                    # 완료될 때까지 "frontend" 디플로이먼트의 롤링 업데이트 상태를 감시
kubectl rollout restart deployment/frontend                      # "frontend" 디플로이먼트의 롤링 재시작


cat pod.json | kubectl replace -f -                              # std로 전달된 JSON을 기반으로 파드 교체

# 리소스를 강제 교체, 삭제 후 재생성함. 이것은 서비스를 중단시킴.
kubectl replace --force -f ./pod.json

# 복제된 nginx를 위한 서비스를 생성한다. 80 포트로 서비스하고, 컨테이너는 8000 포트로 연결한다.
kubectl expose rc nginx --port=80 --target-port=8000

# 단일-컨테이너 파드의 이미지 버전(태그)을 v4로 업데이트
kubectl get pod mypod -o yaml | sed 's/\(image: myimage\):.*$/\1:v4/' | kubectl replace -f -

kubectl label pods my-pod new-label=awesome                      # 레이블 추가
kubectl annotate pods my-pod icon-url=http://goo.gl/XXBTWq       # 어노테이션 추가
kubectl autoscale deployment foo --min=2 --max=10                # 디플로이먼트 "foo" 오토스케일
리소스 패치
kubectl patch node k8s-node-1 -p '{"spec":{"unschedulable":true}}' # 노드를 부분적으로 업데이트

# 컨테이너의 이미지를 업데이트. 병합(merge) 키이므로, spec.containers[*].name이 필요.
kubectl patch pod valid-pod -p '{"spec":{"containers":[{"name":"kubernetes-serve-hostname","image":"new image"}]}}'

# 위치 배열을 이용한 json 패치를 사용하여, 컨테이너의 이미지를 업데이트.
kubectl patch pod valid-pod --type='json' -p='[{"op": "replace", "path": "/spec/containers/0/image", "value":"new image"}]'

# 위치 배열을 이용한 json 패치를 사용하여 livenessProbe 디플로이먼트 비활성화.
kubectl patch deployment valid-deployment  --type json   -p='[{"op": "remove", "path": "/spec/template/spec/containers/0/livenessProbe"}]'

# 위치 배열에 새 요소 추가

kubectl edit svc/docker-registry                      # docker-registry라는 서비스 편집
KUBE_EDITOR="nano" kubectl edit svc/docker-registry   # 다른 편집기 사용
리소스 스케일링
kubectl scale --replicas=3 rs/foo                                 # 'foo'라는 레플리카셋을 3으로 스케일
kubectl scale --replicas=3 -f foo.yaml                            # "foo.yaml"에 지정된 리소스의 크기를 3으로 스케일
kubectl scale --current-replicas=2 --replicas=3 deployment/mysql  # mysql이라는 디플로이먼트의 현재 크기가 2인 경우, mysql을 3으로 스케일
kubectl scale --replicas=5 rc/foo rc/bar rc/baz                   # 여러 개의 레플리케이션 컨트롤러 스케일
리소스 삭제
kubectl delete -f ./pod.json                                              # pod.json에 지정된 유형 및 이름을 사용하여 파드 삭제
kubectl delete pod,service baz foo                                        # "baz", "foo"와 동일한 이름을 가진 파드와 서비스 삭제
kubectl delete pods,services -l name=myLabel                              # name=myLabel 라벨을 가진 파드와 서비스 삭제
kubectl delete pods,services -l name=myLabel --include-uninitialized      # 초기화되지 않은 것을 포함하여, name=myLabel 라벨을 가진 파드와 서비스 삭제
kubectl -n my-ns delete pod,svc --all                                      # 초기화되지 않은 것을 포함하여, my-ns 네임스페이스 내 모든 파드와 서비스 삭제
# awk pattern1 또는 pattern2에 매칭되는 모든 파드 삭제
kubectl get pods  -n mynamespace --no-headers=true | awk '/pattern1|pattern2/{print $1}' | xargs  kubectl delete -n mynamespace pod
실행 중인 파드와 상호 작용
kubectl logs my-pod                                 # 파드 로그(stdout) 덤프
kubectl logs -l name=myLabel                        # name이 myLabel인 파드 로그 덤프 (stdout)
kubectl logs my-pod --previous                      # 컨테이너의 이전 인스턴스 생성에 대한 파드 로그(stdout) 덤프
kubectl logs my-pod -c my-container                 # 파드 로그(stdout, 멀티-컨테이너 경우) 덤프
kubectl logs -l name=myLabel -c my-container        # name이 myLabel인 파드 로그 덤프 (stdout)
kubectl logs my-pod -c my-container --previous      # 컨테이너의 이전 인스턴스 생성에 대한 파드 로그(stdout, 멀티-컨테이너 경우) 덤프
kubectl logs -f my-pod                              # 실시간 스트림 파드 로그(stdout)
kubectl logs -f my-pod -c my-container              # 실시간 스트림 파드 로그(stdout, 멀티-컨테이너 경우)
kubectl logs -f -l name=myLabel --all-containers    # name이 myLabel인 모든 파드의 로그 스트리밍 (stdout)
kubectl run -i --tty busybox --image=busybox -- sh  # 대화형 셸로 파드를 실행


kubectl attach my-pod -i                            # 실행중인 컨테이너에 연결
kubectl port-forward my-pod 5000:6000               # 로컬 머신의 5000번 포트를 리스닝하고, my-pod의 6000번 포트로 전달
kubectl exec my-pod -- ls /                         # 기존 파드에서 명령 실행(한 개 컨테이너 경우)
kubectl exec my-pod -c my-container -- ls /         # 기존 파드에서 명령 실행(멀티-컨테이너 경우)
kubectl top pod POD_NAME --containers               # 특정 파드와 해당 컨테이너에 대한 메트릭 표시

노드, 클러스터와 상호 작용
kubectl cordon my-node                                                # my-node를 스케줄링할 수 없도록 표기
kubectl drain my-node                                                 # 유지 보수를 위해서 my-node를 준비 상태로 비움
kubectl uncordon my-node                                              # my-node를 스케줄링할 수 있도록 표기
kubectl top node my-node                                              # 주어진 노드에 대한 메트릭 표시
kubectl cluster-info                                                  # 마스터 및 서비스의 주소 표시
kubectl cluster-info dump                                             # 현재 클러스터 상태를 stdout으로 덤프
kubectl cluster-info dump --output-directory=/path/to/cluster-state   # 현재 클러스터 상태를 /path/to/cluster-state으로 덤프

# key와 effect가 있는 테인트(taint)가 이미 존재하면, 그 값이 지정된 대로 대체된다.

kubectl api-resources --namespaced=true      # 네임스페이스를 가지는 모든 리소스
kubectl api-resources --namespaced=false     # 네임스페이스를 가지지 않는 모든 리소스
kubectl api-resources -o name                # 모든 리소스의 단순한 (리소스 이름 만) 출력
kubectl api-resources -o wide                # 모든 리소스의 확장된 ("wide"로 알려진) 출력
kubectl api-resources --verbs=list,get       # "list"와 "get"의 요청 동사를 지원하는 모든 리소스 출력
kubectl api-resources --api-group=extensions # "extensions" API 그룹의 모든 리소

3.2 Kubernetes 실습
ㅇ 실습 내용
kubectl
pod
ReplicaSet
Deployment
Service
ㅇ 사전 준비 내용
cluster 생성
포트포워딩 (22번)
echo "root:kubernetes" | chpasswd
master node에 접속하기 위한 ${pw}는 메일로 전송됨. 
ssh root@${공인 ip} -p {22번 포트포워딩한 port번호}로 접속

ㅇ kubectl
kubectl
alias k = 'kubectl'
apply : Apply a configuration to a resource by filename or stdin
get : Display one or many resources
describe : Show details of a specific resource or group of resources
delete : Delete resources by filenames, stdin, resources and names, or by resources and label selector
logs : Print the logs for a container in a pod
exec :Execute a command in a container
전체 Object 검색
kubectl api-resources
k api-resources
실습
get
# pod, replicaset, deployment, service 조회
kubectl get all

# node 조회
kubectl get no
kubectl get node
kubectl get nodes

# 결과 포멧 변경
kubectl get nodes -o wide
kubectl get nodes -o yaml
kubectl get nodes -o json
describe
# kubectl describe type/name
# kubectl describe type name
kubectl describe node <node name>
kubectl describe node/<node name>
그 외
kubectl exec -it <POD_NAME>
kubectl logs -f <POD_NAME|TYPE/NAME>

kubectl apply -f <FILENAME>
kubectl delete -f <FILENAME>
ㅇ pod
기본 예제
kubectl run test --image brenden0730/kt-workshop # deprecated soon..
kubectl get po
kubectl get pod
kubectl get pods
kubectl get pods -o wide
kubectl get pods -o yaml
kubectl get pods -o json
kubectl logs test-<xxxx>
kubectl logs -f test-<xxxx>
kubectl exec -it test-<xxxx> sh
kubectl describe pods test-<xxxx>
kubectl delete pods test-<xxxx>
kubectl get pods
kubectl get all
kubectl delete deployment/test
YAML 파일 관리
kubectl apply -f <filename>
kubectl delete -f <filename>
기본 예제
pod.yml
apiVersion: v1
kind: Pod
metadata:
  name: kt-workshop
  labels:
    type: app
spec:
  containers:
  - name: app
    image: brenden0730/kt-workshop:latest
multi-pod.yml
apiVersion: v1
kind: Pod
metadata:
  name: kt-workshop-redis
  labels:
    type: stack
spec:
  containers:
  - name: app
    image: brenden0730/kt-workshop-redis
    env:
    - name: REDIS_HOST
      value: "localhost"
  - name: db
    image: redis
kubectl get all
kubectl logs kt-workshop-redis
kubectl logs kt-workshop-redis app
kubectl logs kt-workshop-redis db
kubectl exec -it kt-workshop-redis
kubectl exec -it kt-workshop-redis -c db sh
kubectl exec -it kt-workshop-redis -c app sh

  apk add curl busybox-extras # install telnet
  curl localhost:4567
  telnet localhost 6379
    dbsize
    KEYS *
    GET count
    quit

kubectl get pod/kt-workshop-redis
kubectl get pod/kt-workshop-redis -o yaml

ㅇ ReplicaSet
기본 예제


kubectl get pods --show-labels
kubectl label pod/kt-workshop-rs-<xxxx> service-
rs.yml 파일에서 replicas=3으로 바꾸고 다시 apply 해보자!

ㅇ Deployment
기본 예제
vim deployment.yml


kubectl set image deploy/kt-workshop-deploy kt-workshop=brenden0730/kt-workshop:2
kubectl apply -f deployment.yml
kubectl get rs -w
kubectl describe deploy/kt-workshop-deploy
kubectl rollout history -f deployment.yml
kubectl set image deploy/kt-workshop-deploy kt-workshop=brenden0730/kt-workshop:1 --record=true
kubectl rollout history -f deployment.yml
kubectl rollout history -f deployment.yml --revision=2
kubectl rollout status deploy/kt-workshop-deploy
kubectl rollout undo deploy/kt-workshop-deploy
kubectl rollout undo deploy/kt-workshop-deploy --to-revision=3