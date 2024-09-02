pipeline {
    agent none  // No default agent, each stage will specify its own

    tools {
        maven 'maven'  // Ensure this matches the name configured in Global Tool Configuration
    }

    stages {
        stage('Check out code from Git') {
            agent {
                label "${env.BRANCH_NAME == 'Prod' ? 'prod-node' : (env.BRANCH_NAME == 'Test' ? 'test-node' : 'dev-node')}"
            }
            steps {
                script {
                    // Checkout the branch from GitHub
                    checkout([$class: 'GitSCM',
                              branches: [[name: "${env.BRANCH_NAME}"]],
                              doGenerateSubmoduleConfigurations: false,
                              extensions: [],
                              userRemoteConfigs: [[url: 'https://github.com/Sahaja-surnoi/Hospital-BE.git', credentialsId: 'Git-Access']]
                             ])
                    // Stash the source code for later stages
                    stash name: 'source-code1', includes: '**/*'
                }
            }
        }
        stage('Build artifact') {
            agent {
                label "${env.BRANCH_NAME == 'Prod' ? 'prod-node' : (env.BRANCH_NAME == 'Test' ? 'test-node' : 'dev-node')}"
            }
            steps {
                script {
                    // Debugging output
                    echo "Building on node: ${env.NODE_NAME}"
                    echo "Branch Name: ${env.BRANCH_NAME}"
                }
                unstash 'source-code1'
                // Ensure Maven is in the path
                withMaven(maven: 'maven') {
                    sh 'mvn clean package'
                }
            }
        }
    }
}
