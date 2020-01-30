# NodePort :: nginx 

kubectl run nginx --image nginx --port=80

kubectl get pods

kubectl scale deploy nginx --replicas=2

kubectl expose deployment nginx --type=NodePort

D:\aa>
D:\aa>kubectl expose deployment nginx --type=NodePort                수]
service/nginx exposed

D:\aa>k get service
NAME         TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)          AGE
kubernetes   ClusterIP   10.96.0.1      <none>        443/TCP          10h
nginx        NodePort    10.96.58.172   <none>        80:31195/TCP     15s
redis        ClusterIP   10.96.109.7    <none>        6379/TCP         7h22m
whoami       NodePort    10.96.14.73    <none>        4567:31321/TCP   7h5m

D:\aa>k describe nginx
error: the server doesn't have a resource type "nginx"

D:\aa>k describe service nginx
Name:                     nginx
Namespace:                default
Labels:                   run=nginx
Annotations:              <none>
Selector:                 run=nginx
Type:                     NodePort
IP:                       10.96.58.172
Port:                     <unset>  80/TCP
TargetPort:               80/TCP
NodePort:                 <unset>  31195/TCP
Endpoints:                172.17.0.8:80,172.17.0.9:80
Session Affinity:         None
External Traffic Policy:  Cluster
Events:                   <none>

D:\aa>



localhost:30362



kubectl delete deploymene nginx

kubectl delete service nginx-app
