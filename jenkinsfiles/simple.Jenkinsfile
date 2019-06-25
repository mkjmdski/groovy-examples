pipeline {
    // pipeline context will be allocated on master
    // but this is only in term of execution
    // such approach does not allow to checkout git repository
    // neither create any file
    agent none
    environment {
        TEST_FOO = "bar"
        TEST_FOOZ = '${TEST_FOO} inherited from TEST_FOO'
        TEST_SECRET = credentials('secret-credentials')
    }
    options {
        // this adds timestamp for each step being executed
        timestamps()
        // this allows xterm escape signs to render colors on jenkins
        ansiColor('xterm')
        // this forces new builds to set up FIFO query in order to be executed
        // that is preffered way to implement locks on resources for jenkins
        disableConcurrentBuilds()
    }
    stages {
        stage('env') {
            steps {
                echo env.TEST_FOO
                echo env.TEST_FOOZ
                echo env.TEST_SECRET
            }
        }
        stage('with agent') {
            environment {
                TEST_STAGE_SPECIFIC = "new env"
            }
            agent {
                label "qaus"
            }
            steps {
                sh "env | grep TEST"
                dir ('foo'){
                    echo pwd()
                    writeFile(
                        text: '{"name": "andrzej", "age": 21}',
                        file: 'andrzej.json',
                    )
                }
                sh "ls -al foo"
                script {
                    andrzej = readJSON("foo/andrzej.json")
                    def localAndrzej
                }
                echo andrzej.name
                echo "${localAndrzej}" ?: "andrzej was local :("
                convertToYaml(src: "foo/andrzej.json")
                sh "cat output.yaml"
            }
        }
        stage('this cant happen'){
            when {
                expression {
                    false
                }
            }
            steps {
                // I even will not exit anything as I do not have agent!!!
                sh "exit 1"
            }
            post {
                always {
                    echo "This neither happens"
                }
            }
        }
        stage("deploy prod"){
            when {
                // all of possible checks are connected with &&
                expression {
                    params.PUSH_TO_PROD != "NO"
                }
                branch 'master'
                anyOf {
                    expression {
                        true
                    }
                    expression {
                        1 == 0
                    }
                }
                environment name: 'PUSH_TO_PROD', value: 'YES!'
            }
            steps {
                echo "ansible-playbook puppet.yaml"
                echo "puppet agent --hocus-pocus"
            }
            post {
                always {
                    script {
                        (1..9).each {
                            sendSlack (
                                channel: "#just_jenkins",
                                message: "we are so sad, we shout ${it}! deploy failed"
                            )
                        }
                    }
                }
            }
        }
    }
}

void convertToYaml(args){
    writeYaml(
        file: args.get('dest', 'output.yaml'),
        data: readJSON(args.src)
    )
}