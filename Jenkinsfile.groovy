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
                        def log = currentBuild.getLog(1000).join('\n')
                        writeFile file: 'build-log.txt', text: log
                        archiveArtifacts artifacts: 'build-log.txt'
                    }
                    emailext to: "s223226235@deakin.edu.au",
                             subject: "Build Status: ${currentBuild.currentResult}",
                             body: "Build log is attached.",
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
                        def log = currentBuild.getLog(1000).join('\n')
                        writeFile file: 'test-log.txt', text: log
                        archiveArtifacts artifacts: 'test-log.txt'
                    }
                    emailext to: "s223226235@deakin.edu.au",
                             subject: "Test Stage Status: ${currentBuild.currentResult}",
                             body: "Test log is attached.",
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
                        def log = currentBuild.getLog(1000).join('\n')
                        writeFile file: 'security-log.txt', text: log
                        archiveArtifacts artifacts: 'security-log.txt'
                    }
                    emailext to: "s223226235@deakin.edu.au",
                             subject: "Security Scan Status: ${currentBuild.currentResult}",
                             body: "Security scan log is attached.",
                             attachmentsPattern: 'security-log.txt'
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
