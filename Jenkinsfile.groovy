pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', 
                          userRemoteConfigs: [[url: 'https://github.com/NoahJCross/jenkins.git']]])
            }
        }
        stage('Build') {
            steps {
                script {
                    bat 'echo Build stage'
                }
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                script {
                    bat 'echo Unit and Integration Tests stage'
                }
            }
        }
        stage('Code Analysis') {
            steps {
                script {
                    bat 'echo Code Analysis stage'
                }
            }
        }
        stage('Security Scan') {
            steps {
                script {
                    bat 'echo Security Scan stage'
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                script {
                    bat 'echo Deploy to Staging stage'
                }
            }
        }
        stage('Integration Tests on Staging') {
            steps {
                script {
                    bat 'echo Integration Tests on Staging stage'
                }
            }
        }
        stage('Deploy to Production') {
            steps {
                script {
                    bat 'echo Deploy to Production stage'
                }
            }
        }
    }
    post {
        always {
            emailext (
                to: 'dev-team@example.com',
                subject: "Jenkins Build ${currentBuild.fullDisplayName}",
                body: "Build ${currentBuild.fullDisplayName} finished with status: ${currentBuild.currentResult}",
                attachmentsPattern: '**/log/*.log'  // Adjust the pattern based on your log location
            )
        }
    }
}
