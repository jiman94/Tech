node {
        //cleanup current user docker credentials
        sh 'rm  ~/.dockercfg || true'
        sh 'rm ~/.docker/config.json || true'
        
        //configure registry
        docker.withRegistry('https://ID.ecr.eu-west-1.amazonaws.com', 'ecr:eu-west-1:86c8f5ec-1ce1-4e94-80c2-18e23bbd724a') {
          
            //build image
            def customImage = docker.build("my-image:${env.BUILD_ID}")
            
            //push image
            customImage.push()
        }
 
https://plugins.jenkins.io/amazon-ecr/
