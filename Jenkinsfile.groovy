pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                echo "Building the project using Maven..."
            }
            post {
                always {
                    mail to: "noahcrass@hotmail.com",
                        subject: "Build Status: ${currentBuild.currentResult}",
                        body: "Build log attached: ${env.BUILD_URL}consoleText"
                }
            }
        }

        stage("Unit and Integration Tests") {
            steps {
                echo "Running unit and integration tests using JUnit..."
            }
            post {
                always {
                    mail to: "noahcrass@hotmail.com",
                        subject: "Test Stage Status: ${currentBuild.currentResult}",
                        body: "Test log attached: ${env.BUILD_URL}consoleText"
                }
            }
        }

        stage("Code Analysis") {
            steps {
                echo "Running code analysis using SonarQube..."
            }
        }

        stage("Security Scan") {
            steps {
                echo "Performing security scan using OWASP Dependency Check..."
            }
            post {
                always {
                    mail to: "noahcrass@hotmail.com",
                        subject: "Security Scan Status: ${currentBuild.currentResult}",
                        body: "Security scan log attached: ${env.BUILD_URL}consoleText"
                }
            }
        }

        stage("Deploy to Staging") {
            steps {
                echo "Deploying to staging environment (e.g., AWS EC2)..."
            }
        }

        stage("Integration Tests on Staging") {
            steps {
                echo "Running integration tests on staging environment..."
            }
        }

        stage("Deploy to Production") {
            steps {
                echo "Deploying to production environment (e.g., AWS EC2)..."
            }
        }
    }
}
