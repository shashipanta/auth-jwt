package com.jwt.jwtdemo.error.codes;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorCodes {
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String ACCESS_DENIED = "ACCESS_DENIED";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String TOKEN_NOT_VALID = "TOKEN_NOT_VALID";
    public static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";

}
