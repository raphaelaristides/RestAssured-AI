package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT001: Valida se a requisição GET retorna status 200
 * Cenário simples para validar conectividade básica com a API
 */
public class CT001ValidarStatus200 extends Biblioteca {

    @Test
    public void CT001_ValidarStatus200() {
        // GIVEN: Sistema pronto para realizar requisição
        // WHEN: Executa GET no endpoint de teste
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_TEST);

        // THEN: Valida se o status retornado é 200
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Validar Status 200 OK");
    }

}