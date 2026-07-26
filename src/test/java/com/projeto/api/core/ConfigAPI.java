package com.projeto.api.core;

/**
 * Classe de configuração centralizada para a API
 * Define URLs, headers e constantes globais
 */
public class ConfigAPI {

    // ========== URL BASE ==========
    public static final String BASE_URL = "https://dummyjson.com";

    // ========== HEADERS ==========
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String CONTENT_TYPE_JSON = "application/json";

    // ========== ENDPOINTS ==========
    public static final String ENDPOINT_USERS = "/users";
    public static final String ENDPOINT_PRODUCTS = "/products";
    public static final String ENDPOINT_POSTS = "/posts";
    public static final String ENDPOINT_AUTH_LOGIN = "/auth/login";
    public static final String ENDPOINT_AUTH_PRODUCTS = "/auth/products";
    public static final String ENDPOINT_PRODUCTS_ADD = "/products/add";
    public static final String ENDPOINT_TEST = "/test";

    // ========== TIMEOUTS (em segundos) ==========
    public static final int CONNECTION_TIMEOUT = 10;
    public static final int READ_TIMEOUT = 30;

    // ========== TENTATIVAS DE RETRY ==========
    public static final int MAX_RETRIES = 3;
    public static final long RETRY_WAIT_MS = 1000; // 1 segundo entre tentativas

    // ========== RELATÓRIO ==========
    public static final String RELATORIO_PASTA = "target/relatorios";
    public static final String RELATORIO_ARQUIVO = "target/relatorios/Relatorio.html";

}
