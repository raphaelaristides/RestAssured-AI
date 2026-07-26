package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT002: Valida credenciais do primeiro usuário
 * Cenário que verifica se os dados do usuário são retornados corretamente
 */
public class CT002ValidarCredenciais extends Biblioteca {

    @Test
    public void CT002_ValidarCredenciais() {
        // GIVEN: Sistema pronto para consultar usuários
        // WHEN: Executa GET no endpoint de usuários
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_USERS);

        // THEN: Valida o status e os dados do primeiro usuário
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status da requisição deve ser 200");
        validados("ID do primeiro usuário", 1, campoId(response, 0), "equals");
        validados("Nome do primeiro usuário", "Emily", campoFirstName(response, 0), "equals");
        validados("Password do primeiro usuário", "emilyspass", campoPassword(response, 0), "equals");
    }

}