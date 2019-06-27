pipeline {
    agent {
        label 'pytest_nodes'
    }
    options {
        timestamps()
        ansiColor('xterm')
    }
    environment {
        PYTEST_ROOT = '$HOME/env/pytest_virtualenv'
    }
    stages {
        stage('Pytest'){
            steps {
                sh (
                    script: "${env.PYTEST_ROOT}/bin/pip3.6 install -r requirements.txt",
                    label: "Install requirements.txt"
                )
                sh "${env.PYTEST_ROOT}/bin/pytest tests/backend -ra --junitxml=pytests.xml"
            }
            post {
                always {
                    junit (
                        testResults: 'pytests.xml',
                        healthScaleFactor: 1,
                        allowEmptyResults: true,
                        testDataPublishers: [
                            [$class: 'AttachmentPublisher']
                        ],
                    )
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
