package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT015: Valida paginação de posts
 */
public class CT015ValidarPaginacaoPosts extends Biblioteca {

    @Test
    public void CT015_ValidarPaginacaoPosts() {
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_POSTS + "?limit=5&skip=0");
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        validados("Limite de posts retornados", 5, response.jsonPath().getInt("limit"), "equals");
    }
}
