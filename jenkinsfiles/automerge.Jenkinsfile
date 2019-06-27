// GLOBALS
podDef = """
apiVersion: v1
kind: Pod
spec:
 containers:
 - name: alpine-git
   image: alpine/git:1.0.7
   command:
   - cat
   tty: true
   resources:
     requests:
       cpu: "1000m"
       memory: "1024Mi"
     limits:
       cpu: "2000m"
       memory: "2048Mi"
"""
// EOF GLOBAL

pipeline {
    agent {
        kubernetes {
            cloud 'general-gke-cluster'
            label 'automerge-envs'
            defaultContainer 'alpine-git'
            serviceAccount 'jenkins'
            yaml "${podDef}"
        }
    }
    environment {
        REPO_TO_MERGE = "git@github.com/mkjmdski/mlodzikowski.pl.git"
    }
    options {
        timestamps()
        ansiColor('xterm')
        // Sometimes connection with pod used for those operations is lost so we want to timeout it
        timeout(
            time: 10,
            unit: 'MINUTES',
        )
        skipDefaultCheckout()
    }
    triggers {
        cron ('5 6 * * 1')
    }
    stages {
        stage('Automerge develop') {
            steps {
                runMerge("develop")
            }
        }
    }
}

def runMerge(branch) {
    git (
        url: "${env.REPO_TO_MERGE}",
        credentialsId: "git_push",
        branch: "${branch}"
    )
    sshagent(credentials: ['git_push']) {
        sh """#!/bin/sh -x
        git fetch --all
        git merge origin/master
        git push origin ${branch}
    """
    }
}
