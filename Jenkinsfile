pipeline{
    agent any

    stages{
        stage('Build test execution docker image'){
            steps{
                sh "docker build -t simtran/automation-tests:latest ."
            }
        }

        stage('Push docker image to hub'){
            environment{
                DOCKER_HUB = credentials('docker-credentials')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh "docker push simtran/automation-tests:latest"

            }
        }
    }

    post{
        always{
            sh "docker logout"
        }
    }
}