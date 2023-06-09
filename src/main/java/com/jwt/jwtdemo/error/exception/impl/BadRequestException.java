package com.jwt.jwtdemo.error.exception.impl;

import com.jwt.jwtdemo.error.exception.BaseException;
import com.jwt.jwtdemo.error.model.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public class BadRequestException extends BaseException {

    public BadRequestException(String code, String message){
        super(code, message);
    }

    public BadRequestException(String code, String message, List<ValidationError> errors){
        super(code, message, errors);
    }

    BadRequestException(String code, String message, Throwable cause){
        super(code, message, cause);
    }

    @Override
    public HttpStatusCode getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
