pipeline {
    agent any
    stages {
        stage('Docker compose up') {
            steps {
//                sh 'export PATH=$PATH:/usr/local/bin'
//                sh 'docker --version'
//                sh 'cd /usr/local/Cellar/docker-compose/1.27.4_2'
                sh "cd ~"
                sh "pwd"
                sh "whoami"
                echo "PATH is: $PATH"
                sh "docker-compose up -d --scale chrome=3"
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