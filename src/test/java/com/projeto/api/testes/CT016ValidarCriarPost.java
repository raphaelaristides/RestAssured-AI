package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT016: Valida criação de post
 */
public class CT016ValidarCriarPost extends Biblioteca {

    @Test
    public void CT016_ValidarCriarPost() {
        Response response = fazerRequisicaoPostBody(criarCorpoPost("Post de Teste", "Corpo do post de teste", 1), ConfigAPI.ENDPOINT_POSTS_ADD);
        validarStatusCode(response, ConfigAPI.STATUS_CREATED, "Status deve ser 201");
        validados("Título do post criado", "Post de Teste", response.jsonPath().getString("title"), "equals");
    }
}
