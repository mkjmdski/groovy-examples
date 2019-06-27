library "library"
pipeline {
    agent none
    options {
       timestamps()
       ansiColor('xterm')
    }
    stages {
        stage('Check trigger'){
            parallel {
                stage('Webhook'){
                    when {
                        triggeredBy 'GitLabWebHookCause'
                    }
                    steps {
                        script {
                            actions = getActions(true)
                        }
                    }
                }
                stage('Cronjob') {
                    when {
                        triggeredBy 'TimerTrigger'
                    }
                    steps {
                        script {
                            actions = getActions(false)
                        }
                    }
                }
                stage('User') {
                    when {
                        triggeredBy 'UserIdCause'
                    }
                    steps {
                        script {
                            actions = getActions(params.USE_CACHE)
                        }
                    }
                }
            }
        }
        stage('Docker actions') {
            options {
                timeout(
                    time: 95,
                    unit: 'MINUTES',
                )
            }
            when {
                // if job is triggered other way, and actions are not set -> ignore
                expression {
                    actions
                }
            }
            stages {
                stage('build images'){
                    agent {
                        label 'agents'
                    }
                    steps {
                        script {
                            actions.each { sh it }
                        }
                    }
                }
                stage('pull images'){
                    failFast false
                    parallel {
                        stage('agent_1'){
                            agent {
                                label 'agent_1'
                            }
                            steps {
                                // only pull
                                sh actions[0]
                            }
                        }
                        stage('agent_2'){
                            agent {
                                label 'agent_2'
                            }
                            steps {
                                // only pull
                                sh actions[0]
                            }
                        }
                    }
                }
            }
        }
    }
    post {
        unsuccessful {
            mail (
                subject: "[Jenkins] ${env.JOB_NAME} unsuccessful",
                to: "developers@company.com",
                body: "You can point to this <a href='${env.BUILD_URL}'>url</a> for more details."
            )
        }
    }
}

def getActions(withCache){
    currentBuild.description = "<strong>cache used: ${withCache}</strong>"
    return [
        "docker-compose pull --ignore-pull-failures --include-deps",
        "docker-compose build --force-rm ${withCache ? '' : '--no-cache'}",
        "docker-compose push",
    ]
}
