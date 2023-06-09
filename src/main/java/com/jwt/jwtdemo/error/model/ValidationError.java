package com.jwt.jwtdemo.error.model;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ValidationError {

    private String field;
    private String message;
}
