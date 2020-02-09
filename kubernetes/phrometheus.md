프로메테우스(kubernetes monitoring : phrometheus)
 
모니터링 솔루션으로 최근 가장 많이 주목받고 있는건 프로메테우스(phrometheus)입니다. 

https://github.com/prometheus/prometheus/blob/master/documentation/examples/prometheus-kubernetes.yml


kubectl create configmap prometheus-kubernetes --from-file=./prometheus-kubernetes-config.yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: prometheus-app
  labels:
    app: prometheus-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: prometheus-app
  template:
    metadata:
      labels:
        app: prometheus-app
    spec:
      containers:
      - name: prometheus-app
        image: prom/prometheus:v2.3.2
        args:
          - "--config.file=/etc/prometheus/prometheus-kubernetes-config.yaml"
        ports:
          - containerPort: 9090
        volumeMounts:
          - name: config-volume
            mountPath: /etc/prometheus
          - name: storage-volume
            mountPath: /prometheus/
      volumes:
        - name: config-volume
          configMap:
            name: prometheus-kubernetes
        - name: storage-volume
          emptyDir: {}
---          
apiVersion: v1
kind: Service
metadata:
  labels:
    app: prometheus-app
  name: prometheus-app-svc
  namespace: default
spec:
  ports:
  - nodePort: 30990
    port: 9090
    protocol: TCP
    targetPort: 9090
  selector:
    app: prometheus-app
  type: NodePort



grafana.yaml


apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: grafana-app
spec:
  replicas: 1
  template:
    metadata:
      labels:
        k8s-app: grafana
    spec:
      containers:
      - name: grafana
        image: grafana/grafana:5.2.3
        ports:
        - containerPort: 3000
          protocol: TCP
        env:
        - name: GF_SERVER_HTTP_PORT
          value: "3000"
        - name: GF_AUTH_BASIC_ENABLED
          value: "false"
        - name: GF_AUTH_ANONYMOUS_ENABLED
          value: "true"
        - name: GF_AUTH_ANONYMOUS_ORG_ROLE
          value: Admin
        - name: GF_SERVER_ROOT_URL
          value: /
---
apiVersion: v1
kind: Service
metadata:
  labels:
    kubernetes.io/name: grafana-app
  name: grafana-app
spec:
  ports:
  - port: 3000
    targetPort: 3000
    nodePort: 30300
  selector:
    k8s-app: grafana
  type: NodePort



 http://localhost:30300/
