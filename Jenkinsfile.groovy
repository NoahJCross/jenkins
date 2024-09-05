pipeline {
    agent any

    def getLogContent() {
        return readFile("${env.WORKSPACE}/console.log")
    }
    stages {
        stage("Build") {
            steps {
                echo "Building the project using Maven."
            }
            post {
                always {
                    emailext (
                        to: s223226235@deakin.edu.au, 
                        subject: "Build Status ${currentBuild.currentResult}",
                        body: "Build details: ${env.BUILD_URL}",
                        attachLog: true,
                        mimeType: 'text/html'
                    )
                }
            }
        }

       stage("Unit and Integration Tests") {
            steps {
                echo "Running unit and integration tests using JUnit"
            }
            post {
                always {
                    emailext (
                        to: s223226235@deakin.edu.au, 
                        subject: "Test Status ${currentBuild.currentResult}",
                        body: "Test details: ${env.BUILD_URL}",
                        attachLog: true,
                        mimeType: 'text/html'
                    )
                }
            }
        }

        stage("Code Analysis") {
            steps {
                echo "Running code analysis using SonarQube"
            }
        }

        stage("Security Scan") {
            steps {
                echo "Performing security scan using OWASP Dependency Check"
            }
            post {
                always {
                    emailext (
                        to: s223226235@deakin.edu.au, 
                        subject: "Security Status ${currentBuild.currentResult}",
                        body: "Security details: ${env.BUILD_URL}",
                        attachLog: true,
                        mimeType: 'text/html'
                    )
                }
            }
        }

        stage("Deploy to Staging") {
            steps {
                echo "Deploying to staging environment using AWS"
            }
        }

        stage("Integration Tests on Staging") {
            steps {
                echo "Running integration tests on staging environment"
            }
        }

        stage("Deploy to Production") {
            steps {
                echo "Deploying to production environment using AWS"
            }
        }
    }
}
