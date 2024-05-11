pipeline {
    agent any

    stages {
        stage ('Run local test') {
            steps {
                sh './mvnw clean install -Plocal-test'
            }
        }
    }
}