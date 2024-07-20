pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Building the code using Maven...'
                bat 'mvn clean install'
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                echo 'Running unit and integration tests...'
                bat 'mvn test'
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'Performing code analysis...'
                bat 'mvn pmd:pmd'
            }
        }
        stage('Security Scan') {
            steps {
                echo 'Performing security scan...'
                // Replace with your security scanning command
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to staging...'
                // Replace with your staging deployment command
            }
        }
        stage('Integration Tests on Staging') {
            steps {
                echo 'Running integration tests on staging...'
                // Replace with your staging integration tests command
            }
        }
        stage('Deploy to Production') {
            steps {
                echo 'Deploying to production...'
                // Replace with your production deployment command
            }
        }
    }
    post {
        success {
            emailext(
                to: 'developer@example.com',
                subject: "Build Successful",
                body: "The build was successful.",
                attachLog: true
            )
        }
        failure {
            emailext(
                to: 'developer@example.com',
                subject: "Build Failed",
                body: "The build failed. Check the logs for details.",
                attachLog: true
            )
        }
    }
}
