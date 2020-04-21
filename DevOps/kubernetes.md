# k8s_POD_alb-ingress-controller

# docker ps
681ec060081d  k8s_POD_alb-ingress-controller-6bdccbfc4c-4cnlr_kube-system_ce39cb3f-1a5a-11ea-9249-0a5ab6a24988_1

# docker logs -f 6834dd44dc6e

# kubectl get ingress -n pilot-service

api-pub-ingress   *       b7def77c-api-88be-298793544.ap-northeast-2.elb.amazonaws.com            80      41d
api-pvt-ingress   *       internal-b7def77c--api-f5ae-449556566.ap-northeast-2.elb.amazonaws.com   80      13s
bo-web-ingress    *       b7def77c-bow-6db2-862431489.ap-northeast-2.elb.amazonaws.com            80      97d
co-web-ingress    *       b7def77c-cow-9014-769732666.ap-northeast-2.elb.amazonaws.com            80      113m
fm-web-ingress    *       b7def77c-fmw-5b67-659265600.ap-northeast-2.elb.amazonaws.com            80      36d
fp-web-ingress    *       b7def77c-fpw-28a2-1843267330.ap-northeast-2.elb.amazonaws.com           80      36d
po-web-ingress    *       b7def77c-pow-da57-1611179938.ap-northeast-2.elb.amazonaws.com           80      91d

# sh front.sh

# docker ps
ae6ef0f1809a   k8s_co-web_co-web-deployment-5f99964db6-service_1885a83b-83c3-11ea-829a-0aa2858cdeec_0
0071b788dc99   k8s_front-web_front-web-deployment-7b74669579-service_d4dffde0-83a3-11ea-829a-0aa2858cdeec_0
00f0c4688d15   k8s_bo-web_bo-web-deployment-f8d76c487-service_f88e91cf-83a2-11ea-829a-0aa2858cdeec_0
91860443938d   k8s_front-mobile_front-mobile-deployment-589fc8d9c8-service_92c62e01-833c-11ea-829a-0aa2858cdeec_0
e06cb82d097c   k8s_po-web_po-web-deployment-85c879d595-service_c80c7877-82f0-11ea-829a-0aa2858cdeec_0

# docker restart ae6ef0f1809a 0071b788dc99 00f0c4688d15
ae6ef0f1809a
0071b788dc99
00f0c4688d15

