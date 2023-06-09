package com.jwt.jwtdemo.error.exception.impl;

import com.jwt.jwtdemo.error.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class TokenExpiredException extends BaseException {

    public TokenExpiredException(String code, String message) {
        super(code, message);
    }

    @Override
    public HttpStatusCode getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
