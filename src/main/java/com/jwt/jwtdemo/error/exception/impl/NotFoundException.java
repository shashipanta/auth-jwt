package com.jwt.jwtdemo.error.exception.impl;

import com.jwt.jwtdemo.error.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class NotFoundException extends BaseException {

    NotFoundException(String code, String message){
        super(code, message);
    }

    NotFoundException(String code, String message, Throwable cause){
        super(code, message, cause);
    }

    @Override
    public HttpStatusCode getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
