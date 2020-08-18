


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


