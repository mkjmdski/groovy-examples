nfsDir='/home/jenkins-slave/jira-scripts'
pipeline {
    options {
        timestamps()
        ansiColor('x-term')
        skipDefaultCheckout()
        timeout(time: 15, unit: 'MINUTES')
    }
    agent {
        label 'nfs-server'
    }
    stages {
        stage('Checkout scripts to staging'){
            steps {
                dir("${nfsDir}/staging"){
                    checkout scm
                }
            }
        }
        stage('Checkout scripts to production'){
            input {
                message 'Do you want to update production scripts?'
                ok 'Yes, please'
            }
            steps {
                sh "cp -r ${nfsDir}/staging/* ${nfsDir}/production"
            }
        }
    }
}
