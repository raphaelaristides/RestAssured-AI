package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

/**
 * CT008: Valida atualização de produto
 * Cenário que atualiza dados de um produto existente
 */
public class CT008ValidarAtualizacaoProduto extends Biblioteca {

    @Test
    public void CT008_ValidarAtualizacaoProduto() {
        // GIVEN: Dados atualizados de um produto
        Map<String, Object> produtoAtualizado = criarCorpoProduto("Produto Atualizado", 99.99, "Produto atualizado para teste");

        // WHEN: Executa PUT para atualizar o produto ID 1
        Response response = fazerRequisicaoPutBody(produtoAtualizado, ConfigAPI.ENDPOINT_PRODUCTS + "/1");

        // THEN: Valida o status e os dados atualizados
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        
        String tituloAtualizado = capturaDados(response, "title");
        Double precoAtualizado = response.jsonPath().getDouble("price");
        
        validados("Título atualizado", "Produto Atualizado", tituloAtualizado, "equals");
        validados("Preço atualizado", 99.99, precoAtualizado, "equals");
    }

}