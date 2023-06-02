package com.jwt.jwtdemo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAccountMessage {
    private String message;
    private String messageCode;
}
