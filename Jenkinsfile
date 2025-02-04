pipeline {
    agent {
        docker {
            image 'maven:3.9-eclipse-temurin-21-alpine'  // Using JDK 21 with Maven
            args '-u root -v /var/run/docker.sock:/var/run/docker.sock'
  // Mounting Docker socket and .m2 directory
        }
    }

    environment {
        DOCKER_IMAGE = 'arvind005/java-app'
        DOCKER_TAG = "jv${BUILD_NUMBER}"  // Dynamic tag with prefix 'jv'
        GIT_REPO = 'https://github.com/your-username/your-repo.git'
        HELM_CHART_PATH = 'helm/Java_Application'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    sh 'apk add --no-cache docker git'
                    sh 'docker buildx create --use --name multiarch-builder'
                    withCredentials([usernamePassword(credentialsId: 'docker_hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                     }
                    sh 'docker buildx build -t ${DOCKER_IMAGE}:${DOCKER_TAG} --push .'
                }
            }
        }

        stage('Update Helm Chart') {
            steps {
                script {
                    // Update the image tag in values.yaml
                    sh """
                    sed -i '/image:/,/^[^ ]/ s|tag:.*|tag: ${DOCKER_TAG}|' ${HELM_CHART_PATH}/values.yaml
                    """
                    // Commit and push the updated Helm chart
                    sh """
                    git config --global user.name 'Arvindkarwal'
                    git config --global user.email 'arvindkarwal002@gmail.com'
                    git config --global --add safe.directory "\$(pwd)"
                    git add .
                    git commit -m 'Update Docker image tag to ${DOCKER_TAG}'
                    git push origin dev-branch --force
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
