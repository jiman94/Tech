# Telemetry 

docker run -d -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name prometheus prom/prometheus:latest

docker exec -it prometheus /bin/sh


docker run -d --name=grafana -p 3000:3000 --name grafana grafana/grafana:latest

docker exec -it grafana /bin/bash

docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:latest

docker exec -it zipkin /bin/sh

---
```bash 
D:\>docker run -d -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name prometheus prom/prometheus:latest
Unable to find image 'prom/prometheus:latest' locally
latest: Pulling from prom/prometheus


D:\>docker run -d --name=grafana -p 3000:3000 --name grafana grafana/grafana:latest
Unable to find image 'grafana/grafana:latest' locally
latest: Pulling from grafana/grafana

D:\>docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:latest
Unable to find image 'openzipkin/zipkin:latest' locally
latest: Pulling from openzipkin/zipkin
D:\>docker ps -a
CONTAINER ID        IMAGE                      COMMAND                  CREATED              STATUS                          PORTS                              NAMES

59b76f7d665c        openzipkin/zipkin:latest   "/busybox/sh run.sh"     8 seconds ago        Up 7 seconds                    9410/tcp, 0.0.0.0:9411->9411/tcp   zipkin

4ad85b5a8c5b        grafana/grafana:latest     "/run.sh"                24 seconds ago       Up 23 seconds                   0.0.0.0:3000->3000/tcp             grafana

69fc533d15ea        prom/prometheus:latest     "/bin/prometheus --c…"   About a minute ago   Exited (1) About a minute ago                                      prometheus

D:\>


D:\>docker run -d -p 9090:9090 -v /home/ec2-user/apps/prometheus:/etc/prometheus --name prometheus prom/prometheus:latest
Unable to find image 'prom/prometheus:latest' locally
latest: Pulling from prom/prometheus
0f8c40e1270f: Pull complete                                                                                             626a2a3fee8c: Pull complete                                                                                             e7ed030afda4: Pull complete                                                                                             1402c1f8faad: Pull complete                                                                                             81d2279d1c55: Pull complete                                                                                             280e865d0f46: Pull complete                                                                                             40c7beb2b8e0: Pull complete                                                                                             c1be047355d9: Pull complete                                                                                             d81ddb9e06a9: Pull complete                                                                                             fb780b8f81a9: Pull complete                                                                                             5e958f95e7b4: Pull complete                                                                                             8b293a391a3d: Pull complete                                                                                             Digest: sha256:e4ca62c0d62f3e886e684806dfe9d4e0cda60d54986898173c1083856cfda0f4
Status: Downloaded newer image for prom/prometheus:latest
69fc533d15eab53c6dc2d20c5d62327d0a0eede9cd20c6692d22cf4bfc9a2daa

D:\>docker run -d --name=grafana -p 3000:3000 --name grafana grafana/grafana:latest
Unable to find image 'grafana/grafana:latest' locally
latest: Pulling from grafana/grafana
4167d3e14976: Pull complete                                                                                             f5de5b425a84: Pull complete                                                                                             0566de8a7966: Pull complete                                                                                             21558318b453: Pull complete                                                                                             9c0705e53d50: Pull complete                                                                                             0cb366e38dc9: Pull complete                                                                                             9d6a49548b66: Pull complete                                                                                             6a9f63007f4a: Pull complete                                                                                             Digest: sha256:d49afac3e472244d5d96264a759e65b7e6752c71e567b50920784952038489c2
Status: Downloaded newer image for grafana/grafana:latest
4ad85b5a8c5b96dee25c060bc087520d0288d55828b13c2ea0d906043f4b06d1

D:\>docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:latest
Unable to find image 'openzipkin/zipkin:latest' locally
latest: Pulling from openzipkin/zipkin
9ff2acc3204b: Pull complete                                                                                             69e2f037cdb3: Pull complete                                                                                             0ab175cd7cdc: Pull complete                                                                                             3e010093287c: Pull complete                                                                                             4a63134ba470: Pull complete                                                                                             8f250d77eb0d: Pull complete                                                                                             05b9d3576e9f: Pull complete                                                                                             ef0fec9b6120: Pull complete                                                                                             07f5eb79ad62: Pull complete                                                                                             a5873462a72e: Pull complete                                                                                             91f6ffe383ee: Pull complete                                                                                             8514ef1d8f34: Pull complete                                                                                             Digest: sha256:4a9b22c7ae3b2f691ad2eb02ab17d6dd86273922264987c7c176d12a867e5c6e
Status: Downloaded newer image for openzipkin/zipkin:latest
59b76f7d665cece00147fd6e567600a6ed5f5103d7d686d51ec1296665c96c7b

D:\>docker ps -a
CONTAINER ID        IMAGE                      COMMAND                  CREATED              STATUS                          PORTS                              NAMES
59b76f7d665c        openzipkin/zipkin:latest   "/busybox/sh run.sh"     8 seconds ago        Up 7 seconds                    9410/tcp, 0.0.0.0:9411->9411/tcp   zipkin
4ad85b5a8c5b        grafana/grafana:latest     "/run.sh"                24 seconds ago       Up 23 seconds                   0.0.0.0:3000->3000/tcp             grafana
69fc533d15ea        prom/prometheus:latest     "/bin/prometheus --c…"   About a minute ago   Exited (1) About a minute ago                                      prometheus

D:\>
```

