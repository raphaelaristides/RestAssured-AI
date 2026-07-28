##### ARQUIVOS .IA #####

- Prompt02-Mestre.md
- Prompt03-Cenarios.md
- Prompt04-Desenvolvedor.md

Esses arquivos servirão como documentação permanente do projeto e poderão ser reutilizados por qualquer desenvolvedor da equipe.


#### Explicação de como utilizar os arquivos junto ao Agent ####

Com o seu projeto de automação API - Rest Assured criado inicie a conversa com o Agent:

Anexe o Prompt02-Mestre.md - Este prompt passa a ser utilizado durante toda a evolução do projeto.

Sempre que iniciar uma nova funcionalidade, envie:

Prompt02-Mestre.md + Swagger ou OpenAPI ou Documentação

# Exemplo de conversação:

Utilize o Prompt02, Analise este Swagger (ou outro que enviar)
Informe quais componentes podem ser reutilizados.
Não gere código ainda.

O objetivo é fazer o Copilot pensar antes de codificar.


#### Gerando Cenários PROMPT03 ####

Depois de entender a API no passo anterior

Utilize o Prompt03-Cenarios.md Junto com:

-Swagger
- User Story ( caso não possua utilize os criterios de aceite para base dos cenários)
- OpenAPI
- Endpoint

# Exemplo de conversação:

Utilize o Prompt03.
Analise este endpoint.
Gere todos os cenários positivos.
Negativos.
Borda.
Contrato.(caso necessite)
Segurança (caso necessite)
Não gere código.

A saída será apenas documentação dos testes.

Depois de aprovar os cenários:

#### Criando Cenários PROMPT04 ####


Utilize:

Prompt04-Desenvolvedor.md
Junto com os cenários aprovados.

Exemplo:

Utilize o Prompt04.
Implemente o cenário CT001.
Reutilize toda arquitetura existente.
Nunca crie código duplicado.


O Copilot implementará somente o necessário sem duplicações:

Requests
Services
Builders
Validators
Models
Testes



## FLUXO COMPLETO ##

Nova API

↓

Prompt02

Analisa arquitetura

↓

Prompt03

Cria cenários

↓

Aprovação dos cenários

↓

Prompt04

Implementa

↓

Code Review

↓

Commit

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


## Como Conversar com o Copilot ##

Inicie:

Utilize o Prompt02.
Analise a arquitetura.
Identifique reutilizações.
Depois aguarde minha aprovação.

Depois:

Utilize o Prompt03.
Crie todos os cenários para este endpoint.
Não gere código.

Depois:

Utilize o Prompt04.
Implemente somente o cenário CT005.
Reutilize Requests.
Services.
Builders.
Validators.
Não altere a arquitetura.


## Boas Práticas ##

- Mantenha os três prompts na pasta .ai versionada junto ao projeto.
- Inicie uma nova conversa no Copilot para cada nova demanda relevante, anexando apenas o prompt necessário para aquela etapa.
- Sempre forneça o contexto mínimo necessário (Swagger, OpenAPI, User Story ou documentação funcional) junto com o prompt.
- Peça primeiro análise e planejamento (Prompt02), depois cenários (Prompt03) e só então implementação (Prompt04).
- Revise o código gerado antes de fazer o commit, mesmo quando ele seguir os padrões do framework.