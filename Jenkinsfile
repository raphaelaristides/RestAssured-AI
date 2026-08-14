pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Verificar ambiente') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
                bat 'git --version'
            }
        }

        stage('Executar testes') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {

        always {
            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: true

            archiveArtifacts artifacts: 'target/relatorios/**/*.html',
                             allowEmptyArchive: true
        }

        success {
            echo 'Pipeline executado com sucesso!'
        }

        failure {
            echo 'Pipeline falhou. Verifique o Console Output.'
        }
    }
}