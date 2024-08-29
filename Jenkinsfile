pipeline {
    agent any
       tools {
           maven 'maven'
       }    
 
    stages {
        stage('check out code form git') {
            steps {
                git branch: 'main', credentialsId: 'Git-Access', url: 'https://github.com/Sahaja-surnoi/Hospital-BE.git'
            }
        }
        stage('build artifact') {
            steps {
                sh 'mvn clean package'
            }
        }
            }

    }

