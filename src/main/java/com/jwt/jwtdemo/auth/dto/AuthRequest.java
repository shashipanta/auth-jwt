package com.jwt.jwtdemo.auth.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRequest{

    private String userEmail;
    private String userPassword;

}
