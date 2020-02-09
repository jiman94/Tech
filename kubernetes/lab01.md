```yaml
PS D:\> minikube start --vm-driver=hyperv
* minikube v1.7.1 on Microsoft Windows 10 Pro 10.0.17763 Build 17763
* Using the hyperv driver based on user configuration
* Creating hyperv VM (CPUs=2, Memory=2000MB, Disk=20000MB) ...
* Preparing Kubernetes v1.17.2 on Docker '19.03.5' ...
* Pulling images ...
* Launching Kubernetes ...
* Enabling addons: default-storageclass, storage-provisioner
* Waiting for cluster to come online ...
* Done! kubectl is now configured to use "minikube"
```
```yaml

```
```yaml
PS D:\k8s\istio-1.4.3> kubectl apply -f install/kubernetes/istio-demo.yaml
namespace/istio-system created
customresourcedefinition.apiextensions.k8s.io/attributemanifests.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/clusterrbacconfigs.rbac.istio.io created
customresourcedefinition.apiextensions.k8s.io/destinationrules.networking.istio.io created
customresourcedefinition.apiextensions.k8s.io/envoyfilters.networking.istio.io created
customresourcedefinition.apiextensions.k8s.io/gateways.networking.istio.io created
customresourcedefinition.apiextensions.k8s.io/httpapispecbindings.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/httpapispecs.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/meshpolicies.authentication.istio.io created
customresourcedefinition.apiextensions.k8s.io/policies.authentication.istio.io created
customresourcedefinition.apiextensions.k8s.io/quotaspecbindings.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/quotaspecs.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/rbacconfigs.rbac.istio.io created
customresourcedefinition.apiextensions.k8s.io/rules.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/serviceentries.networking.istio.io created
customresourcedefinition.apiextensions.k8s.io/servicerolebindings.rbac.istio.io created
customresourcedefinition.apiextensions.k8s.io/serviceroles.rbac.istio.io created
customresourcedefinition.apiextensions.k8s.io/virtualservices.networking.istio.io created
customresourcedefinition.apiextensions.k8s.io/adapters.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/instances.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/templates.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/handlers.config.istio.io created
customresourcedefinition.apiextensions.k8s.io/sidecars.networking.istio.io created
customresourcedefinition.apiextensions.k8s.io/authorizationpolicies.security.istio.io created
customresourcedefinition.apiextensions.k8s.io/clusterissuers.certmanager.k8s.io created
customresourcedefinition.apiextensions.k8s.io/issuers.certmanager.k8s.io created
customresourcedefinition.apiextensions.k8s.io/certificates.certmanager.k8s.io created
customresourcedefinition.apiextensions.k8s.io/orders.certmanager.k8s.io created
customresourcedefinition.apiextensions.k8s.io/challenges.certmanager.k8s.io created
secret/kiali created
configmap/istio-galley-configuration created
configmap/istio-grafana-custom-resources created
configmap/istio-grafana-configuration-dashboards-citadel-dashboard created
configmap/istio-grafana-configuration-dashboards-galley-dashboard created
configmap/istio-grafana-configuration-dashboards-istio-mesh-dashboard created
configmap/istio-grafana-configuration-dashboards-istio-performance-dashboard created
configmap/istio-grafana-configuration-dashboards-istio-service-dashboard created
configmap/istio-grafana-configuration-dashboards-istio-workload-dashboard created
configmap/istio-grafana-configuration-dashboards-mixer-dashboard created
configmap/istio-grafana-configuration-dashboards-pilot-dashboard created
configmap/istio-grafana created
configmap/kiali created
configmap/prometheus created
configmap/istio-security-custom-resources created
configmap/istio created
configmap/istio-sidecar-injector created
serviceaccount/istio-galley-service-account created
serviceaccount/istio-egressgateway-service-account created
serviceaccount/istio-ingressgateway-service-account created
serviceaccount/istio-grafana-post-install-account created
clusterrole.rbac.authorization.k8s.io/istio-grafana-post-install-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/istio-grafana-post-install-role-binding-istio-system created
job.batch/istio-grafana-post-install-1.4.3 created
serviceaccount/kiali-service-account created
serviceaccount/istio-mixer-service-account created
serviceaccount/istio-pilot-service-account created
serviceaccount/prometheus created
serviceaccount/istio-security-post-install-account created
clusterrole.rbac.authorization.k8s.io/istio-security-post-install-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/istio-security-post-install-role-binding-istio-system created
job.batch/istio-security-post-install-1.4.3 created
serviceaccount/istio-citadel-service-account created
serviceaccount/istio-sidecar-injector-service-account created
serviceaccount/istio-multi created
clusterrole.rbac.authorization.k8s.io/istio-galley-istio-system created
clusterrole.rbac.authorization.k8s.io/kiali created
clusterrole.rbac.authorization.k8s.io/kiali-viewer created
clusterrole.rbac.authorization.k8s.io/istio-mixer-istio-system created
clusterrole.rbac.authorization.k8s.io/istio-pilot-istio-system created
clusterrole.rbac.authorization.k8s.io/prometheus-istio-system created
clusterrole.rbac.authorization.k8s.io/istio-citadel-istio-system created
clusterrole.rbac.authorization.k8s.io/istio-sidecar-injector-istio-system created
clusterrole.rbac.authorization.k8s.io/istio-reader created
clusterrolebinding.rbac.authorization.k8s.io/istio-galley-admin-role-binding-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/istio-kiali-admin-role-binding-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/istio-mixer-admin-role-binding-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/istio-pilot-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/prometheus-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/istio-citadel-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/istio-sidecar-injector-admin-role-binding-istio-system created
clusterrolebinding.rbac.authorization.k8s.io/istio-multi created
role.rbac.authorization.k8s.io/istio-ingressgateway-sds created
rolebinding.rbac.authorization.k8s.io/istio-ingressgateway-sds created
service/istio-galley created
service/istio-egressgateway created
service/istio-ingressgateway created
service/grafana created
service/kiali created
service/istio-policy created
service/istio-telemetry created
service/istio-pilot created
service/prometheus created
service/istio-citadel created
service/istio-sidecar-injector created
deployment.apps/istio-galley created
deployment.apps/istio-egressgateway created
deployment.apps/istio-ingressgateway created
deployment.apps/grafana created
deployment.apps/kiali created
deployment.apps/istio-policy created
deployment.apps/istio-telemetry created
deployment.apps/istio-pilot created
deployment.apps/prometheus created
deployment.apps/istio-citadel created
deployment.apps/istio-sidecar-injector created
deployment.apps/istio-tracing created
service/jaeger-query created
service/jaeger-collector created
service/jaeger-agent created
service/zipkin created
service/tracing created
mutatingwebhookconfiguration.admissionregistration.k8s.io/istio-sidecar-injector created
poddisruptionbudget.policy/istio-galley created
poddisruptionbudget.policy/istio-egressgateway created
poddisruptionbudget.policy/istio-ingressgateway created
poddisruptionbudget.policy/istio-policy created
poddisruptionbudget.policy/istio-telemetry created
poddisruptionbudget.policy/istio-pilot created
poddisruptionbudget.policy/istio-citadel created
poddisruptionbudget.policy/istio-sidecar-injector created
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "attributemanifest" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "attributemanifest" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "handler" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "rule" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "rule" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "handler" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "rule" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "rule" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "rule" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "rule" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "handler" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "rule" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "rule" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "instance" in version "config.istio.io/v1alpha2"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "DestinationRule" in version "networking.istio.io/v1alpha3"
unable to recognize "install/kubernetes/istio-demo.yaml": no matches for kind "DestinationRule" in version "networking.istio.io/v1alpha3"
PS D:\k8s\istio-1.4.3>

```
```yaml

PS D:\k8s\istio-1.4.3> kubectl get pods -n istio-system
NAME                                      READY   STATUS              RESTARTS   AGE
grafana-7797c87688-5rcdk                  0/1     ContainerCreating   0          13s
istio-citadel-c5b7668c-7wkwr              0/1     ContainerCreating   0          12s
istio-egressgateway-86dfc5f774-nt7mt      0/1     ContainerCreating   0          13s
istio-galley-5c869899f9-xkwcz             0/1     ContainerCreating   0          13s
istio-grafana-post-install-1.4.3-rn8cl    1/1     Running             0          14s
istio-ingressgateway-58b9cf7f89-v4wm7     0/1     ContainerCreating   0          13s
istio-pilot-7fc8fbd85f-sl6xw              0/2     ContainerCreating   0          12s
istio-policy-65d856994-x2pcb              0/2     ContainerCreating   0          12s
istio-security-post-install-1.4.3-5b8dp   0/1     ContainerCreating   0          14s
istio-sidecar-injector-78469f44c6-92g8r   0/1     ContainerCreating   0          12s
istio-telemetry-7bf7f595f4-7hhmx          0/2     ContainerCreating   0          12s
istio-tracing-55c965d5b6-psnkp            0/1     ContainerCreating   0          12s
kiali-74fdc898b9-d6xg8                    0/1     ContainerCreating   0          12s
prometheus-c8fdbd64f-ppzhw                0/1     ContainerCreating   0          12s
PS D:\k8s\istio-1.4.3>
```
