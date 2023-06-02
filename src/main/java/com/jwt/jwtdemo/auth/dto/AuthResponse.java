package com.jwt.jwtdemo.auth.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    private String token;
    private String issuedAt;
    private String expiresAt;
}
