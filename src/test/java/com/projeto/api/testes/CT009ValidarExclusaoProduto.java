package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT009: Valida exclusão de produto
 * Cenário que deleta um produto e valida a resposta
 */
public class CT009ValidarExclusaoProduto extends Biblioteca {

    @Test
    public void CT009_ValidarExclusaoProduto() {
        // GIVEN: ID de um produto para deletar
        String produtoID = "1";

        // WHEN: Executa DELETE no produto
        Response response = fazerRequisicaoDelete(ConfigAPI.ENDPOINT_PRODUCTS + "/" + produtoID);

        // THEN: Valida o status e que o produto foi deletado
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        
        // Valida se o ID retornado é o mesmo que foi deletado
        int idDeletado = response.jsonPath().getInt("id");
        validados("ID do produto deletado", 1, idDeletado, "equals");
        
        // Valida o campo isDeleted
        boolean isDeletado = response.jsonPath().getBoolean("isDeleted");
        validados("Produto marcado como deletado", true, isDeletado, "equals");
    }

}