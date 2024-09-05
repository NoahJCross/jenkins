pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                echo "Building the project using Maven."
            }
            post {
                always {
                    script {
                        writeFile file: 'build-log.txt', text: "${currentBuild.rawBuild.getLog(1000).join('\n')}"
                        archiveArtifacts artifacts: 'build-log.txt'
                    }
                    mail to: "s223226235@deakin.edu.au",
                    subject: "Build Status: ${currentBuild.currentResult}",
                    body: "Build log attached.",
                    attachmentsPattern: 'build-log.txt'
                }
            }
        }

        stage("Unit and Integration Tests") {
            steps {
                echo "Running unit and integration tests using JUnit"
            }
            post {
                always {
                    script {
                        writeFile file: 'test-log.txt', text: "${currentBuild.rawBuild.getLog(1000).join('\n')}"
                        archiveArtifacts artifacts: 'test-log.txt'
                    }
                    mail to: "s223226235@deakin.edu.au",
                    subject: "Test Stage Status: ${currentBuild.currentResult}",
                    body: "Test log attached.",
                    attachmentsPattern: 'test-log.txt'
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
                    script {
                        writeFile file: 'security-scan-log.txt', text: "${currentBuild.rawBuild.getLog(1000).join('\n')}"
                        archiveArtifacts artifacts: 'security-scan-log.txt'
                    }
                    mail to: "s223226235@deakin.edu.au",
                    subject: "Security Scan Status: ${currentBuild.currentResult}",
                    body: "Security scan log attached.",
                    attachmentsPattern: 'security-scan-log.txt'
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
