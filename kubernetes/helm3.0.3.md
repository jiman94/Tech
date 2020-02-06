The following commands were removed:
helm home
helm init
helm reset
helm serve


```bash 
C:\Users\ryu>helm env
HELM_REPOSITORY_CACHE="C:\Users\ryu\AppData\Local\Temp\helm\repository"
HELM_REPOSITORY_CONFIG="C:\Users\ryu\AppData\Roaming\helm\repositories.yaml"
HELM_NAMESPACE="default"
HELM_KUBECONTEXT=""
HELM_BIN="helm"
HELM_DEBUG="false"
HELM_PLUGINS="C:\Users\ryu\AppData\Roaming\helm\plugins"
HELM_REGISTRY_CONFIG="C:\Users\ryu\AppData\Roaming\helm\registry.json"

C:\Users\ryu>
```

```bash
C:\Users\ryu>helm version
version.BuildInfo{Version:"v3.0.3", GitCommit:"ac925eb7279f4a6955df663a0128044a8a6b7593", GitTreeState:"clean", GoVersion:"go1.13.6"}
```

```bash
C:\Users\ryu>helm repo add nginx https://helm.nginx.com/stable
"nginx" has been added to your repositories
```

```bash
C:\Users\ryu>helm search repo nginx-ingress
NAME                                    CHART VERSION   APP VERSION     DESCRIPTION
bitnami/nginx-ingress-controller        5.3.2           0.28.0          Chart for the nginx Ingress controller
nginx/nginx-ingress                     0.4.1           1.6.1           NGINX Ingress Controller
```

```bash
C:\Users\ryu>helm install my-ingress-controller nginx/nginx-ingress
NAME: my-ingress-controller
LAST DEPLOYED: Thu Feb  6 15:29:38 2020
NAMESPACE: default
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
The NGINX Ingress Controller has been installed.
```

```bash
C:\Users\ryu> kubectl get deployments
NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
my-ingress-controller-nginx-ingress   0/1     1            0           11s
nginx-deployment                      2/2     2            2           29m
```

```bash
C:\Users\ryu>helm uninstall my-ingress-controller
release "my-ingress-controller" uninstalled

C:\Users\ryu>
```

