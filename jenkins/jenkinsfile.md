 echo "Running ${env.BUILD_ID} ${env.BUILD_DISPLAY_NAME} on ${env.NODE_NAME} and JOB ${env.JOB_NAME}"

 node {
    def hello = 'Hello jojoldu' // 변수선언
    stage ('clone') {
        git 'https://github.com/jojoldu/jenkins-pipeline.git' // git clone
    }
    dir ('sample') { // clone 받은 프로젝트 안의 sample 디렉토리에서 stage 실행
        stage ('sample/execute') {
            sh './execute.sh'
        }
    }
    stage ('print') {
        print(hello) // 함수 + 변수 사용
    }
}

// 함수 선언 (반환 타입이 없기 때문에 void로 선언, 있다면 def로 선언하면 됨)
void print(message) {
    echo "${message}"
}



####  bestpractices-docker-jenkins/best-practices/Jenkinsfile.ecr

```bash 
node {
	def app

	stage('Clone repository') {
		checkout scm
	}

	stage('Build image') {
		app = docker.build('0123456789.ecr.eu-west-1.amazonaws.com/mjuuso/example-app')
	}

	stage('Test') {
		app.inside {
			sh 'npm test'
		}
	}

	stage('Push image') {
		docker.withRegistry('https://0123456789.ecr.eu-west-1.amazonaws.com', 'ecr:eu-west-1:my-aws-credentials') {
			app.push("${env.BRANCH_NAME}-latest")
			app.push("${env.BRANCH_NAME}-${env.BUILD_NUMBER}")
		}
	}
}

```

### DevOps – Pushing Docker Image Into ECR

### Jenkins를 사용하여 Amazon ECR에서 Kubernetes로 Docker 컨테이너를 자동 배포하는 방법

> Install Jenkins Amazon ECR Plugin

> Jenkinsfile

```bash
pipeline
{
    options
    {
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }
    agent any
    environment 
    {
        VERSION = 'latest'
        PROJECT = 'tap_sample'
        IMAGE = 'tap_sample:latest'
        ECRURL = 'http://999999999999.dkr.ecr.eu-central-1.amazonaws.com'
        ECRCRED = 'ecr:eu-central-1:tap_ecr'
    }
    stages
    {
        stage('Build preparations')
        {
            steps
            {
                script 
                {
                    // calculate GIT lastest commit short-hash
                    gitCommitHash = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
                    shortCommitHash = gitCommitHash.take(7)
                    // calculate a sample version tag
                    VERSION = shortCommitHash
                    // set the build display name
                    currentBuild.displayName = "#${BUILD_ID}-${VERSION}"
                    IMAGE = "$PROJECT:$VERSION"
                }
            }
        }
        stage('Docker build')
        {
            steps
            {
                script
                {
                    // Build the docker image using a Dockerfile
                    docker.build("$IMAGE","examples/pipelines/TAP_docker_image_build_push_ecr")
                }
            }
        }
        stage('Docker push')
        {
            steps
            {
                script
                {
                    // login to ECR - for now it seems that that the ECR Jenkins plugin is not performing the login as expected. I hope it will in the future.
                    sh("eval \$(aws ecr get-login --no-include-email | sed 's|https://||')")
                    // Push the Docker image to ECR
                    docker.withRegistry(ECRURL, ECRCRED)
                    {
                        docker.image(IMAGE).push()
                    }
                }
            }
        }
    }
    
    post
    {
        always
        {
            // make sure that the Docker image is removed
            sh "docker rmi $IMAGE | true"
        }
    }
} 
```
