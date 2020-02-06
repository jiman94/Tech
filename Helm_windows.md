Install Helm on Windows

choco install kubernetes-helm

Helm consists of Helm (Client) and Tiller (Server). 

kubectl apply -f   https://raw.githubusercontent.com/airwavetechio/helm/master/airwave-deploy-ns.json
kubectl apply -f   https://raw.githubusercontent.com/airwavetechio/helm/master/airwave-tiller-ns.json

kubectl apply -f https://raw.githubusercontent.com/airwavetechio/helm/master/rbac-tiller-role.yml


kubectl get ns
kubectl get sa
kubectl get sa -n airwave-tiller

Initialize Helm

helm init --service-account tiller --tiller-namespace airwave-tiller --skip-refresh

helm init --service-account tiller  --tiller-namespace airwave-tiller --history-max 200

helm repo update

helm install stable/mysql --namespace airwave-tiller --tiller-namespace airwave-tiller


kubectl get pods -n airwave-tiller

helm install stable/mysql --namespace airwave-deploy --tiller-namespace airwave-tiller

kubectl apply -f https://raw.githubusercontent.com/airwavetechio/helm/master/rbac-deploy-role.yml

helm install stable/mysql --namespace airwave-deploy --tiller-namespace airwave-tiller

helm ls --tiller-namespace airwave-tiller

Delete those apps
helm delete <name> --tiller-namespace airwave-tiller
Then remove all the Kubernetes changes

kubectl delete -f https://raw.githubusercontent.com/airwavetechio/helm/master/rbac-deploy-role.yml
kubectl delete -f https://raw.githubusercontent.com/airwavetechio/helm/master/rbac-tiller-role.yml
kubectl delete -f https://raw.githubusercontent.com/airwavetechio/helm/master/airwave-tiller-ns.json
kubectl delete -f https://raw.githubusercontent.com/airwavetechio/helm/master/airwave-deploy-ns.json

choco uninstall kubernetes-helm
