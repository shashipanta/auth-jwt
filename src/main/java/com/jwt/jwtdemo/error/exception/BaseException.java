package com.jwt.jwtdemo.error.exception;


import com.jwt.jwtdemo.dto.response.ErrorResponse;
import com.jwt.jwtdemo.error.model.ValidationError;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;
import java.util.List;

public abstract class BaseException extends RuntimeException{

    private final ErrorResponse errorResponse;

    public BaseException(String code, String message){
        super(message);

        errorResponse = ErrorResponse.builder()
                .code(code)
                .message(message)
                .time(Instant.now().toString())
                .statusCode(String.valueOf(getHttpStatus()))
                .build();
    }

    public BaseException(String code, String message, List<ValidationError> errors){
        super(message);

        errorResponse = ErrorResponse.builder()
                .code(code)
                .message(message)
                .statusCode(String.valueOf(getHttpStatus()))
                .time(Instant.now().toString())
                .errors(errors)
                .build();
    }

    public BaseException(String code, String message, Throwable cause){
        super(message);

        errorResponse = ErrorResponse.builder()
                .code(code)
                .message(message)
                .statusCode(String.valueOf(getHttpStatus()))
                .time(Instant.now().toString())
                .build();
    }

    public abstract HttpStatusCode getHttpStatus();

    public void setPath(String path){
        errorResponse.setPath(path);
    }

    public ErrorResponse getErrorResponse(){
        return this.errorResponse;
    }
}
