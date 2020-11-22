$ mkdir -p docker/gitlab
$ cd docker/gitlab
$ vi docker-compose.yml
version: '3.1'

services:
  web:
    image: 'gitlab/gitlab-ee:12.8.1-ee.0'
    restart: always
    hostname: 'xxxxx.compute.amazonaws.com'
    environment:
      TZ: "Asia/Seoul"
      GITLAB_OMNIBUS_CONFIG: |
        external_url 'https://xxxxx.compute.amazonaws.com'
        letsencrypt['enable'] = false
        nginx['redirect_http_to_https'] = false
        nginx['listen_https'] = false
        nginx['listen_port'] = 8929
        gitlab_rails['gitlab_shell_ssh_port'] = 2224
    ports:
      - '8929:8929'
      - '2224:22'
    volumes:
      - '/home/ubuntu/docker/gitlab/config:/etc/gitlab'
      - '/home/ubuntu/docker/gitlab/logs:/var/log/gitlab'
      - '/home/ubuntu/docker/gitlab/data:/var/opt/gitlab'

$ docker-compse up -d