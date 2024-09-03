pipeline {
    agent none 

    tools {
        maven 'maven' 
    }

    environment {
         NODE_LABEL = "${env.BRANCH_NAME == 'dev' ? 'dev-node' : (env.BRANCH_NAME == 'test' ? 'test-node' : (env.BRANCH_NAME == 'UAT' ? 'UAT-node' : 'prod-node'))}"
    }

    stages {
        stage('Check out code from Git') {
            agent {
                label "${env.NODE_LABEL}" 
            }
            steps {
                script {
                    checkout scm
                    stash name: 'source-code1', includes: '**/*'
                }
            }
        }
        stage('Build artifact') {
            agent {
                label "${env.NODE_LABEL}"
            }
            steps {
                script {
                    unstash 'source-code1'
                    withMaven(maven: 'maven') {
                        sh 'mvn clean package'
                    }
                }
            }
        }
    }
}
