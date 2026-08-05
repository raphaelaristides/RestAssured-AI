package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT012: Valida consulta de post por ID
 */
public class CT012ValidarPostPorID extends Biblioteca {

    @Test
    public void CT012_ValidarPostPorID() {
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_POSTS + "/1");
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        validados("Post ID retornado", "1", response.jsonPath().getString("id"), "equals");
    }
}
