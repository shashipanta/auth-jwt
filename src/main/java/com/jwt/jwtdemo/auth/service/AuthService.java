package com.jwt.jwtdemo.auth.service;


import com.jwt.jwtdemo.auth.dto.AuthRequest;
import com.jwt.jwtdemo.auth.dto.AuthResponse;
import com.jwt.jwtdemo.model.UserAccount;
import com.jwt.jwtdemo.repo.UserAccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserAccountRepo userAccountRepo;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public AuthResponse attemptLogin(AuthRequest authRequest){

        UserAccount userAccount = userAccountRepo.findByEmail(authRequest.getUserEmail()).orElseThrow();
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userAccount.getEmail());

        String token = jwtService.generateToken(userDetails);

        return AuthResponse
                .builder()
                .token(token)
                .build();
    }
}
