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
• Comando utilizado
• Analise esta API:


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
• Comando utilizado
• Gere os cenários desta documentação:


##  👨‍💻 Desenvolvedor

Especialista em implementação.

Responsabilidades:
• reutilizar componentes existentes;
• implementar novos cenários;
• evitar duplicidade de código;
• preservar a arquitetura do framework;
• seguir os padrões definidos no projeto.
• Comando utilizado
• Implemente os cenários aprovados.


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


🚀 Como utilizar

Depois que os Custom Agents estiverem configurados no GitHub Copilot, basta selecionar o Agent desejado e utilizar comandos simples.

1️⃣ Análise arquitetural

Selecione o Agent Mestre:
Analise esta API:
https://dummyjson.com/docs/products

2️⃣ Geração dos cenários

Selecione o Agent QA Cenários:
Gere os cenários desta documentação:
https://dummyjson.com/docs/products

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


🛠 Tecnologias
Java 11+
Maven
Rest Assured
JUnit
Log4j
Extent Reports
GitHub Copilot
GitHub Copilot Custom Agents
Visual Studio Code


👨‍💻 Autor - Victor Aristides
💼 LinkedIn: https://www.linkedin.com/in/victor-aristides-b435821b3/
💻 GitHub: https://github.com/raphaelaristides
