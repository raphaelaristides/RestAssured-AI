package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT007: Valida consulta de produto por ID
 * Cenário que consulta um produto específico pelo ID
 */
public class CT007ValidarProdutoPorID extends Biblioteca {

    @Test
    public void CT007_ValidarProdutoPorID() {
        // GIVEN: ID de um produto válido (1)
        String produtoID = "1";

        // WHEN: Consulta o produto pelo ID
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_PRODUCTS + "/" + produtoID);

        // THEN: Valida o status e os dados do produto
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        
        // Valida ID do produto retornado
        int idRetornado = response.jsonPath().getInt("id");
        validados("ID do produto", 1, idRetornado, "equals");
        
        // Valida se contém os campos essenciais
        String titulo = capturaDados(response, "title");
        String categoria = capturaDados(response, "category");
        
        validados("Título do produto existe", true, titulo != null && !titulo.isEmpty(), "equals");
        validados("Categoria do produto existe", true, categoria != null && !categoria.isEmpty(), "equals");
    }

}