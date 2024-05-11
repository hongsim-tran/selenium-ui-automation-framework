pipeline {
    agent any

    tools {
        jdk 'jdk 21'
    }

    stages {
        stage ('Execute tests in local') {
            steps {
                sh './mvnw clean install -Plocal-test'
            }

            post {
                success { allure([
                    includeProperties: false,
                    jdk: 'jdk 21',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}