package com.jwt.jwtdemo.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ErrorResponse {

    private String code;
    private String message;
    private String statusCode;
    private String time;
    private final Object errors;

    @Setter
    private String path;
}
