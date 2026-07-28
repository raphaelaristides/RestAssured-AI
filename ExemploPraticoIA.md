@@ EXEMPLO DE IMPLEMENTAÇÃO NOVA US PARA AUTOMATIZAR @@

Imagine que você já possui este escopo de projeto abaixo (ou o seu projeto):

RestAssured-AI

src
 ├── main
 │   ├── requests
 │   ├── services
 │   ├── validators
 │   ├── builders
 │   ├── models
 │   ├── utils
 │   ├── helpers
 │   └── ...
 │
 └── test


Chega uma nova User Story.

Exemplo:

User Story - 001

Como cliente da API,desejo consultar meus pedidos para visualizar meu histórico.

Critérios de aceite:

Endpoint GET /orders
Deve retornar 200
Deve listar apenas pedidos do usuário autenticado
Deve retornar paginação
Quando não existir pedido deve retornar lista vazia
Quando token for inválido retornar 401

Agora veja como utilizar cada prompt.


Utilize o Prompt02.
Analise toda a estrutura do framework existente.
Realize um inventário completo do projeto antes de qualquer implementação.
Considere a User Story abaixo.

Identifique:

• Requests existentes;
• Services existentes;
• Builders existentes;
• Validators existentes;
• Models existentes;
• Helpers e Utils disponíveis;
• Factories existentes;
• Configurações reutilizáveis (Environment, Authentication, BaseTest, RequestSpecification, etc.);
• Componentes que podem ser reutilizados;
• Componentes que precisarão ser alterados;
• Componentes realmente novos que precisarão ser criados;
• Dependências entre os componentes;
• Impactos arquiteturais da nova funcionalidade.

Não gere código,apenas realize a análise técnica e proponha uma estratégia de implementação.

##### ETAPA 2 — Prompt03

Agora que sei como vou desenvolver, peça somente os cenários.

Exemplo:

Utilize o Prompt03.
Com base nesta User Story,
gere todos os cenários necessários.
Não gere código.
Ele produzirá algo como:

CT001
Validar consulta de pedidos
200
---
CT002
Validar usuário sem pedidos
200
Lista vazia
---
CT003
Token inválido
401
---
CT004
Token expirado
401
---
CT005
Sem autenticação
401
---
CT006
Página inexistente
200
Lista vazia
---
CT007
Limite máximo de paginação
200
---
CT008
Ordenação crescente
200
---
...

Agora você tem uma especificação de testes, ainda sem escrever uma linha de código.

##### ETAPA 3 — Você escolhe o cenário ou os cenários que deseja criar

Você pode dizer:

Vamos implementar apenas CT001.

##### ETAPA 4 — Prompt04

Agora sim, Você envia o Prompt04 junto com o cenário escolhido.

Exemplo:

Utilize o Prompt04.
Implemente somente o CT001 ( todos os cenários )
Reutilize integralmente a arquitetura existente.
Caso alguma classe já exista, reutilize-a.
Crie apenas componentes inexistentes.

Agora o Copilot entende que deve criar somente:

OrderRequest
OrderService
OrderValidator
OrderModel
OrderTest

Sem duplicar:

Authentication
RequestSpecification
BaseTest
Logger
Utils
Helpers
EnvironmentConfig
