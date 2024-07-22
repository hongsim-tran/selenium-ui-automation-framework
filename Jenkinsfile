pipeline {
    agent any

    parameters {
      choice choices: ['testng-local', 'testng-prod', 'cucumber-local', 'cucumber-prod'], description: 'Select the test profile', name: 'PROFILE'
    }

    tools {
        jdk 'jdk 21'
    }

    stages {
        stage ('Execute tests') {
            steps {
                sh './mvnw clean install -P ${PROFILE}'
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