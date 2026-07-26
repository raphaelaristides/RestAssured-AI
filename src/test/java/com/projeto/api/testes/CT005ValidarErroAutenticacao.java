package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

/**
 * CT005: Valida erro de autenticação com token inválido
 * Cenário que tenta acessar recurso protegido com token inválido
 */
public class CT005ValidarErroAutenticacao extends Biblioteca {

    @BeforeClass
    public static void obterCredenciais() {
        Response dados = fazerRequisicaoGET(ConfigAPI.ENDPOINT_USERS);
        username = dados.jsonPath().getString("users[0].username");
        password = dados.jsonPath().getString("users[0].password");
    }

    @Test
    public void CT005_ValidarErroAutenticacao() {
        // GIVEN: Credenciais válidas para login
        // WHEN: Realiza login para obter token
        Map<String, Object> credentials = criarCorpoLogin(username, password);
        Response loginResponse = fazerRequisicaoPostBody(credentials, ConfigAPI.ENDPOINT_AUTH_LOGIN);

        // WHEN: Tenta usar campo inválido como token (usando "gender" em vez de "accessToken")
        String tokenInvalido = capturaDados(loginResponse, "gender");

        // THEN: Tenta acessar recurso protegido com token inválido
        Response response = fazerRequisicaoGETAuth(ConfigAPI.ENDPOINT_AUTH_PRODUCTS, tokenInvalido);

        // THEN: Valida se recebeu erro 401 (Unauthorized)
        validarStatusCode(response, ConfigAPI.STATUS_UNAUTHORIZED, "Status deve ser 401 - Unauthorized");
        validarResposta(response, "message", "Invalid/Expired Token!");
    }

}