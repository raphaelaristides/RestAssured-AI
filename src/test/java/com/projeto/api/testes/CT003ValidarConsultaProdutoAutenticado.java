package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Map;

/**
 * CT003: Valida consulta de produtos com autenticação
 * Cenário que realiza login, captura token e consulta produtos autenticado
 */
public class CT003ValidarConsultaProdutoAutenticado extends Biblioteca {

    @BeforeClass
    public static void obterCredenciais() {
        // Captura credenciais do primeiro usuário
        Response dados = fazerRequisicaoGET(ConfigAPI.ENDPOINT_USERS);
        username = dados.jsonPath().getString("users[0].username");
        password = dados.jsonPath().getString("users[0].password");
    }

    @Test
    public void CT003_ValidarConsultaProdutoAutenticado() {
        // GIVEN: Credenciais válidas do usuário
        // WHEN: Realiza login para obter token
        Map<String, Object> credentials = criarCorpoLogin(username, password);
        Response loginResponse = fazerRequisicaoPostBody(credentials, ConfigAPI.ENDPOINT_AUTH_LOGIN);

        // THEN: Captura o token de acesso
        token = capturaDados(loginResponse, "accessToken");
        validados("Token capturado com sucesso", true, token != null && !token.isEmpty(), "equals");

        // WHEN: Consulta produtos com token de autenticação
        Response produtosResponse = fazerRequisicaoGETAuth(ConfigAPI.ENDPOINT_AUTH_PRODUCTS, token);

        // THEN: Valida se recebeu produtos
        validarStatusCode(produtosResponse, ConfigAPI.STATUS_SUCCESS, "Status da consulta de produtos autenticada");
        validados("Título do primeiro produto", "Essence Mascara Lash Princess", 
                  campoTitleProduto(produtosResponse, 0), "equals");
    }

}