package com.jwt.jwtdemo.auth.controller;

import com.jwt.jwtdemo.auth.dto.AuthRequest;
import com.jwt.jwtdemo.auth.dto.AuthResponse;
import com.jwt.jwtdemo.auth.service.AuthService;
import com.jwt.jwtdemo.dto.request.UserAccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserAccountRequest userAccountRequest) {
        return null;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<AuthResponse> attemptLogin(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.attemptLogin(authRequest));
    }
}
