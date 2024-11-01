pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/FaresMirza/TaskManager/', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package Docker Image') {
            steps {
                script {
                sh 'sudo docker build -t mvc-image -f Dockerfile .'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker stop spring-boot-app || true && docker rm spring-boot-app || true'

                    sh 'docker run -d --name spring-boot-app -p 8000:8080 mvc-image'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker containers and images if necessary
            sh 'docker system prune -f'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs for errors.'
        }
    }
}