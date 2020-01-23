npm install -g npm-check-updates

ncu -u를 CLI로 입력을 하면 package.json의 dependencies와 devDependencies에 있는 각 패키지들이 최신버전으로 변경이 됩니다.

ncu - u

or

yarn 

npm install



# Yarn 설치 및 사용법
- Facebook에서 만든 자바스크립트 패키지 매니저인 Yarn


## macOS
Homebrew를 사용하는 설치

$ brew install yarn

$ brew install yarn --without-node

## Windows
Chocolatey를 사용하는 설치

$ choco install yarn

$ scoop install yarn

$ npm install -g yarn

$ yarn --version

### Yarn 사용법

#### 프로젝트를 시작할 때 초기화를 하려면(package.json을 생성합니다.)
$ yarn init

#### package.json으로부터 의존성 모듈을 설치하려면
$ yarn install

#### 의존성 모듈을 설치하려면
$ yarn add [package]
$ yarn add [package]@[version]
$ yarn add [package]@[tag]

devDependencies, peerDependencies, optionalDependencies와 같은 다른 범주의 의존성을 추가하려면

$ yarn add [package] --dev
$ yarn add [package] --peer
$ yarn add [package] --optional

#### 의존성 모듈을 업그레이드하려면

$ yarn upgrade [package]
$ yarn upgrade [package]@[version]
$ yarn upgrade [package]@[tag]

#### 의존성 모듈을 제거

$ yarn remove [package]

#### 

yarn add --dev yarn-upgrade-all

yarn upgrade --latest


ng update --all --force
npm install --save-dev @typescipt@3.4

ng update @angular/core --from 7 --to 8 --migrate-only

# angular-auth-with-jwt\src\polyfills.ts

import 'core-js/es7/reflect';
import 'core-js/es/reflect';


npm install -g karma-typescript

npm install typescript@3.5.3

npm i @angular-devkit/core



#### how-to-update-local-angular-cli-version
npm install -g @angular/cli
ng new Angular8app

npm i -g npm-check-updates
ncu -u
npm install


ng update @angular/cli @angular/core
ng update @angular/material
ng update @angular/cli @angular/core

npm install -g @angular/cli 
npm uninstall -g angular-cli
npm cache clean    
ng update rxjs
ng update @angular/core

npm install --save-dev @angular-devkit/build-angular
or,
yarn add @angular-devkit/build-angular --dev

npm install --save-dev @angular-devkit/build-angular@latest

npm uninstall @angular-devkit/build-angular

npm install --save-dev @angular-devkit/build-angular


npm install --save ag-grid-community ag-grid-angular


Debugger for chrome
