[ec2-user@ip-10-222-16-64 seleniumgrid]$ sudo curl -L "https://github.com/docker/compose/releases/download/1.26.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   651  100   651    0     0  34263      0 --:--:-- --:--:-- --:--:-- 34263
100 11.6M  100 11.6M    0     0  3580k      0  0:00:03  0:00:03 --:--:-- 4741k
[ec2-user@ip-10-222-16-64 seleniumgrid]$ docker-compose up -d
Creating network "seleniumgrid_default" with the default driver
Pulling hub (selenium/hub:)...
ERROR: Get https://registry-1.docker.io/v2/selenium/hub/manifests/latest: unauthorized: incorrect username or password
[
  ec2-user@ip-10-222-16-64 seleniumgrid]$ sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
[ec2-user@ip-10-222-16-64 seleniumgrid]$ docker-compose up -d
Pulling hub (selenium/hub:)...
ERROR: Get https://registry-1.docker.io/v2/selenium/hub/manifests/latest: unauthorized: incorrect username or password
[ec2-user@ip-10-222-16-64 seleniumgrid]$ docker login
Authenticating with existing credentials...
Stored credentials invalid or expired
Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username (jiman94): jiman94
Password: 
WARNING! Your password will be stored unencrypted in /home/ec2-user/.docker/config.json.
Configure a credential helper to remove this warning. See
https://docs.docker.com/engine/reference/commandline/login/#credentials-store

Login Succeeded
[ec2-user@ip-10-222-16-64 seleniumgrid]$ 
[ec2-user@ip-10-222-16-64 seleniumgrid]$ 
[ec2-user@ip-10-222-16-64 seleniumgrid]$ docker-compose up -d
Pulling hub (selenium/hub:)...
latest: Pulling from selenium/hub


sudo curl -L "https://github.com/docker/compose/releases/download/1.25.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose


https://docs.docker.com/compose/install/

