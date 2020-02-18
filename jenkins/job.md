```bash 
def jobs = [
    "SubStageA",
    "SubStageB",
    "SubStageC"
    ]


def parallelStagesMap = jobs.collectEntries {
    ["${it}" : generateStage(it)]
}

def generateStage(job) {

    return {
           node() {
               
                stage("SCM: ${job}") {
                   
                    echo "This is ${job}."
                }
        
                stage("Build: ${job}") {
                   
                    sh script: "sleep 1"
                }
           }
    }
}

pipeline {
    agent any

    parameters {
        string(defaultValue: "all", description: 'What is the target repository?', name: 'target_repo')
    //    string(defaultValue: "develop", description: 'What is the target branch?', name: 'target_branch')
    }

    stages {
        stage('non-parallel stage') {
            steps {
                script {
                    if(params.target_repo != 'all') {
                        println params.target_repo
                         echo "Hello ${params.target_repo}"

                       // sh script: "sleep 1"

                    } else {
                        echo "Hello ${params.target_repo}"
                        
                        echo 'This stage will be executed first.'
                    }
                }
            }
        }

        stage('parallel stage') {
            steps {
                script {
                    if(params.target_repo == 'all') {
                         //parallelStagesMap
                          echo "Hello ${params.target_repo}"
                          parallelStagesMap.failFast = true
                        parallel parallelStagesMap
                    }
                }
            }
        }
    }
}
```
### 참조 
https://gist.github.com/sheeeng/8d9274ccd9f9d6370feba6f5888f5dee

