package com.projeto.api.core;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.rules.TestName;
import org.junit.Rule;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe base que centraliza todos os métodos reutilizáveis para testes API
 * Implementa padrões de boas práticas para automação com Rest Assured
 * 
 * Todas as classes de teste devem estender esta classe
 */
public class Biblioteca {

    private static final Logger logger = LogManager.getLogger(Biblioteca.class);

    @Rule
    public TestName testName = new TestName();

    // ========== VARIÁVEIS DE SESSÃO ==========
    public static String username;
    public static String password;
    public static String token;

    // ========== DADOS PARA TESTES (MASSA) ==========
    public static final String NOVO_PRODUTO_JSON = """
{
  "title": "Produto QA",
  "description": "produto criado para QA",
  "price": 22,
  "discountPercentage": 0.4,
  "rating": 2.22,
  "stock": 22,
  "brand": "produto criado qa2",
  "category": "comida",
  "thumbnail": "www.produtoqa.com.br"
}
""";

    public static final String NOVO_POST_JSON = """
{
  "title": "Post de Teste",
  "body": "Este é um post de teste para validação",
  "userId": 1
}
""";

    /**
     * Bloco estático para inicializar RestAssured
     * Executado uma única vez quando a classe é carregada
     */
    static {
        RestAssured.baseURI = ConfigAPI.BASE_URL;
        logger.info(">>> RestAssured inicializado com baseURI: " + ConfigAPI.BASE_URL);
    }

    /**
     * Executado antes de cada teste
     * Cria registro no relatório
     */
    @Before
    public void iniciarTeste() {
        String nomeTeste = testName.getMethodName();
        System.out.println("\n" + "=".repeat(80));
        System.out.println(">>> Iniciando teste: " + nomeTeste);
        System.out.println("=".repeat(80));
        Relatorio.criarTeste(nomeTeste);
    }

    /**
     * Executado após cada teste
     */
    @After
    public void finalizarTeste() {
        logger.info(">>> Teste finalizado: " + testName.getMethodName());
    }

    // ========== MÉTODOS DE REQUISIÇÃO HTTP ==========

    /**
     * Realiza uma requisição GET simples
     * 
     * @param endpointPath Caminho do endpoint (ex: /users)
     * @return Response da requisição
     */
    public static Response fazerRequisicaoGET(String endpointPath) {
        try {
            Relatorio.logInfo("GET → " + endpointPath);
            
            Response response = RestAssured
                    .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(endpointPath)
                    .then()
                    .log().body()
                    .extract().response();

            Relatorio.logInfo("Status: " + response.getStatusCode());
            logger.info(">>> Resposta GET: " + response.asString());
            
            return response;
        } catch (Exception e) {
            logger.error(">>> Erro ao executar GET: " + e.getMessage(), e);
            Relatorio.logFail("Erro ao executar GET: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Realiza uma requisição GET com autenticação via token Bearer
     * 
     * @param endpointPath Caminho do endpoint
     * @param token1 Token de autenticação
     * @return Response da requisição
     */
    public static Response fazerRequisicaoGETAuth(String endpointPath, String token1) {
        try {
            Relatorio.logInfo("GET com Autenticação → " + endpointPath);
            
            Response response = RestAssured
                    .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .header(ConfigAPI.AUTHORIZATION_HEADER, "Bearer " + token1)
                    .when()
                    .get(endpointPath)
                    .then()
                    .log().body()
                    .extract().response();

            Relatorio.logInfo("Status: " + response.getStatusCode());
            logger.info(">>> Resposta GET Auth: " + response.asString());
            
            return response;
        } catch (Exception e) {
            logger.error(">>> Erro ao executar GET com Auth: " + e.getMessage(), e);
            Relatorio.logFail("Erro ao executar GET com Auth: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Realiza uma requisição POST com corpo
     * 
     * @param body Corpo da requisição
     * @param endpointPath Caminho do endpoint
     * @return Response da requisição
     */
    public static Response fazerRequisicaoPostBody(Object body, String endpointPath) {
        try {
            Relatorio.logInfo("POST com Body → " + endpointPath);
            logger.debug(">>> Body: " + body);

            Response response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .log().all()
                    .when()
                    .post(endpointPath)
                    .then()
                    .log().body()
                    .extract().response();

            Relatorio.logInfo("Status: " + response.getStatusCode());
            Relatorio.logInfo("Resposta: " + response.asString());
            
            return response;
        } catch (Exception e) {
            logger.error(">>> Erro ao executar POST: " + e.getMessage(), e);
            Relatorio.logFail("Erro ao executar POST: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Realiza uma requisição PUT com corpo
     * 
     * @param body Corpo da requisição
     * @param endpointPath Caminho do endpoint
     * @return Response da requisição
     */
    public static Response fazerRequisicaoPutBody(Object body, String endpointPath) {
        try {
            Relatorio.logInfo("PUT com Body → " + endpointPath);
            
            Response response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(body)
                    .log().all()
                    .when()
                    .put(endpointPath)
                    .then()
                    .log().body()
                    .extract().response();

            Relatorio.logInfo("Status: " + response.getStatusCode());
            logger.info(">>> Resposta PUT: " + response.asString());
            
            return response;
        } catch (Exception e) {
            logger.error(">>> Erro ao executar PUT: " + e.getMessage(), e);
            Relatorio.logFail("Erro ao executar PUT: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Realiza uma requisição DELETE
     * 
     * @param endpointPath Caminho do endpoint
     * @return Response da requisição
     */
    public static Response fazerRequisicaoDelete(String endpointPath) {
        try {
            Relatorio.logInfo("DELETE → " + endpointPath);
            
            Response response = RestAssured
                    .given()
                    .log().all()
                    .when()
                    .delete(endpointPath)
                    .then()
                    .log().body()
                    .extract().response();

            Relatorio.logInfo("Status: " + response.getStatusCode());
            logger.info(">>> Resposta DELETE: " + response.asString());
            
            return response;
        } catch (Exception e) {
            logger.error(">>> Erro ao executar DELETE: " + e.getMessage(), e);
            Relatorio.logFail("Erro ao executar DELETE: " + e.getMessage());
            throw e;
        }
    }

    // ========== MÉTODOS DE CAPTURA E VALIDAÇÃO ==========

    /**
     * Captura um valor específico da resposta JSON
     * 
     * @param response Response da requisição
     * @param campo Caminho do campo a capturar (ex: "users[0].username")
     * @return Valor capturado como String
     */
    public static String capturaDados(Response response, String campo) {
        try {
            JsonPath jp = response.jsonPath();
            String valor = jp.getString(campo);
            logger.info(">>> Captura de dados - Campo: " + campo + " | Valor: " + valor);
            return valor;
        } catch (Exception e) {
            logger.error(">>> Erro ao capturar dados do campo: " + campo, e);
            Relatorio.logWarning("Erro ao capturar dados: " + e.getMessage());
            return null;
        }
    }

    /**
     * Captura um valor inteiro da resposta JSON
     * 
     * @param response Response da requisição
     * @param campo Caminho do campo
     * @return Valor capturado como Integer
     */
    public static Integer capturaInteiro(Response response, String campo) {
        try {
            JsonPath jp = response.jsonPath();
            Integer valor = jp.getInt(campo);
            logger.info(">>> Captura de inteiro - Campo: " + campo + " | Valor: " + valor);
            return valor;
        } catch (Exception e) {
            logger.error(">>> Erro ao capturar inteiro do campo: " + campo, e);
            return null;
        }
    }

    /**
     * Valida se um valor esperado é igual ao obtido
     * 
     * @param descricao Descrição da validação
     * @param valorEsperado Valor esperado
     * @param valorObtido Valor obtido
     * @param tipoValidacao Tipo de validação ("equals" ou "contains")
     */
    public static <T> void validados(String descricao, T valorEsperado, T valorObtido, String tipoValidacao) {
        try {
            boolean validado = false;

            if ("equals".equalsIgnoreCase(tipoValidacao)) {
                validado = valorEsperado.equals(valorObtido);
            } else if ("contains".equalsIgnoreCase(tipoValidacao) &&
                    valorEsperado instanceof String &&
                    valorObtido instanceof String) {
                validado = ((String) valorObtido).contains((String) valorEsperado);
            }

            if (validado) {
                Relatorio.logPass(descricao + " → OK! (" + valorObtido + ")");
                logger.info(">>> Validação OK: " + descricao);
            } else {
                Relatorio.logFail(descricao + " → FALHOU! Esperado: " + valorEsperado + " | Obtido: " + valorObtido);
                logger.error(">>> Validação FALHOU: " + descricao);
            }
        } catch (Exception e) {
            logger.error(">>> Erro ao validar: " + e.getMessage(), e);
            Relatorio.logFail("Erro ao validar: " + e.getMessage());
        }
    }

    /**
     * Valida o status code da resposta
     * 
     * @param response Response da requisição
     * @param statusEsperado Status esperado
     * @param descricao Descrição da validação
     */
    public static void validarStatusCode(Response response, int statusEsperado, String descricao) {
        int statusObtido = response.getStatusCode();
        validados(descricao, statusEsperado, statusObtido, "equals");
    }

    /**
     * Valida um campo específico da resposta JSON
     * 
     * @param response Response da requisição
     * @param chave Chave do campo
     * @param valorEsperado Valor esperado
     */
    public static void validarResposta(Response response, String chave, String valorEsperado) {
        try {
            String valorObtido = response.jsonPath().getString(chave);
            validados("Campo '" + chave + "'", valorEsperado, valorObtido, "equals");
        } catch (Exception e) {
            logger.error(">>> Erro ao validar resposta: " + e.getMessage(), e);
            Relatorio.logFail("Erro ao validar campo: " + chave);
        }
    }

    // ========== MÉTODOS AUXILIARES PARA CAMPOS ==========

    /**
     * Extrai o ID de um usuário em determinado índice
     * 
     * @param resp Response
     * @param index Índice do usuário
     * @return ID do usuário
     */
    public static int campoId(Response resp, int index) {
        return resp.jsonPath().getInt("users[" + index + "].id");
    }

    /**
     * Extrai o primeiro nome de um usuário em determinado índice
     * 
     * @param resp Response
     * @param index Índice do usuário
     * @return Primeiro nome
     */
    public static String campoFirstName(Response resp, int index) {
        return resp.jsonPath().getString("users[" + index + "].firstName");
    }

    /**
     * Extrai a senha de um usuário em determinado índice
     * 
     * @param resp Response
     * @param index Índice do usuário
     * @return Senha
     */
    public static String campoPassword(Response resp, int index) {
        return resp.jsonPath().getString("users[" + index + "].password");
    }

    /**
     * Extrai o username de um usuário em determinado índice
     * 
     * @param resp Response
     * @param index Índice do usuário
     * @return Username
     */
    public static String campoUsuario(Response resp, int index) {
        return resp.jsonPath().getString("users[" + index + "].username");
    }

    /**
     * Extrai o título de um produto em determinado índice
     * 
     * @param resp Response
     * @param index Índice do produto
     * @return Título do produto
     */
    public static String campoTitleProduto(Response resp, int index) {
        try {
            return resp.jsonPath().getString("products[" + index + "].title");
        } catch (Exception e) {
            logger.error(">>> Erro ao capturar título do produto no índice " + index, e);
            return null;
        }
    }

    /**
     * Extrai o preço de um produto em determinado índice
     * 
     * @param resp Response
     * @param index Índice do produto
     * @return Preço do produto
     */
    public static Double campoPrecoProduto(Response resp, int index) {
        try {
            return resp.jsonPath().getDouble("products[" + index + "].price");
        } catch (Exception e) {
            logger.error(">>> Erro ao capturar preço do produto no índice " + index, e);
            return null;
        }
    }

    // ========== MÉTODOS UTILITÁRIOS ==========

    /**
     * Cria um mapa com credenciais para login
     * 
     * @param user Username
     * @param pass Password
     * @return Map com credenciais
     */
    public static Map<String, Object> criarCorpoLogin(String user, String pass) {
        Map<String, Object> requestBody = new HashMap<>();
        if (user != null && !user.isEmpty()) {
            requestBody.put("username", user);
        }
        if (pass != null && !pass.isEmpty()) {
            requestBody.put("password", pass);
        }
        logger.debug(">>> Corpo de login criado: " + requestBody);
        return requestBody;
    }

    /**
     * Cria um mapa com dados de um produto
     * 
     * @param title Título do produto
     * @param price Preço
     * @param description Descrição
     * @return Map com dados do produto
     */
    public static Map<String, Object> criarCorpoProduto(String title, double price, String description) {
        Map<String, Object> produto = new HashMap<>();
        produto.put("title", title);
        produto.put("price", price);
        produto.put("description", description);
        produto.put("category", "comida");
        logger.debug(">>> Corpo de produto criado: " + produto);
        return produto;
    }

    /**
     * Aguarda um tempo em milissegundos
     * 
     * @param millisegundos Tempo em ms
     */
    public static void aguardar(long millisegundos) {
        try {
            Thread.sleep(millisegundos);
            logger.debug(">>> Aguardado: " + millisegundos + "ms");
        } catch (InterruptedException e) {
            logger.error(">>> Erro ao aguardar: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

}
