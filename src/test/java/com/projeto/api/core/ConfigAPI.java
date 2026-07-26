package com.projeto.api.core;

/**
 * Classe de configuração centralizada para URLs e endpoints da API
 * Facilita manutenção e reutilização de configurações
 */
public class ConfigAPI {

    // ========== CONFIGURAÇÕES DE BASE ==========
    public static final String BASE_URL = "https://dummyjson.com";
    public static final String REQUEST_TIMEOUT = "5000";

    // ========== ENDPOINTS ==========
    public static final String ENDPOINT_TEST = "/test";
    public static final String ENDPOINT_USERS = "/users";
    public static final String ENDPOINT_AUTH_LOGIN = "/auth/login";
    public static final String ENDPOINT_AUTH_PRODUCTS = "/auth/products";
    public static final String ENDPOINT_PRODUCTS_ADD = "/products/add";
    public static final String ENDPOINT_PRODUCTS = "/products";
    public static final String ENDPOINT_POSTS = "/posts";
    public static final String ENDPOINT_COMMENTS = "/comments";

    // ========== HEADERS PADRÃO ==========
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // ========== STATUS CODES ESPERADOS ==========
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;

}