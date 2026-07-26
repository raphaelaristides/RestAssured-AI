package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT006: Valida lista de produtos
 * Cenário que consulta a lista de todos os produtos disponíveis
 */
public class CT006ValidarListaProdutos extends Biblioteca {

    @Test
    public void CT006_ValidarListaProdutos() {
        // GIVEN: Sistema pronto para consultar produtos
        // WHEN: Executa GET no endpoint de produtos
        Response response = fazerRequisicaoGET(ConfigAPI.ENDPOINT_PRODUCTS);

        // THEN: Valida o status e a existência de produtos
        validarStatusCode(response, ConfigAPI.STATUS_SUCCESS, "Status deve ser 200");
        
        // Valida se contém dados de produtos
        int totalProdutos = response.jsonPath().getInt("total");
        validados("Total de produtos deve ser maior que zero", true, totalProdutos > 0, "equals");
        
        // Valida primeiro produto
        String primeiroTitulo = campoTitleProduto(response, 0);
        validados("Primeiro produto existe", true, primeiroTitulo != null && !primeiroTitulo.isEmpty(), "equals");
    }

}