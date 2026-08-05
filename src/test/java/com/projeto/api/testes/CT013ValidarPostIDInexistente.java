package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT013: Valida consulta de post por ID inexistente
 */
public class CT013ValidarPostIDInexistente extends Biblioteca {

    @Test
    public void CT013_ValidarPostIDInexistente() {
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_POSTS + "/9999");
        validarStatusCode(response, ConfigAPI.STATUS_NOT_FOUND, "Status deve ser 404");
    }
}
