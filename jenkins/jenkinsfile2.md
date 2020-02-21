#### Declarative pipeline multiple stages in parallel when use loop

def jobs = ["JobA", "JobB", "JobC"]

def parallelStagesMap = jobs.collectEntries {
    ["${it}" : generateStage(it)]
}

def generateStage(job) {
    return {
        stage("stage: ${job}") {
                echo "This is ${job}."
                sh script: "sleep 15"
        }
    }
}


pipeline {
    agent any
    triggers {
        cron('* * * * *') //cron('15 8 * * *')
    }

    stages {
        stage('non-parallel stage') {
            steps {
                echo 'This stage will be executed first.'
            }
        }

        stage('parallel stage') {
            steps {
                script {
                    parallel parallelStagesMap
                }
            }
        }
    }
}

---

def jobs = ["JobA", "JobB", "JobC"]
def parallelStagesMap = jobs.collectEntries {
 ["${it}" : generateStage(it)]
}

def generateStage(job) {
    return {
        stage("stage: ${job}") {
            stages {
                stage ('complie') {
                    agent xxx
                    steps {
                        echo "${job} complie."
                    }
                } 
                stage(‘build') {
                    steps {
                        echo “${job} build."
                    }
                }
             } 
         }
     }
 }

pipeline {
     agent any 
     stages {

         stage(‘xxx') {
          ...
          }

         stage('parallel stage') {
             steps {
                 script{ 
                     parallel parallelStagesMap 
                 }
             }
         }
     }
 }
