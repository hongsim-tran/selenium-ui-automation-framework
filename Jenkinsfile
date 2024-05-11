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
                always {
                    allure includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                }
            }
        }
    }
}