# RestAssured-AI - Projeto de Automação de Testes API

Projeto completo de automação de testes API utilizando **Rest Assured**, **JUnit 4**, **Extent Reports** e boas práticas de engenharia de qualidade.

## 📋 Características Principais

✅ **Arquitetura Modular**: Separação clara entre camadas (Core, Testes, Listeners)
✅ **Biblioteca Reutilizável**: Classe `Biblioteca` com métodos comuns para todos os testes
✅ **Relatório HTML Unificado**: Extent Reports gerando um único HTML para todos os 10 cenários
✅ **Logging Completo**: Log4j2 para rastreamento detalhado de execução
✅ **Configuração Centralizada**: Classe `ConfigAPI` para URLs e endpoints
✅ **Padrões de Boas Práticas**: Uso de GIVEN-WHEN-THEN, tratamento de exceções, documentação

## 📁 Estrutura do Projeto

```
RestAssured-AI/
├── pom.xml                                    # Dependências Maven
├── README.md                                  # Este arquivo
├── GUIA_DE_BOAS_PRATICAS.md                  # Documentação detalhada
├── src/
│   └── test/
│       ├── java/
│       │   └── com/projeto/api/
│       │       ├── core/
│       │       │   ├── ConfigAPI.java        # Configurações centralizadas
│       │       │   ├── Relatorio.java        # Extent Reports
│       │       │   └── Biblioteca.java       # Métodos reutilizáveis
│       │       ├── testes/
│       │       │   ├── CT001ValidarStatus200.java
│       │       │   ├── CT002ValidarCredenciais.java
│       │       │   ├── CT003ValidarConsultaProdutoAutenticado.java
│       │       │   ├── CT004ValidarInclusaoProduto.java
│       │       │   ├── CT005ValidarErroAutenticacao.java
│       │       │   ├── CT006ValidarListaProdutos.java
│       │       │   ├── CT007ValidarProdutoPorID.java
│       │       │   ├── CT008ValidarAtualizacaoProduto.java
│       │       │   ├── CT009ValidarExclusaoProduto.java
│       │       │   └── CT010ValidarContentType.java
│       │       └── listeners/
│       │           └── TestListener.java     # Listener para eventos de testes
│       └── resources/
│           └── log4j2.xml                    # Configuração de logging
└── target/
    ├── relatorios/
    │   └── Relatorio.html                    # Relatório gerado
    └── logs/
        └── testes.log                        # Log de execução
```

## 🚀 Começando

### Pré-requisitos

- **Java 11+** instalado
- **Maven 3.6+** instalado
- **Git** para clonar o repositório

### Instalação

1. Clone o repositório:
```bash
git clone https://github.com/raphaelaristides/RestAssured-AI.git
cd RestAssured-AI
```

2. Instale as dependências:
```bash
mvn clean install
```

## 🧪 Executando os Testes

### Executar todos os testes
```bash
mvn test
```

### Executar teste específico
```bash
mvn test -Dtest=CT001ValidarStatus200
```

### Executar múltiplos testes
```bash
mvn test -Dtest=CT001ValidarStatus200,CT002ValidarCredenciais
```

## 📊 Relatório de Testes

Após a execução dos testes, o relatório HTML é gerado automaticamente em:

```
target/relatorios/Relatorio.html
```

O relatório inclui:
- ✅ Status de todos os testes (Pass/Fail)
- 📊 Gráficos de sucesso e eventos
- 📝 Logs detalhados de cada teste
- ⏱️ Tempo de execução
- 📈 Dashboard com estatísticas

## 📝 Cenários de Teste

### CT001 - Validar Status 200
Valida se a requisição GET retorna status 200

### CT002 - Validar Credenciais
Valida dados de credenciais do primeiro usuário

### CT003 - Consulta Produto Autenticado
Realiza login, captura token e consulta produtos

### CT004 - Inclusão de Produto
Cria novo produto e valida dados retornados

### CT005 - Erro de Autenticação
Tenta acessar recurso com token inválido

### CT006 - Lista de Produtos
Consulta lista de todos os produtos

### CT007 - Produto por ID
Consulta produto específico pelo ID

### CT008 - Atualização de Produto
Atualiza dados de um produto existente

### CT009 - Exclusão de Produto
Deleta um produto

### CT010 - Content-Type
Valida Content-Type das respostas

## 🔧 Configuração

### Alterar URL Base da API

Edite `ConfigAPI.java`:
```java
public static final String BASE_URL = "https://sua-api.com";
```

### Adicionar Novos Endpoints

Em `ConfigAPI.java`:
```java
public static final String ENDPOINT_NOVO = "/novo-endpoint";
```

### Customizar Relatório

Em `Relatorio.java`, modifique as configurações do ExtentSparkReporter:
```java
html.config().setTheme(Theme.DARK); // DARK ou STANDARD
```

## 📚 Usando a Biblioteca em Seus Testes

1. Estenda a classe `Biblioteca`:
```java
public class MeuTeste extends Biblioteca {
    @Test
    public void meuCenario() {
        // Seu código aqui
    }
}
```

2. Use os métodos disponíveis:
```java
// Requisições
Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_USERS);
Response response = fazerRequisicaoPostBody(corpo, ConfigAPI.ENDPOINT_PRODUCTS_ADD);

// Validações
validarStatusCode(response, 200, "Deve retornar 200");
validados("Descrição", esperado, obtido, "equals");

// Captura de dados
String valor = capturaDados(response, "campo.aninhado");
```

## 🛠️ Troubleshooting

### Relatório não está sendo gerado
- Verifique permissões de escrita em `target/relatorios/`
- Certifique-se de chamar `Relatorio.finalizar()` ao final

### Erro ao conectar com API
- Verifique a URL em `ConfigAPI.BASE_URL`
- Verifique conectividade com a internet
- Teste manualmente com curl ou Postman

### Logs não aparecem
- Verifique `log4j2.xml` em `src/test/resources/`
- Verifique permissões em `target/logs/`

## 📖 Padrões Utilizados

### GIVEN-WHEN-THEN
```
GIVEN: Pré-condições e dados de entrada
WHEN:  Ação a ser executada
THEN:  Validação dos resultados
```

### AAA (Arrange-Act-Assert)
- **Arrange**: Preparação dos dados
- **Act**: Execução da ação
- **Assert**: Validação dos resultados

## 🔐 Segurança

- Evite hardcoding de senhas; use variáveis de ambiente
- Não commitar credenciais reais no repositório
- Use tokens com tempo de expiração

## 📞 Suporte

Para dúvidas ou sugestões, abra uma issue no repositório.

## 📄 Licença

Este projeto é fornecido como está para fins educacionais e de automação de testes.

---

**Desenvolvido com ❤️ usando Rest Assured + JUnit + Extent Reports**