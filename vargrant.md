kubectl apply -f "https://cloud.weave.works/k8s/net?k8s-version=$(kubectl version | base64 | tr -d '\n')"
kubectl apply -f https://raw.githubusercontent.com/ecomm-integration-ballerina/kubernetes-cluster/master/calico/calico.yaml	

kubectl apply -f https://raw.githubusercontent.com/ecomm-integration-ballerina/kubernetes-cluster/master/calico/rbac-kdd.yaml	    kubectl apply -f "https://cloud.weave.works/k8s/net?k8s-version=$(kubectl version | base64 | tr -d '\n')"
kubectl apply -f https://raw.githubusercontent.com/ecomm-integration-ballerina/kubernetes-cluster/master/calico/calico.yaml


https://www.vagrantup.com/downloads.html
