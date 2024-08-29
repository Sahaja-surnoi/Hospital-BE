pipeline {
    agent any
       tools {
           maven 'maven'
       }    
 
    stages {
        stage('check out code form git') {
            agent { label 'dev-node' }  // Use the development node for checkout
            steps {
                git branch: 'main', credentialsId: 'Git-Access', url: 'https://github.com/Sahaja-surnoi/Hospital-BE.git'
                 stash 'source-code1'
            }
        }
        stage('build artifact') {
            agent { label 'test-node' } 
            steps {
                      unstash 'source-code1'
                sh 'mvn clean package'
            }
        }
            }

    }

