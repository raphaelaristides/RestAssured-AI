# 🚀 RestAssured-AI

Framework de automação de testes de APIs utilizando **Java + Rest Assured + GitHub Copilot Custom Agents**.

---

## 📖 Sobre o projeto

O **RestAssured-AI** é uma prova de conceito (PoC) que demonstra como utilizar **GitHub Copilot Custom Agents** para automatizar parte do processo de desenvolvimento de testes de APIs com Rest Assured.

O foco deste projeto não é validar a API DummyJSON, mas demonstrar como agentes especializados podem:

- compreender a arquitetura de um framework existente;
- analisar documentações de APIs e User Stories;
- gerar cenários de testes;
- reutilizar código existente;
- implementar novas automações;
- revisar a implementação antes da integração ao projeto.

Como exemplo, foi utilizada a documentação da API **Products** da DummyJSON.

### Cenários gerados automaticamente

<img width="269" height="209" alt="Cenários gerados pelos Agents" src="https://github.com/user-attachments/assets/99d4c201-911e-480b-be3b-adf9f5b7a578" />

> ❗ **Observação**
>
> O objetivo deste repositório é demonstrar a utilização da IA aplicada à automação de testes.  
> A API DummyJSON foi utilizada apenas como base para demonstrar o funcionamento dos Agents.

---

## 🎯 Objetivo

No projeto anterior, era necessário anexar manualmente arquivos `.md` ao GitHub Copilot sempre que uma nova conversa era iniciada.
Nesta versão, toda essa inteligência passou a fazer parte do próprio projeto, utilizando a estrutura oficial de **GitHub Copilot Custom Agents**.
Dessa forma, os Agents já conhecem previamente:

- ✅ a arquitetura do framework;
- ✅ a organização do projeto;
- ✅ os padrões de desenvolvimento;
- ✅ as regras de implementação;
- ✅ as boas práticas;
- ✅ os componentes reutilizáveis.

Com isso, a interação passa a ser simples e objetiva, utilizando apenas comandos curtos.

---

## 📂 Estrutura do projeto

```text
.github
│
├── agents
│   ├── mestre.agent.md
│   ├── qa-cenarios.agent.md
│   └── desenvolvedor.agent.md
│
├── instructions
│   └── framework.instructions.md
│
├── prompts
│   ├── Prompt02-Mestre.md
│   ├── Prompt03-Cenarios.md
│   └── Prompt04-Desenvolvedor.md
│
└── copilot-instructions.md

src
└── test
    └── java
        └── com.projeto.api
```


Toda a inteligência utilizada pelos Agents está centralizada na pasta .github.

🤖 AI Agents

O projeto utiliza três Agents especializados.


##  🧠 Mestre

Especialista em arquitetura e governança do framework.

Responsabilidades: 
• analisar a estrutura do projeto;

• identificar componentes reutilizáveis;

• validar impactos arquiteturais;

• definir a estratégia de implementação;

• revisar a implementação final.

## 📄Comando utilizado: 

Analise esta API: https://dummyjson.com/docs/products


##  🔍 QA Cenários

Especialista em análise funcional.

Responsabilidades:

• interpretar documentações REST;

• interpretar Swagger e OpenAPI;

• interpretar User Stories;

• gerar cenários positivos;

• gerar cenários negativos;

• gerar casos de borda;

• definir critérios de aceite.

## 📄Comando utilizado

Gere os cenários desta documentação: https://dummyjson.com/docs/products


##  👨‍💻 Desenvolvedor

Especialista em implementação.

Responsabilidades:

• reutilizar componentes existentes;

• implementar novos cenários;

• evitar duplicidade de código;

• preservar a arquitetura do framework;

• seguir os padrões definidos no projeto.

## 📄Comando utilizado:

Implemente os cenários aprovados.

```
🔄 Fluxo de trabalho
Documentação da API
        │
        ▼
🧠 Mestre
Análise arquitetural
        │
        ▼
🔍 QA Cenários
Criação dos cenários
        │
        ▼
✅ Aprovação
        │
        ▼
👨‍💻 Desenvolvedor
Implementação
        │
        ▼
🧠 Mestre
Code Review
```

🚀 Como utilizar

Depois que os Custom Agents estiverem configurados no GitHub Copilot, basta selecionar o Agent desejado e utilizar comandos simples.

1️⃣ Análise arquitetural

Selecione o Agent Mestre:

Analise esta API: https://dummyjson.com/docs/products

2️⃣ Geração dos cenários

Selecione o Agent QA Cenários:

Gere os cenários desta documentação: https://dummyjson.com/docs/products

3️⃣ Implementação

Selecione o Agent Desenvolvedor:

Implemente os cenários aprovados.

4️⃣ Revisão

Selecione o Agent Mestre:

Revise a implementação realizada.

Toda a complexidade está encapsulada nos arquivos .agent.md, .instructions.md e .prompts.

✨ Principais benefícios

✅ Não é necessário anexar prompts a cada conversa.

✅ Os Agents já conhecem a arquitetura do projeto.

✅ Reutilização de componentes existentes.

✅ Redução de duplicidade de código.

✅ Redução de duplicidade de cenários de testes.

✅ Maior padronização da automação.

✅ Fluxo de desenvolvimento mais simples.

✅ Maior escalabilidade para novas APIs e User Stories.


# ⚙️ Configurando os Agents

1. Clone o repositório.
2. Abra o projeto no VS Code.
3. Instale as extensões GitHub Copilot e GitHub Copilot Chat.
4. Os Agents serão carregados automaticamente a partir da pasta `.github/agents`.
5. Abra o Chat do Copilot, selecione o Agent desejado e utilize os comandos apresentados neste README.

## 🔄 Integração Contínua com Jenkins

Além da utilização de **GitHub Copilot Custom Agents** para auxiliar na criação e manutenção dos testes automatizados, o projeto também utiliza **Jenkins** para execução contínua da suíte de testes.

O objetivo é simular um fluxo de **Continuous Integration (CI)** no qual qualquer alteração enviada ao repositório pode iniciar automaticamente a execução dos testes da API.

O Jenkins está configurado utilizando **Pipeline as Code**, através do arquivo `Jenkinsfile` versionado na raiz do projeto.

```text
RestAssured-AI
│
├── .github
├── src
├── pom.xml
├── README.md
└── Jenkinsfile
```

---

## ⚙️ Ambiente utilizado no Jenkins

Para execução local do pipeline foram utilizadas as seguintes ferramentas:

➡️ Java 21

➡️ Apache Maven 3.9.11

➡️ Git 2.45.1

➡️ Jenkins LTS

➡️ Windows 11

O projeto continua sendo compilado utilizando a configuração definida no `pom.xml`, enquanto o Jenkins utiliza Java 21 para execução do ambiente de CI.

---

## 🚀 Pipeline Jenkins

O pipeline executa automaticamente as principais etapas necessárias para validação do projeto.

```text
GitHub
   │
   ▼
Jenkins
   │
   ▼
Checkout do projeto
   │
   ▼
Validação do ambiente
   │
   ├── java -version
   ├── mvn -version
   └── git --version
   │
   ▼
mvn clean test
   │
   ▼
Rest Assured + JUnit
   │
   ▼
Resultados dos testes
   │
   ├── Surefire Reports
   └── Extent Report
   │
   ▼
SUCCESS / FAILURE
```

O comando principal executado pelo pipeline é:

```bash
mvn clean test
```

Atualmente, a suíte executa os cenários automatizados existentes no projeto utilizando **Rest Assured + JUnit**.

---

## 📄 Jenkinsfile

A configuração do pipeline está centralizada no arquivo:

```text
Jenkinsfile
```

Exemplo da configuração utilizada:

```groovy
pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    triggers {
        pollSCM('H/2 * * * *')
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
```

Com essa abordagem, a configuração do CI também faz parte do controle de versão do projeto.

---

## 🔁 Execução automática com Poll SCM

O Jenkins utiliza **Poll SCM** para verificar periodicamente se ocorreram alterações na branch monitorada.

A configuração está definida diretamente no `Jenkinsfile`:

```groovy
triggers {
    pollSCM('H/2 * * * *')
}
```

Dessa forma, o Jenkins consulta periodicamente o repositório.

Quando não existem novos commits:

```text
Jenkins consulta o GitHub
        │
        ▼
Nenhuma alteração encontrada
        │
        ▼
Pipeline não é executado
```

Quando uma alteração é encontrada:

```text
Desenvolvedor
     │
     ▼
git commit
     │
     ▼
git push
     │
     ▼
GitHub
     │
     ▼
Jenkins detecta alteração
     │
     ▼
Pipeline iniciado automaticamente
     │
     ▼
mvn clean test
```

Durante a execução automática, o Jenkins identifica o build como iniciado por uma alteração no SCM.

Exemplo:

```text
Iniciado por uma mudança no SCM
Obtained Jenkinsfile from git
```

---

## 🌿 Branch utilizada pelo Pipeline

Durante a PoC, o Jenkins foi configurado para acompanhar a branch:

```text
feature/projeto-completo-rest-assured
```

O pipeline utiliza:

```text
Pipeline script from SCM
```

permitindo que o Jenkins obtenha diretamente do GitHub o arquivo:

```text
Jenkinsfile
```

Assim, alterações realizadas no pipeline também são versionadas junto com o código.

---

## 📊 Resultados dos testes

Após a execução do Maven, o Jenkins coleta os resultados gerados pelo **Maven Surefire**:

```text
target/surefire-reports/*.xml
```

Esses arquivos são publicados pelo comando:

```groovy
junit testResults: 'target/surefire-reports/*.xml'
```

Isso permite visualizar diretamente no Jenkins informações como:

```text
Total de testes
Testes executados
Testes aprovados
Testes com falha
Testes ignorados
```

Exemplo de execução da suíte:

```text
Tests run: 19
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

## 📈 Relatório HTML

Além dos resultados do JUnit, o framework também gera relatório HTML utilizando **Extent Reports**.

O relatório é criado em:

```text
target/relatorios/APITestRelatorio.html
```

Durante o pipeline, o Jenkins arquiva automaticamente os relatórios através de:

```groovy
archiveArtifacts artifacts: 'target/relatorios/**/*.html',
                 allowEmptyArchive: true
```

Dessa forma, cada execução do pipeline mantém seu respectivo relatório disponível como artefato do build.

---

## 🔄 Fluxo completo do projeto

Com a integração dos **Custom Agents** e do **Jenkins**, o fluxo da PoC passa a contemplar tanto a criação assistida dos testes quanto sua validação contínua.

```text
Documentação da API
        │
        ▼
🧠 Mestre
Análise arquitetural
        │
        ▼
🔍 QA Cenários
Criação dos cenários
        │
        ▼
✅ Aprovação
        │
        ▼
👨‍💻 Desenvolvedor
Implementação
        │
        ▼
🧠 Mestre
Code Review
        │
        ▼
Git Commit
        │
        ▼
Git Push
        │
        ▼
GitHub
        │
        ▼
⚙️ Jenkins
Poll SCM
        │
        ▼
Maven
mvn clean test
        │
        ▼
Rest Assured + JUnit
        │
        ▼
📊 Resultados
        │
        ├── JUnit / Surefire
        └── Extent Reports
        │
        ▼
✅ SUCCESS
ou
❌ FAILURE
```

---

## ✨ Benefícios da integração com Jenkins

✅ Execução automática dos testes após alterações no repositório.

✅ Pipeline versionado através do `Jenkinsfile`.

✅ Redução da necessidade de executar testes manualmente.

✅ Identificação rápida de regressões.

✅ Histórico de execuções no Jenkins.

✅ Visualização dos testes aprovados e com falha.

✅ Armazenamento dos relatórios HTML como artefatos.

✅ Integração entre Git, Maven, Rest Assured e JUnit.

✅ Simulação local de um ambiente real de Continuous Integration.

✅ Separação entre criação dos testes com IA e validação automática via CI.

---

## 🛠 Tecnologias

➡️ Java 11+

➡️ Maven

➡️ Rest Assured

➡️ JUnit

➡️ Log4j

➡️ Extent Reports

➡️ Git

➡️ Jenkins

➡️ Continuous Integration (CI)

➡️ GitHub Copilot

➡️ GitHub Copilot Custom Agents

➡️ Visual Studio Code


# 👨‍💻 Autor - Victor Aristides
💼 LinkedIn: https://www.linkedin.com/in/victor-aristides-b435821b3/
💻 GitHub: https://github.com/raphaelaristides
