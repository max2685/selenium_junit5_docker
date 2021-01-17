pipeline {
    agent none
    stages {
        stage('Docker compose up') {
            steps {
                sh 'export PATH=$PATH:/usr/local/bin'
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
                sh 'export PATH=$PATH:/usr/local/bin'
                sh 'docker-compose up -d --scale chrome=3'
                }
            }
        }
    }