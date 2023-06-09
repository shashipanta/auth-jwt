package com.jwt.jwtdemo.error.exception.impl;

import com.jwt.jwtdemo.error.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class InternalServerException extends BaseException {
    
    public InternalServerException(String code, String message){
        super(code, message);
    }

    public InternalServerException(String code, String message, Throwable cause){
        super(code, message, cause);
    }

    @Override
    public HttpStatusCode getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
