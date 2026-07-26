# Guia de Boas Práticas - Automação com Rest Assured

## 📚 Introdução

Este guia documenta as melhores práticas implementadas no projeto RestAssured-AI para automação de testes API.

## 1. Arquitetura e Organização

### 1.1 Separação de Responsabilidades

```
core/           → Funcionalidades centrais (Relatorio, Biblioteca, ConfigAPI)
testes/         → Casos de teste específicos (CT001, CT002, ...)
listeners/      → Eventos e hooks de execução
resources/      → Arquivos de configuração (log4j2.xml)
```

### 1.2 Princípio DRY (Don't Repeat Yourself)

Todos os métodos reutilizáveis estão centralizados em `Biblioteca.java`:

```java
// ✅ BOM: Código reutilizável
public static Response fazerRequisicaoGET(String endpointPath) {
    // Implementação centralizada
}

// ❌ RUIM: Código duplicado em cada teste
Response response = given()
    .log().all()
    .when()
    .get(url)
    .then()
    .extract().response();
```

## 2. Padrão de Testes

### 2.1 Estrutura GIVEN-WHEN-THEN

Todos os testes seguem o padrão Gherkin:

```java
@Test
public void CT001_ValidarStatus200() {
    // GIVEN: Sistema pronto para realizar requisição
    
    // WHEN: Executa GET no endpoint de teste
    Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_TEST);

    // THEN: Valida se o status retornado é 200
    validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Mensagem clara");
}
```

### 2.2 Nomenclatura Clara

```
✅ CT001_ValidarStatus200         → Claro e descritivo
✅ validarStatusCode()             → Nome da função descreve ação
❌ test1()                         → Vago
❌ validar()                       → Incompleto
```

### 2.3 Uma Responsabilidade por Teste

```java
// ✅ BOM: Teste com responsabilidade única
@Test
public void CT002_ValidarCredenciais() {
    Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_USERS);
    validarStatusCode(response, 200, "Status deve ser 200");
    validados("ID do usuário", 1, campoId(response, 0), "equals");
}

// ❌ RUIM: Teste fazendo muitas coisas
@Test
public void testarTudo() {
    // Login, criar produto, atualizar, deletar... tudo em um teste
}
```

## 3. Tratamento de Erros

### 3.1 Try-Catch em Métodos Críticos

```java
public static Response fazerRequisicaoGET(String endpointPath) {
    try {
        Relatorio.logInfo("GET → " + endpointPath);
        Response response = RestAssured
            .given()
            .log().all()
            .when()
            .get(endpointPath)
            .then()
            .extract().response();
        return response;
    } catch (Exception e) {
        logger.error(">>> Erro ao executar GET: " + e.getMessage(), e);
        Relatorio.logFail("Erro ao executar GET: " + e.getMessage());
        throw e;  // Re-lança a exceção após logging
    }
}
```

### 3.2 Logging em Múltiplos Níveis

```java
// INFO: Informações gerais
Relatorio.logInfo("GET → " + endpointPath);

// PASS: Validações bem-sucedidas
Relatorio.logPass("Status 200 OK!");

// FAIL: Validações falhadas
Relatorio.logFail("Erro: esperado 200 mas recebeu 404");

// WARNING: Situações incomuns
Relatorio.logWarning("Campo não encontrado, usando valor padrão");
```

## 4. Configuração Centralizada

### 4.1 URLs e Endpoints

Em vez de hardcoding em cada teste:

```java
// ✅ BOM: Centralizado em ConfigAPI.java
public static final String ENDPOINT_USERS = "/users";

// Usado nos testes:
Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_USERS);

// ❌ RUIM: Hardcoded
Response response = fazerRequisicaoGET("/users");
```

### 4.2 Status Codes

```java
// ✅ BOM
validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Descrição");

// ❌ RUIM
validarStatusCode(response, 200, "Descrição");
```

## 5. Criação de Dados de Teste

### 5.1 Massa de Dados Centralizada

```java
// ✅ BOM: Centralizado em Biblioteca.java
public static final String NOVO_PRODUTO_JSON = """
{
  "title": "Produto QA",
  "price": 22
}
""";

// ❌ RUIM: Espalhado pelo código
Map<String, Object> produto = new HashMap<>();
produto.put("title", "Produto QA");
produto.put("price", 22);
```

### 5.2 Métodos Auxiliares para Criar Dados

```java
// ✅ BOM: Método reutilizável
public static Map<String, Object> criarCorpoLogin(String user, String pass) {
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("username", user);
    requestBody.put("password", pass);
    return requestBody;
}

// Uso nos testes:
Map<String, Object> credentials = criarCorpoLogin(username, password);
```

## 6. Validações Efetivas

### 6.1 Validações Explícitas

```java
// ✅ BOM: Claro o que está sendo validado
validados("Título do produto", "Produto QA", titulo, "equals");
validarStatusCode(response, 200, "Deve retornar sucesso");

// ❌ RUIM: Implícito
assert resposta != null;
assert status == 200;
```

### 6.2 Tipo Seguro com Genéricos

```java
// ✅ BOM: Funciona com qualquer tipo
public static <T> void validados(String descricao, T esperado, T obtido, String tipo) {
    // Validação genérica
}

// Uso com diferentes tipos:
validados("ID", 1, campoId(response, 0), "equals");           // Integer
validados("Nome", "João", nome, "equals");                    // String
validados("Preço", 99.99, preco, "equals");                   // Double
```

### 6.3 Operadores de Comparação

```java
// ✅ Suportados
validados("Descrição", esperado, obtido, "equals");    // Igualdade
validados("Descrição", "test", "test123", "contains"); // Contém

// Extensível para novos operadores
```

## 7. Logging e Rastreabilidade

### 7.1 Logger em Todas as Classes

```java
private static final Logger logger = LogManager.getLogger(MinhaClasse.class);

// Uso
logger.info("Mensagem informativa");
logger.error("Erro encontrado", exception);
```

### 7.2 Mensagens Significativas

```java
// ✅ BOM
logger.info(">>> Resposta GET: " + response.asString());
Relatorio.logInfo("Status: " + response.getStatusCode());

// ❌ RUIM
logger.info("ok");
Relatorio.logInfo("resp");
```

## 8. Relatórios

### 8.1 Relatório Unificado

Todos os 10 testes geram um único HTML:

```java
// Automático: cada teste chama Relatorio.criarTeste(nome)
// Ao final: Relatorio.finalizar() gera o HTML

// Localização: target/relatorios/Relatorio.html
```

### 8.2 Informações do Sistema no Relatório

```java
extent.setSystemInfo("Ambiente", "Teste");
extent.setSystemInfo("OS", System.getProperty("os.name"));
extent.setSystemInfo("Java Version", System.getProperty("java.version"));
```

## 9. Documentação

### 9.1 JavaDoc em Métodos Públicos

```java
/**
 * Realiza uma requisição GET simples
 * 
 * @param endpointPath Caminho do endpoint (ex: /users)
 * @return Response da requisição
 * @throws Exception Se ocorrer erro na requisição
 */
public static Response fazerRequisicaoGET(String endpointPath) {
    // Implementação
}
```

### 9.2 Comentários em Testes

```java
@Test
public void CT001_ValidarStatus200() {
    // GIVEN: Sistema pronto para realizar requisição
    
    // WHEN: Executa GET no endpoint de teste
    Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_TEST);

    // THEN: Valida se o status retornado é 200
    validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status 200 OK");
}
```

## 10. Padrões Avançados

### 10.1 ThreadLocal para Contexto de Teste

```java
private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

public static void criarTeste(String nome) {
    ExtentTest extentTest = extent.createTest(nome);
    test.set(extentTest);  // Específico para cada thread
}

public static ExtentTest getTest() {
    return test.get();     // Recupera contexto da thread
}
```

### 10.2 Singleton para Relatório

```java
public class Relatorio {
    private static ExtentReports extent;  // Uma única instância
    
    private static void inicializar() {
        if (extent == null) {  // Lazy initialization
            extent = new ExtentReports();
        }
    }
}
```

### 10.3 Métodos Genéricos

```java
public static <T> void validados(String descricao, T valorEsperado, 
                                 T valorObtido, String tipoValidacao) {
    // Funciona com qualquer tipo T
}
```

## 11. Checklist de Qualidade

Antes de fazer commit:

- [ ] Teste possui nome descritivo (CT###_Descricao)
- [ ] Segue padrão GIVEN-WHEN-THEN
- [ ] Estende `Biblioteca`
- [ ] Usa `ConfigAPI` para URLs e endpoints
- [ ] Contém validações claras com `validados()` ou `validarStatusCode()`
- [ ] Logging presente em pontos-chave
- [ ] Sem hardcoding de valores
- [ ] Trata exceções adequadamente
- [ ] JavaDoc para métodos públicos
- [ ] Funciona isoladamente (sem dependências de outros testes)

## 12. Extensão do Projeto

### 12.1 Adicionar Novo Teste

1. Crie classe em `src/test/java/com/projeto/api/testes/CTxxxNomeTeste.java`
2. Estenda `Biblioteca`
3. Implemente método `@Test` com padrão GIVEN-WHEN-THEN
4. Use métodos de `Biblioteca` para requisições e validações

### 12.2 Adicionar Novo Endpoint

1. Em `ConfigAPI.java`, adicione constante
2. Use em `Biblioteca` para criar método auxiliar se necessário
3. Implemente teste correspondente

### 12.3 Adicionar Nova Validação

1. Implemente em `Biblioteca.java` como método estático
2. Chame `Relatorio.logPass()` ou `Relatorio.logFail()`
3. Use nos testes via `validados()`

## 📝 Referências

- [Rest Assured Documentation](http://rest-assured.io/)
- [JUnit 4](https://junit.org/junit4/)
- [Extent Reports](https://www.extentreports.com/)
- [Gherkin Syntax](https://cucumber.io/docs/gherkin/)
- [Log4j2](https://logging.apache.org/log4j/2.x/)

---

**Mantenha a qualidade, a clareza e a rastreabilidade em seus testes!** ✨