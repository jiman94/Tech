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


npm install -g karma-typescript



#### how-to-update-local-angular-cli-version
npm install -g @angular/cli
ng new Angular8app

npm i -g npm-check-updates
ncu -u
npm install

npm install typescript@3.5.3


npm install --save-dev @angular-devkit/build-angular
or,
yarn add @angular-devkit/build-angular --dev

npm install --save-dev @angular-devkit/build-angular@latest

npm uninstall @angular-devkit/build-angular

npm install --save-dev @angular-devkit/build-angular


npm install --save ag-grid-community ag-grid-angular


Debugger for chrome