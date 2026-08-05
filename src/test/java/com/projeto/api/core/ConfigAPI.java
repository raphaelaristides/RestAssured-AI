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
    public static final String ENDPOINT_POSTS_ADD = "/posts/add";
    public static final String ENDPOINT_POSTS_SEARCH = "/posts/search";
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
    public static final String RELATORIO_ARQUIVO = "target/relatorios/APITestRelatorio.html";

    // ========== STATUS CODES ESPERADOS ==========
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;
}
