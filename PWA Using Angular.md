# PWA Using Angular 7 - Step-by-Step Guide

- SPA (Single Page Application) vs SSR (Server Side Rendering)
첫 요청시 딱 한 페이지만 불러오고 페이지 이동시 기존 페이지의 내부를 수정해서 보여주는 방식

- Nuxt.js 의 SSR
universal : Isomorphic application (Server Side Rendering + Client Side Navigation)


npm install -g @angular/cli

ng new pwa-example

cd pwa-example

ng add @angular/pwa

npm i -g http-server

ng build --prod


http-server -c-1 dist\pwa-example

or 

cd dist/pwa-example
http-server -o



ng new pwatest
cd pwatest
ng add @angular/material

ng g component auth/login
ng g service services/api
