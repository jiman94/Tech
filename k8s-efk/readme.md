


# k8s-efk kubectl get all  
NAME                                 READY   STATUS    RESTARTS   AGE
pod/elasticsearch-69d7479778-pxlgw   1/1     Running   0          39s
pod/kibana-768c8fc454-gfpp5          1/1     Running   0          23m

NAME                    TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
service/elasticsearch   NodePort    10.96.8.107      <none>        9200:31466/TCP   39s
service/kibana          NodePort    10.101.144.223   <none>        5601:31692/TCP   23m
service/kubernetes      ClusterIP   10.96.0.1        <none>        443/TCP          40m

NAME                            READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/elasticsearch   1/1     1            1           39s
deployment.apps/kibana          1/1     1            1           23m

NAME                                       DESIRED   CURRENT   READY   AGE
replicaset.apps/elasticsearch-69d7479778   1         1         1       39s
replicaset.apps/kibana-768c8fc454          1         1         1       23m

http://localhost:31692/




# kubectl get pods -n kube-system
NAME                                     READY   STATUS    RESTARTS   AGE
coredns-5644d7b6d9-7t777                 1/1     Running   0          45m
coredns-5644d7b6d9-qwzhv                 1/1     Running   0          45m
etcd-docker-desktop                      1/1     Running   0          44m
fluentd-688s2                            1/1     Running   0          39m
kube-apiserver-docker-desktop            1/1     Running   0          44m
kube-controller-manager-docker-desktop   1/1     Running   0          44m
kube-proxy-gcdf5                         1/1     Running   0          45m
kube-scheduler-docker-desktop            1/1     Running   0          44m
storage-provisioner                      1/1     Running   1          41m
vpnkit-controller                        1/1     Running   0          41m


# docker images
REPOSITORY                                      TAG                                              IMAGE ID            CREATED             SIZE
docker/desktop-storage-provisioner              v1.1                                             e704287ce753        4 months ago        41.8MB
docker/desktop-vpnkit-controller                v1.0                                             79da37e5a3aa        5 months ago        36.6MB
docker/desktop-kubernetes                       kubernetes-v1.16.5-cni-v0.7.5-critools-v1.15.0   a86647f0b376        6 months ago        279MB
k8s.gcr.io/kube-controller-manager              v1.16.5                                          441835dd2301        7 months ago        151MB
k8s.gcr.io/kube-proxy                           v1.16.5                                          0ee1b8a3ebe0        7 months ago        82.7MB
k8s.gcr.io/kube-apiserver                       v1.16.5                                          fc838b21afbb        7 months ago        159MB
k8s.gcr.io/kube-scheduler                       v1.16.5                                          b4d073a9efda        7 months ago        83.5MB
docker/kube-compose-controller                  v0.4.25-alpha1                                   129151cdf35f        9 months ago        35.6MB
docker/kube-compose-api-server                  v0.4.25-alpha1                                   989749268895        9 months ago        50.7MB
docker/kube-compose-installer                   v0.4.25-alpha1                                   2a71ac5a1359        9 months ago        42.3MB
k8s.gcr.io/etcd                                 3.3.15-0                                         b2756210eeab        11 months ago       247MB
k8s.gcr.io/coredns                              1.6.2                                            bf261d157914        12 months ago       44.1MB
docker.elastic.co/kibana/kibana                 6.5.4                                            3c8a8603d365        20 months ago       735MB
docker.elastic.co/elasticsearch/elasticsearch   6.5.4                                            93109ce1d590        20 months ago       774MB
fluent/fluentd-kubernetes-daemonset             v1.3-debian-elasticsearch                        76929cb73738        20 months ago       226MB
k8s.gcr.io/pause                                3.1                                              da86e6ba6ca1        2 years ago         742kB

