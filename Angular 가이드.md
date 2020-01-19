# Angular 가이드 

> SPA vs SSR & Nuxt.js

npm install -g @angular/cli

ng new client

scss
cd client

ng serve


### Add Angular 8 Routing and Navigation

#### Open `src/app/app.module.ts`

ng g component auth/login
ng g component auth/register
ng g component home
ng g component admin
ng g component bycategory
ng g component details
ng g component category
ng g component category/category-details
ng g component category/category-add
ng g component category/category-edit
ng g component post
ng g component post/post-details
ng g component post/post-add
ng g component post/post-edit

###  Add Angular 8 Service (HttpClient, RxJS, Observable)
#### Open `src/app/app-routing.module.ts`

ng g service auth
ng g service home
ng g service category
ng g service post

### ng add @angular/material
