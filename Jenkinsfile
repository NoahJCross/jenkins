pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    // Example for Maven
                    sh 'mvn clean install'
                }
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                script {
                    // Example for JUnit
                    sh 'mvn test'
                }
            }
        }
        stage('Code Analysis') {
            steps {
                script {
                    // Example for SonarQube
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Security Scan') {
            steps {
                script {
                    // Example for OWASP ZAP
                    sh 'zap-cli quick-scan http://your-staging-url'
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                script {
                    // Example for AWS CLI
                    sh 'aws deploy --application-name my-app --deployment-group-name staging-group'
                }
            }
        }
        stage('Integration Tests on Staging') {
            steps {
                script {
                    // Example for Selenium
                    sh 'selenium-runner http://your-staging-url'
                }
            }
        }
        stage('Deploy to Production') {
            steps {
                script {
                    // Example for AWS CLI
                    sh 'aws deploy --application-name my-app --deployment-group-name production-group'
                }
            }
        }
    }
    post {
        always {
            // Send email notification with build status and logs
            mail to: 'dev-team@example.com',
                 subject: "Jenkins Build ${currentBuild.fullDisplayName}",
                 body: "Build ${currentBuild.fullDisplayName} finished with status: ${currentBuild.currentResult}",
                 attachLog: true
        }
    }
}
