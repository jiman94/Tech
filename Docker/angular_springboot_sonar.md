# SampleApplication
## Development

npm install
npm start

### PWA (Progressive Web App)  Support

```html
<script>
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./service-worker.js').then(function() {
      console.log('Service Worker Registered');
    });
  }
</script>
```

### Managing dependencies

    npm install --save --save-exact leaflet
    npm install --save-dev --save-exact @types/leaflet

   import 'leaflet/dist/leaflet.js';

  @import '~leaflet/dist/leaflet.css';

### Using Angular CLI

    ng generate component my-component

    create src/main/webapp/app/my-component/my-component.component.html
    create src/main/webapp/app/my-component/my-component.component.ts
    update src/main/webapp/app/app.module.ts

    ./mvnw -Pprod clean verify

    java -jar target/*.jar

    ./mvnw -Pprod,war clean verify

    ./mvnw verify

docker-compose -f src/main/docker/sonar.yml up -d

D:\git\jhipster-sample-app>docker ps -a
CONTAINER ID        IMAGE                            COMMAND                  CREATED             STATUS                    PORTS                                            NAMES
f007df5fd1c1        sonarqube:7.9.1-community        "./bin/run.sh"           30 minutes ago      Up 10 seconds             0.0.0.0:9092->9092/tcp, 0.0.0.0:9001->9000/tcp   docker_jhips

-Pprod clean verify sonar:sonar -Dsonar.host.url=http://localhost:9001
 admin/admin

    docker-compose -f src/main/docker/mysql.yml up -d

    docker-compose -f src/main/docker/mysql.yml down

    ./mvnw -Pprod verify jib:dockerBuild

    docker-compose -f src/main/docker/app.yml up -d


