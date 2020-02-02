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




D:\git\jhipster-sample-app>nuc -u
'nuc'은(는) 내부 또는 외부 명령, 실행할 수 있는 프로그램, 또는
배치 파일이 아닙니다.

D:\git\jhipster-sample-app>ncu -u
Upgrading D:\git\jhipster-sample-app\package.json
[====================] 96/96 100%

 @ng-bootstrap/ng-bootstrap                      5.1.4  →         5.2.1
 core-js                                         3.5.0  →         3.6.4
 ngx-cookie                                      4.0.2  →         4.1.2
 ngx-webstorage                                  4.0.1  →         4.0.2
 rxjs                                            6.5.3  →         6.5.4
 swagger-ui-dist                                3.24.3  →        3.25.0
 @angular/cli                                   8.3.20  →        8.3.24
 @ngtools/webpack                               8.3.20  →        8.3.24
 @types/chai                                     4.2.7  →         4.2.8
 @types/jest                                   24.0.23  →        25.1.1
 @types/mocha                                    5.2.7  →         7.0.1
 @types/node                                  12.12.17  →        13.7.0
 @typescript-eslint/eslint-plugin               2.11.0  →        2.18.0
 @typescript-eslint/eslint-plugin-tslint        2.11.0  →        2.18.0
 @typescript-eslint/parser                      2.11.0  →        2.18.0
 @types/selenium-webdriver                       4.0.5  →         4.0.6
 autoprefixer                                    9.7.3  →         9.7.4
 codelyzer                                       5.2.0  →         5.2.1
 css-loader                                      3.3.2  →         3.4.2
 eslint                                          6.7.2  →         6.8.0
 eslint-config-prettier                          6.7.0  →        6.10.0
 fork-ts-checker-webpack-plugin                  3.1.1  →         4.0.3
 husky                                           3.1.0  →         4.2.1
 jest                                           24.9.0  →        25.1.0
 jest-date-mock                                  1.0.7  →         1.0.8
 lint-staged                                     8.2.1  →        10.0.7
 mini-css-extract-plugin                         0.8.0  →         0.9.0
 mocha                                           6.2.2  →         7.0.1
 protractor                                      5.4.2  →         5.4.3
 rimraf                                          3.0.0  →         3.0.1
 sass                                           1.23.7  →        1.25.0
 sass-loader                                     8.0.0  →         8.0.2
 style-loader                                    1.0.1  →         1.1.3
 terser-webpack-plugin                           2.3.0  →         2.3.4
 ts-node                                         8.5.4  →         8.6.2
 tslint                                         5.20.1  →         6.0.0
 typescript                                      3.4.5  →         3.7.5
 @openapitools/openapi-generator-cli      0.0.14-4.0.2  →  1.0.10-4.2.3
 webpack                                        4.41.2  →        4.41.5
 webpack-dev-server                              3.9.0  →        3.10.2
 workbox-webpack-plugin                          4.3.1  →         5.0.0

Run npm install to install new versions.
