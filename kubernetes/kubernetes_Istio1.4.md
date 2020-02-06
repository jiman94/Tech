# Kubernetes_Istio
> https://istio.io/docs/examples/bookinfo/

### Install the demo profile
#### istioctl manifest apply --set profile=demo
```bash 
D:\k8s\istio-1.4.3>istioctl manifest apply --set profile=demo
- Applying manifest for component Base...
✔ Finished applying manifest for component Base.
- Applying manifest for component Citadel...
- Applying manifest for component Galley...
- Applying manifest for component Policy...
- Applying manifest for component IngressGateway...
- Applying manifest for component Pilot...
- Applying manifest for component Kiali...
- Applying manifest for component Prometheus...
- Applying manifest for component EgressGateway...
- Applying manifest for component Tracing...
- Applying manifest for component Injector...
- Applying manifest for component Telemetry...
- Applying manifest for component Grafana...
✔ Finished applying manifest for component Citadel.
✔ Finished applying manifest for component Prometheus.
✔ Finished applying manifest for component Kiali.
✔ Finished applying manifest for component Policy.
✔ Finished applying manifest for component Injector.
✔ Finished applying manifest for component Galley.
✔ Finished applying manifest for component Pilot.
✔ Finished applying manifest for component EgressGateway.
✔ Finished applying manifest for component IngressGateway.
✔ Finished applying manifest for component Tracing.
✔ Finished applying manifest for component Grafana.
✔ Finished applying manifest for component Telemetry.
✔ Installation complete
D:\k8s\istio-1.4.3>
```

#### kubectl get svc -n istio-system
```bash 
:\k8s\istio-1.4.3>kubectl get svc -n istio-system
NAME                     TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)                                                                                                                      AGE
grafana                  ClusterIP      10.101.201.38    <none>        3000/TCP                                                                                                                     51s
istio-citadel            ClusterIP      10.98.164.186    <none>        8060/TCP,15014/TCP                                                                                                           58s
istio-egressgateway      ClusterIP      10.100.49.36     <none>        80/TCP,443/TCP,15443/TCP                                                                                                     53s
istio-galley             ClusterIP      10.103.30.57     <none>        443/TCP,15014/TCP,9901/TCP,15019/TCP                                                                                         54s
istio-ingressgateway     LoadBalancer   10.100.164.98    <pending>     15020:32342/TCP,80:30390/TCP,443:32334/TCP,15029:31182/TCP,15030:31385/TCP,15031:30105/TCP,15032:31953/TCP,15443:32288/TCP   53s
istio-pilot              ClusterIP      10.101.183.218   <none>        15010/TCP,15011/TCP,8080/TCP,15014/TCP                                                                                       54s
istio-policy             ClusterIP      10.110.217.188   <none>        9091/TCP,15004/TCP,15014/TCP                                                                                                 56s
istio-sidecar-injector   ClusterIP      10.99.52.224     <none>        443/TCP                                                                                                                      55s
istio-telemetry          ClusterIP      10.99.35.11      <none>        9091/TCP,15004/TCP,15014/TCP,42422/TCP                                                                                       50s
jaeger-agent             ClusterIP      None             <none>        5775/UDP,6831/UDP,6832/UDP                                                                                                   59s
jaeger-collector         ClusterIP      10.97.162.141    <none>        14267/TCP,14268/TCP,14250/TCP                                                                                                58s
jaeger-query             ClusterIP      10.101.185.50    <none>        16686/TCP                                                                                                                    57s
kiali                    ClusterIP      10.97.216.47     <none>        20001/TCP                                                                                                                    55s
prometheus               ClusterIP      10.99.59.155     <none>        9090/TCP                                                                                                                     56s
tracing                  ClusterIP      10.100.26.231    <none>        80/TCP                                                                                                                       55s
zipkin                   ClusterIP      10.111.61.235    <none>        9411/TCP                                                                                                                     53s

D:\k8s\istio-1.4.3>
```

### kubectl get pods -n istio-system

```bash 
D:\k8s\istio-1.4.3>kubectl get pods -n istio-system
NAME                                      READY   STATUS              RESTARTS   AGE
grafana-6b65874977-twvwh                  0/1     ContainerCreating   0          83s
istio-citadel-f78ff689-2rwzs              1/1     Running             0          88s
istio-egressgateway-7b6b69ddcd-hplnl      0/1     Running             0          88s
istio-galley-69674cb559-hwvtm             0/1     ContainerCreating   0          86s
istio-ingressgateway-649f9646d4-nzfh4     0/1     Running             0          88s
istio-pilot-7989874664-7d4xw              0/1     Running             0          85s
istio-policy-5cdbc47674-brwpn             1/1     Running             2          87s
istio-sidecar-injector-7dd87d7989-z8lgq   0/1     ContainerCreating   0          86s
istio-telemetry-6dccd56cf4-zqcsl          1/1     Running             2          86s
istio-tracing-c66d67cd9-4n9xc             1/1     Running             0          88s
kiali-8559969566-gfjdh                    1/1     Running             0          86s
prometheus-66c5887c86-25xbk               0/1     ContainerCreating   0          86s

D:\k8s\istio-1.4.3>
```


#### Start the application services
#### label namespace default istio-injection=enabled
```bash 
kubectl label namespace default istio-injection=enabled
```

####  kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml
```bash 
D:\k8s\istio-1.4.3> kubectl apply -f samples/bookinfo/platform/kube/bookinfo.yaml
service/details created
serviceaccount/bookinfo-details created
deployment.apps/details-v1 created
service/ratings created
serviceaccount/bookinfo-ratings created
deployment.apps/ratings-v1 created
service/reviews created
serviceaccount/bookinfo-reviews created
deployment.apps/reviews-v1 created
deployment.apps/reviews-v2 created
deployment.apps/reviews-v3 created
service/productpage created
serviceaccount/bookinfo-productpage created
deployment.apps/productpage-v1 created

D:\k8s\istio-1.4.3>kubectl get services
NAME               TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
details            ClusterIP   10.111.14.76    <none>        9080/TCP       33s
kubernetes         ClusterIP   10.96.0.1       <none>        443/TCP        99m
nginx-deployment   NodePort    10.98.135.157   <none>        80:32645/TCP   81m
productpage        ClusterIP   10.96.163.155   <none>        9080/TCP       32s
ratings            ClusterIP   10.108.44.217   <none>        9080/TCP       33s
reviews            ClusterIP   10.110.210.99   <none>        9080/TCP       33s

D:\k8s\istio-1.4.3>
```
#### kubectl get pods
```bash 
D:\k8s\istio-1.4.3>kubectl get pods
NAME                                READY   STATUS    RESTARTS   AGE
details-v1-78d78fbddf-896m7         1/1     Running   0          2m35s
nginx-deployment-54f57cf6bf-9kpk9   1/1     Running   0          83m
nginx-deployment-54f57cf6bf-kpz7g   1/1     Running   0          83m
productpage-v1-596598f447-drhzp     1/1     Running   0          2m34s
ratings-v1-6c9dbf6b45-sc4bt         1/1     Running   0          2m35s
reviews-v1-7bb8ffd9b6-2gznh         1/1     Running   0          2m35s
reviews-v2-d7d75fff8-qnx78          1/1     Running   0          2m35s
reviews-v3-68964bc4c8-hsks9         1/1     Running   0          2m35s

D:\k8s\istio-1.4.3>
```

### Determine the ingress IP and port
#### kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml
```bash 
D:\k8s\istio-1.4.3>kubectl apply -f samples/bookinfo/networking/bookinfo-gateway.yaml
gateway.networking.istio.io/bookinfo-gateway created
virtualservice.networking.istio.io/bookinfo created

D:\k8s\istio-1.4.3>
```
#### kubectl get gateway
```bash 
D:\k8s\istio-1.4.3>kubectl get gateway
NAME               AGE
bookinfo-gateway   113s

D:\k8s\istio-1.4.3>
```

### Apply default destination rules
#### kubectl apply -f samples/bookinfo/networking/destination-rule-all.yaml
```bash 
D:\k8s\istio-1.4.3> kubectl apply -f samples/bookinfo/networking/destination-rule-all.yaml
destinationrule.networking.istio.io/productpage created
destinationrule.networking.istio.io/reviews created
destinationrule.networking.istio.io/ratings created
destinationrule.networking.istio.io/details created

D:\k8s\istio-1.4.3>
```
#### kubectl apply -f samples/bookinfo/networking/destination-rule-all-mtls.yaml

```bash 
D:\k8s\istio-1.4.3>kubectl apply -f samples/bookinfo/networking/destination-rule-all-mtls.yaml
destinationrule.networking.istio.io/productpage configured
destinationrule.networking.istio.io/reviews configured
destinationrule.networking.istio.io/ratings configured
destinationrule.networking.istio.io/details configured

D:\k8s\istio-1.4.3>\
```
####  kubectl get destinationrules -o yaml
```bash 
D:\k8s\istio-1.4.3> kubectl get destinationrules -o yaml
apiVersion: v1
items:
- apiVersion: networking.istio.io/v1alpha3
  kind: DestinationRule
  metadata:
    annotations:
      kubectl.kubernetes.io/last-applied-configuration: |
        {"apiVersion":"networking.istio.io/v1alpha3","kind":"DestinationRule","metadata":{"annotations":{},"name":"details","namespace":"default"},"spec":{"host":"details","subsets":[{"labels":{"version":"v1"},"name":"v1"},{"labels":{"version":"v2"},"name":"v2"}],"trafficPolicy":{"tls":{"mode":"ISTIO_MUTUAL"}}}}
    creationTimestamp: "2020-02-06T07:44:15Z"
    generation: 2
    name: details
    namespace: default
    resourceVersion: "15048"
    selfLink: /apis/networking.istio.io/v1alpha3/namespaces/default/destinationrules/details
    uid: 92f01863-3fc1-46d0-ba69-a9ff3d9e7c84
  spec:
    host: details
    subsets:
    - labels:
        version: v1
      name: v1
    - labels:
        version: v2
      name: v2
    trafficPolicy:
      tls:
        mode: ISTIO_MUTUAL
- apiVersion: networking.istio.io/v1alpha3
  kind: DestinationRule
  metadata:
    annotations:
      kubectl.kubernetes.io/last-applied-configuration: |
        {"apiVersion":"networking.istio.io/v1alpha3","kind":"DestinationRule","metadata":{"annotations":{},"name":"productpage","namespace":"default"},"spec":{"host":"productpage","subsets":[{"labels":{"version":"v1"},"name":"v1"}],"trafficPolicy":{"tls":{"mode":"ISTIO_MUTUAL"}}}}
    creationTimestamp: "2020-02-06T07:44:15Z"
    generation: 2
    name: productpage
    namespace: default
    resourceVersion: "15045"
    selfLink: /apis/networking.istio.io/v1alpha3/namespaces/default/destinationrules/productpage
    uid: 1585a8ed-4ad7-4e9b-a186-361bbf21ef90
  spec:
    host: productpage
    subsets:
    - labels:
        version: v1
      name: v1
    trafficPolicy:
      tls:
        mode: ISTIO_MUTUAL
- apiVersion: networking.istio.io/v1alpha3
  kind: DestinationRule
  metadata:
    annotations:
      kubectl.kubernetes.io/last-applied-configuration: |
        {"apiVersion":"networking.istio.io/v1alpha3","kind":"DestinationRule","metadata":{"annotations":{},"name":"ratings","namespace":"default"},"spec":{"host":"ratings","subsets":[{"labels":{"version":"v1"},"name":"v1"},{"labels":{"version":"v2"},"name":"v2"},{"labels":{"version":"v2-mysql"},"name":"v2-mysql"},{"labels":{"version":"v2-mysql-vm"},"name":"v2-mysql-vm"}],"trafficPolicy":{"tls":{"mode":"ISTIO_MUTUAL"}}}}
    creationTimestamp: "2020-02-06T07:44:15Z"
    generation: 2
    name: ratings
    namespace: default
    resourceVersion: "15047"
    selfLink: /apis/networking.istio.io/v1alpha3/namespaces/default/destinationrules/ratings
    uid: 098555c8-1732-4f27-af43-0ce7247c6e40
  spec:
    host: ratings
    subsets:
    - labels:
        version: v1
      name: v1
    - labels:
        version: v2
      name: v2
    - labels:
        version: v2-mysql
      name: v2-mysql
    - labels:
        version: v2-mysql-vm
      name: v2-mysql-vm
    trafficPolicy:
      tls:
        mode: ISTIO_MUTUAL
- apiVersion: networking.istio.io/v1alpha3
  kind: DestinationRule
  metadata:
    annotations:
      kubectl.kubernetes.io/last-applied-configuration: |
        {"apiVersion":"networking.istio.io/v1alpha3","kind":"DestinationRule","metadata":{"annotations":{},"name":"reviews","namespace":"default"},"spec":{"host":"reviews","subsets":[{"labels":{"version":"v1"},"name":"v1"},{"labels":{"version":"v2"},"name":"v2"},{"labels":{"version":"v3"},"name":"v3"}],"trafficPolicy":{"tls":{"mode":"ISTIO_MUTUAL"}}}}
    creationTimestamp: "2020-02-06T07:44:15Z"
    generation: 2
    name: reviews
    namespace: default
    resourceVersion: "15046"
    selfLink: /apis/networking.istio.io/v1alpha3/namespaces/default/destinationrules/reviews
    uid: b3baee3e-70c1-43df-9283-57ee89575e1a
  spec:
    host: reviews
    subsets:
    - labels:
        version: v1
      name: v1
    - labels:
        version: v2
      name: v2
    - labels:
        version: v3
      name: v3
    trafficPolicy:
      tls:
        mode: ISTIO_MUTUAL
kind: List
metadata:
  resourceVersion: ""
  selfLink: ""

D:\k8s\istio-1.4.3>
```
#### kubectl get virtualservices
```bash 
D:\k8s\istio-1.4.3>kubectl get virtualservices
NAME       GATEWAYS             HOSTS   AGE
bookinfo   [bookinfo-gateway]   [*]     5m14s
```
#### kubectl get destinationrules
```bash 
D:\k8s\istio-1.4.3>kubectl get destinationrules
NAME          HOST          AGE
details       details       2m33s
productpage   productpage   2m33s
ratings       ratings       2m33s
reviews       reviews       2m33s
```
#### kubectl get gateway
```bash 
D:\k8s\istio-1.4.3> kubectl get gateway
NAME               AGE
bookinfo-gateway   5m25s
```
#### kubectl get pods
```bash 
D:\k8s\istio-1.4.3>kubectl get pods
NAME                                READY   STATUS    RESTARTS   AGE
details-v1-78d78fbddf-896m7         1/1     Running   0          25m
nginx-deployment-54f57cf6bf-9kpk9   1/1     Running   0          106m
nginx-deployment-54f57cf6bf-kpz7g   1/1     Running   0          106m
productpage-v1-596598f447-drhzp     1/1     Running   0          25m
ratings-v1-6c9dbf6b45-sc4bt         1/1     Running   0          25m
reviews-v1-7bb8ffd9b6-2gznh         1/1     Running   0          25m
reviews-v2-d7d75fff8-qnx78          1/1     Running   0          25m
reviews-v3-68964bc4c8-hsks9         1/1     Running   0          25m

D:\k8s\istio-1.4.3>
```


#### minikube service list

```bash 
PS C:\WINDOWS\system32> minikube service list
|----------------------|---------------------------|--------------------------------|-----|
|      NAMESPACE       |           NAME            |          TARGET PORT           | URL |
|----------------------|---------------------------|--------------------------------|-----|
| default              | details                   | No node port                   |
| default              | kubernetes                | No node port                   |
| default              | nginx-deployment          | http://192.168.120.61:32645    |
| default              | productpage               | No node port                   |
| default              | ratings                   | No node port                   |
| default              | reviews                   | No node port                   |
| istio-system         | grafana                   | No node port                   |
| istio-system         | istio-citadel             | No node port                   |
| istio-system         | istio-egressgateway       | No node port                   |
| istio-system         | istio-galley              | No node port                   |
| istio-system         | istio-ingressgateway      | http://192.168.120.61:32342    |
|                      |                           | http://192.168.120.61:30390    |
|                      |                           | http://192.168.120.61:32334    |
|                      |                           | http://192.168.120.61:31182    |
|                      |                           | http://192.168.120.61:31385    |
|                      |                           | http://192.168.120.61:30105    |
|                      |                           | http://192.168.120.61:31953    |
|                      |                           | http://192.168.120.61:32288    |
| istio-system         | istio-pilot               | No node port                   |
| istio-system         | istio-policy              | No node port                   |
| istio-system         | istio-sidecar-injector    | No node port                   |
| istio-system         | istio-telemetry           | No node port                   |
| istio-system         | jaeger-agent              | No node port                   |
| istio-system         | jaeger-collector          | No node port                   |
| istio-system         | jaeger-query              | No node port                   |
| istio-system         | kiali                     | No node port                   |
| istio-system         | prometheus                | No node port                   |
| istio-system         | tracing                   | No node port                   |
| istio-system         | zipkin                    | No node port                   |
| kube-system          | kube-dns                  | No node port                   |
| kubernetes-dashboard | dashboard-metrics-scraper | No node port                   |
| kubernetes-dashboard | kubernetes-dashboard      | No node port                   |
|----------------------|---------------------------|--------------------------------|-----|
PS C:\WINDOWS\system32>
```

#### npm install

```bash 
D:\k8s\istio-1.4.3\samples\bookinfo\src\ratings>npm install
npm notice created a lockfile as package-lock.json. You should commit this file.
npm WARN ratings No description
npm WARN ratings No repository field.
npm WARN ratings No license field.

added 24 packages from 22 contributors and audited 31 packages in 2.962s
found 1 high severity vulnerability
  run `npm audit fix` to fix them, or `npm audit` for details


   ╭────────────────────────────────────────────────────────────────╮
   │                                                                │
   │      New patch version of npm available! 6.13.4 -> 6.13.7      │
   │   Changelog: https://github.com/npm/cli/releases/tag/v6.13.7   │
   │               Run npm install -g npm to update!                │
   │                                                                │
   ╰────────────────────────────────────────────────────────────────╯


D:\k8s\istio-1.4.3\samples\bookinfo\src\ratings>ncu
Checking D:\k8s\istio-1.4.3\samples\bookinfo\src\ratings\package.json
[====================] 3/3 100%

 httpdispatcher    1.0.0  →    2.1.2
 mongodb         ^2.2.31  →   ^3.5.2
 mysql           ^2.15.0  →  ^2.18.1

Run ncu -u to upgrade package.json

D:\k8s\istio-1.4.3\samples\bookinfo\src\ratings>minikube service list
```

