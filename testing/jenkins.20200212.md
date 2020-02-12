 D:\2019\minikube-helm-jenkins> helm install my-release stable/jenkins
NAME: my-release
LAST DEPLOYED: Wed Feb 12 14:16:09 2020
NAMESPACE: default
STATUS: deployed
REVISION: 1
NOTES:
1. Get your 'admin' user password by running:
  printf $(kubectl get secret --namespace default my-release-jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode);echo
2. Get the Jenkins URL to visit by running these commands in the same shell:
  export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/component=jenkins-master" -l "app.kubernetes.io/instance=my-release" -o jsonpath="{.items[0].metadata.name}")
  echo http://127.0.0.1:8080
  

  kubectl --namespace default port-forward my-release-jenkins-fff57d6c-n4mpg 8888:8080

  kubectl --namespace default port-forward $POD_NAME 8080:8080

3. Login with the password from step 1 and the username: admin


PS D:\2019\minikube-helm-jenkins> helm install my-release stable/jenkins
NAME: my-release
LAST DEPLOYED: Wed Feb 12 14:31:35 2020
NAMESPACE: default
STATUS: deployed
REVISION: 1
NOTES:
1. Get your 'admin' user password by running:
  printf $(kubectl get secret --namespace default my-release-jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode);echo
2. Get the Jenkins URL to visit by running these commands in the same shell:
  export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/component=jenkins-master" -l "app.kubernetes.io/instance=my-release" -o jsonpath="{.items[0].metadata.name}")
  echo http://127.0.0.1:8080
  kubectl --namespace default port-forward $POD_NAME 8080:8080

3. Login with the password from step 1 and the username: admin


For more information on running Jenkins on Kubernetes, visit:
https://cloud.google.com/solutions/jenkins-on-container-engine
PS D:\2019\minikube-helm-jenkins> kubectl get pods
NAME                                READY   STATUS              RESTARTS   AGE
my-release-jenkins-fff57d6c-n4mpg   0/1     Init:ErrImagePull   0          17s
PS D:\2019\minikube-helm-jenkins> kubectl --namespace default port-forward my-release-jenkins-fff57d6c-n4mpg 8080:8080
error: unable to forward port because pod is not running. Current status=Pending
PS D:\2019\minikube-helm-jenkins> kubectl --namespace default port-forward my-release-jenkins-fff57d6c-n4mpg 8888:8080
error: unable to forward port because pod is not running. Current status=Pending
PS D:\2019\minikube-helm-jenkins> kubectl --namespace default port-forward my-release-jenkins-fff57d6c-n4mpg 8080:8888
error: unable to forward port because pod is not running. Current status=Pending
PS D:\2019\minikube-helm-jenkins> kubectl logs my-release-jenkins-fff57d6c-n4mpg
Error from server (BadRequest): container "jenkins" in pod "my-release-jenkins-fff57d6c-n4mpg" is waiting to start: PodInitializing
PS D:\2019\minikube-helm-jenkins> helm list
NAME            NAMESPACE       REVISION        UPDATED                                 STATUS          CHART           APP VERSION
my-release      default         1               2020-02-12 14:31:35.1655354 +0900 KST   deployed        jenkins-1.9.16  lts
PS D:\2019\minikube-helm-jenkins> helm delete my-release
release "my-release" uninstalled
PS D:\2019\minikube-helm-jenkins>
