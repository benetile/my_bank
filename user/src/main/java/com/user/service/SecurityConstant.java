package com.user.service;

public class SecurityConstant {

    public static  final long EXPIRATION_TIME = 7200000;// 2 hours expressed in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token Cannot be verified";
    public static final String AUTHORITIES = "authorities";
    public static final String GET_SOCIETY = "my Bank";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD ="OPTIONS";
    public static final String [] PUBLIC_URLS ={ "/login", "/register","/reset-password"};

}
