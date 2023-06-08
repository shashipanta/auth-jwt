package com.jwt.jwtdemo.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRequest{

    @NotBlank(message = "Email cannot be blank")
    private String userEmail;
    @NotBlank(message = "Password cannot be empty")
    private String userPassword;

}
