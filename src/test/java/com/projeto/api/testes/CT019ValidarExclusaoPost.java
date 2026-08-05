package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT019: Valida exclusão de post
 */
public class CT019ValidarExclusaoPost extends Biblioteca {

    @Test
    public void CT019_ValidarExclusaoPost() {
        Response response = fazerRequisicaoDelete(ConfigAPI.ENDPOINT_POSTS + "/1");
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
    }
}
