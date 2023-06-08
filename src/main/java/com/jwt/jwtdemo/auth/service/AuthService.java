package com.jwt.jwtdemo.auth.service;


import com.jwt.jwtdemo.auth.dto.AuthRequest;
import com.jwt.jwtdemo.auth.dto.AuthResponse;
import com.jwt.jwtdemo.model.UserAccount;
import com.jwt.jwtdemo.repo.UserAccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserAccountRepo userAccountRepo;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse attemptLogin(AuthRequest authRequest){

        UserAccount userAccount = userAccountRepo.findByEmail(authRequest.getUserEmail()).orElseThrow();
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userAccount.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUserEmail(), authRequest.getUserPassword());
        additionalAuthenticationChecks(userDetails,authenticationToken );

        String token = jwtService.generateToken(userDetails);

        return AuthResponse
                .builder()
                .token(token)
                .build();
    }

    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        // checks
        if(authentication.getCredentials() == null || userDetails.getPassword() == null){
            throw new BadCredentialsException("Credentials cannot be null");
        }

        String credentials = (String) authentication.getCredentials();

        String password = userDetails.getPassword();

        if(!passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword())){
            throw new BadCredentialsException("Credentials not matched");
        }

    }
}
