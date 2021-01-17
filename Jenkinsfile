pipeline {
    agent any
    stages {
        stage('Docker compose up') {
            steps {
//                sh 'export PATH=$PATH:/usr/local/bin'
                sh 'sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose'
                sh 'docker-compose up -d --scale chrome=3'
            }
        }
        stage('Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Docker compose down') {
            steps {
//                sh 'export PATH=$PATH:/usr/local/bin'
                sh 'docker-compose up -d --scale chrome=3'
                }
            }
        }
    }