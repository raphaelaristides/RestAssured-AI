package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT011: Valida listagem de posts
 */
public class CT011ValidarListaPosts extends Biblioteca {

    @Test
    public void CT011_ValidarListaPosts() {
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_POSTS);
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        validados("Retorno de lista de posts", true, response.jsonPath().getList("posts").size() > 0, "equals");
    }
}
