쿠버네티스 오토스케일링(kubernetes autoscaling)

kubectl apply -f autosclinging.yaml

apiVersion: autoscaling/v1
kind: HorizontalPodAutoscaler
metadata:
  name: kubernetes-simple-app-hpa
  namespace: default
spec:
  maxReplicas: 10
  minReplicas: 1
  scaleTargetRef:
    apiVersion: extensions/v1beta1
    kind: Deployment
    name: kubernetes-simple-app
  targetCPUUtilizationPercentage: 30

$  kubectl get hpa
