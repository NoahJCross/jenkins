pipeline {
    agent any
    environment {
        EMAIL_RECIPIENT = 'developer@example.com'
        STAGING_SERVER = 'staging-server-url'
        PRODUCTION_SERVER = 'production-server-url'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    // Example tool: Maven
                    echo 'Building the code using Maven...'
                    sh 'mvn clean package'
                }
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                script {
                    // Example tools: JUnit for unit tests, TestNG for integration tests
                    echo 'Running unit tests using JUnit...'
                    sh 'mvn test'
                    echo 'Running integration tests using TestNG...'
                    sh 'mvn verify'
                }
            }
        }
        stage('Code Analysis') {
            steps {
                script {
                    // Example tool: SonarQube
                    echo 'Performing code analysis using SonarQube...'
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Security Scan') {
            steps {
                script {
                    // Example tool: SonarQube for security or OWASP Dependency-Check
                    echo 'Performing security scan using OWASP Dependency-Check...'
                    sh 'dependency-check.sh'
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                script {
                    // Example tool: AWS CLI for deployment
                    echo 'Deploying to staging server...'
                    sh 'aws deploy push --application-name my-app --s3-location s3://my-bucket/my-app.zip'
                }
            }
        }
        stage('Integration Tests on Staging') {
            steps {
                script {
                    // Example tools: JUnit or Selenium
                    echo 'Running integration tests on staging environment...'
                    sh 'mvn verify'
                }
            }
        }
        stage('Deploy to Production') {
            steps {
                script {
                    // Example tool: AWS CLI for deployment
                    echo 'Deploying to production server...'
                    sh 'aws deploy push --application-name my-app --s3-location s3://my-bucket/my-app.zip'
                }
            }
        }
    }
    post {
        success {
            script {
                // Send success email
                emailext(
                    to: EMAIL_RECIPIENT,
                    subject: 'Build Successful',
                    body: 'The build was successful. Please check the Jenkins job for details.',
                    attachLog: true
                )
            }
        }
        failure {
            script {
                // Send failure email
                emailext(
                    to: EMAIL_RECIPIENT,
                    subject: 'Build Failed',
                    body: 'The build failed. Please check the Jenkins job for details.',
                    attachLog: true
                )
            }
        }
    }
}
