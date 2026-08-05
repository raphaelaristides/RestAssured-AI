package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * CT018: Valida atualização parcial de post com PATCH
 */
public class CT018ValidarAtualizacaoPostPATCH extends Biblioteca {

    @Test
    public void CT018_ValidarAtualizacaoPostPATCH() {
        Map<String, Object> patchBody = new HashMap<>();
        patchBody.put("title", "Título PATCH");

        Response response = fazerRequisicaoPatchBody(patchBody, ConfigAPI.ENDPOINT_POSTS + "/1");
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        validados("Título do post depois de PATCH", "Título PATCH", response.jsonPath().getString("title"), "equals");
    }
}
