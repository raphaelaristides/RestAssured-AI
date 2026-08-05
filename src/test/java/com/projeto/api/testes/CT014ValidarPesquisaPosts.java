package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT014: Valida pesquisa de posts
 */
public class CT014ValidarPesquisaPosts extends Biblioteca {

    @Test
    public void CT014_ValidarPesquisaPosts() {
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_POSTS_SEARCH + "?q=qui");
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        validados("Resultado de pesquisa possui post", true, response.jsonPath().getList("posts").size() > 0, "equals");
    }
}
