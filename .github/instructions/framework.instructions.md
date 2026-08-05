---
name: Framework RestAssured
description: Regras arquiteturais obrigatórias do framework de automação de APIs.
applyTo: "src/test/java/com/projeto/api/**/*.java"
---

# REGRAS DO FRAMEWORK

Este projeto é um framework de automação de APIs REST.

# ARQUITETURA

Preservar a arquitetura existente.

Nunca criar arquitetura paralela.

Nunca modificar a arquitetura sem autorização explícita.

# REQUESTS

Requests devem permanecer centralizados.

Nunca utilizar RestAssured diretamente nos testes.

# PAYLOAD

Nunca criar JSON diretamente dentro dos testes.

Utilizar Builders ou mecanismos existentes.

# URLs

Nunca utilizar URLs hardcoded nos testes.

Utilizar ConfigAPI ou mecanismo existente.

# HEADERS

Não declarar Headers diretamente nos testes quando existir mecanismo centralizado.

# AUTENTICAÇÃO

Nunca implementar autenticação diretamente nos testes.

Utilizar o mecanismo existente.

# VALIDATORS

Utilizar Validators centralizados.

Não duplicar assertions.

# TESTES

Os testes devem conter somente:

- Preparação
- Execução
- Validação

# CÓDIGO

Seguir obrigatoriamente:

- SOLID
- DRY
- Clean Code
- SRP
- Open/Closed
- Baixo acoplamento
- Alta coesão

# REUTILIZAÇÃO

Antes de criar qualquer componente:

1. Procurar classe semelhante.
2. Procurar método semelhante.
3. Procurar Request semelhante.
4. Procurar Builder semelhante.
5. Procurar Validator semelhante.
6. Procurar Model semelhante.
7. Procurar Service semelhante.

Sempre priorizar reutilização.