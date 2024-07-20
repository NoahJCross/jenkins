pipeline {
    agent any
    stages{
        stage("Build"){
            steps{
                echo "Building..."
            }
            post{
                always{
                    mail to: "noahcrass@hotmail.com",
                    subject: "Build Status Email",
                    body: "Build log attached"
                }
            }
        }

        stage('Unit and Integration Tests') {
            steps {
                echo 'Running unit and integration tests using JUnit and Selenium...'
            }
        }

        stage('Code Analysis') {
            steps {
                echo 'Performing code analysis using SonarQube...'
            }
        }

        stage('Security Scan') {
            steps {
                echo 'Running security scan using OWASP ZAP...'
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo 'Deploying to staging using AWS CodeDeploy...'
            }
        }

        stage('Integration Tests on Staging') {
            steps {
                echo 'Running integration tests on staging using Postman...'
            }
        }

        stage('Deploy to Production') {
            steps {
                echo 'Deploying to production using AWS CodeDeploy...'
            }
        }
    }
}
