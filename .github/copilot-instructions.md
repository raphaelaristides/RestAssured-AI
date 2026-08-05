# CONTEXTO DO PROJETO

Este projeto é um framework de automação de APIs REST desenvolvido em Java.

## STACK

- Java
- Maven
- RestAssured
- JUnit
- Extent Reports

## CÓDIGO

O código do framework está localizado em:

src/test/java/com/projeto/api

## AGENTES

Este projeto possui três agentes especializados:

### Mestre

Responsável pela governança e arquitetura.

Arquivo:

.github/agents/mestre.agent.md

### QA Cenários

Responsável pela análise funcional e criação de cenários.

Arquivo:

.github/agents/qa-cenarios.agent.md

### Desenvolvedor

Responsável pela implementação dos cenários.

Arquivo:

.github/agents/desenvolvedor.agent.md

## FLUXO

A evolução de uma nova funcionalidade deve seguir:

User Story
↓
Mestre
↓
Análise arquitetural
↓
QA Cenários
↓
Cenários de teste
↓
Aprovação
↓
Desenvolvedor
↓
Implementação
↓
Execução dos testes

## REGRA PRINCIPAL

Nunca alterar a arquitetura existente sem autorização explícita.

Sempre priorizar reutilização.

Nunca duplicar código.

Nunca utilizar RestAssured diretamente dentro dos testes.