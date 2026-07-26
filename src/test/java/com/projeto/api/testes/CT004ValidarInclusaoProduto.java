package com.projeto.api.testes;

import com.projeto.api.core.Biblioteca;
import com.projeto.api.core.ConfigAPI;
import io.restassured.response.Response;
import org.junit.Test;

/**
 * CT004: Valida inclusão de novo produto
 * Cenário que cria um novo produto e valida os dados retornados
 */
public class CT004ValidarInclusaoProduto extends Biblioteca {

    @Test
    public void CT004_ValidarInclusaoProduto() {
        // GIVEN: Dados de um novo produto preparados
        // WHEN: Executa POST para adicionar novo produto
        Response response = fazerRequisicaoPostBody(NOVO_PRODUTO_JSON, ConfigAPI.ENDPOINT_PRODUCTS_ADD);

        // THEN: Valida o status e os dados retornados
        validarStatusCode(response, ConfigAPI.STATUS_CREATED, "Status da inclusão de produto deve ser 201");
        
        // Captura os dados do produto criado
        String titulo = capturaDados(response, "title");
        String preco = capturaDados(response, "price");
        String categoria = capturaDados(response, "category");

        // Valida os dados
        validados("Título do produto", "Produto QA", titulo, "equals");
        validados("Preço do produto", "22", preco, "equals");
        validados("Categoria do produto", "comida", categoria, "equals");
    }

}