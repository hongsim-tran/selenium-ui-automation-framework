pipeline {
    agent any

    stages {
        stage ('Run local test') {
            steps {
                bat './mvnw clean install -Plocal-test'
            }
        }
    }
}