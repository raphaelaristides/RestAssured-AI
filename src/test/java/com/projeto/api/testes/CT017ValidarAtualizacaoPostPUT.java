package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT017: Valida atualização de post com PUT
 */
public class CT017ValidarAtualizacaoPostPUT extends Biblioteca {

    @Test
    public void CT017_ValidarAtualizacaoPostPUT() {
        Response response = fazerRequisicaoPutBody(criarCorpoPost("Post Atualizado", "Corpo atualizado do post", 1), ConfigAPI.ENDPOINT_POSTS + "/1");
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        validados("Título do post atualizado", "Post Atualizado", response.jsonPath().getString("title"), "equals");
    }
}
