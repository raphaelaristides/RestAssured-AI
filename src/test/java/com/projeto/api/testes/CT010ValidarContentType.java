package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT010: Valida Content-Type das respostas
 * Cenário que verifica se as respostas possuem Content-Type correto
 */
public class CT010ValidarContentType extends Biblioteca {

    @Test
    public void CT010_ValidarContentType() {
        // GIVEN: Sistema pronto para fazer requisição
        // WHEN: Executa GET em um endpoint
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_USERS);

        // THEN: Valida o status
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        
        // THEN: Valida o Content-Type da resposta
        String contentType = response.getHeader("Content-Type");
        validados("Content-Type contém application/json", true, 
                  contentType != null && contentType.contains("application/json"), "equals");
        
        // Valida se há dados na resposta
        int usuariosCount = response.jsonPath().getInt("users.size()");
        validados("Resposta contém usuários", true, usuariosCount > 0, "equals");
    }

}