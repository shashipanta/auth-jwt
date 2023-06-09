package com.jwt.jwtdemo.auth.filter;

import com.jwt.jwtdemo.auth.service.JwtService;
import com.jwt.jwtdemo.error.codes.ErrorCodes;
import com.jwt.jwtdemo.error.exception.impl.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String AUTH_HEADER = "Authorization";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTH_HEADER);

        // if no bearer token then skip this
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String passedToken = authHeader.substring(7);

            // first check token expiration then proceed
//            if (jwtService.isTokenExpired(passedToken))
//                throw new TokenExpiredException(ErrorCodes.TOKEN_EXPIRED, "Token expire bhayo daju");

            String userEmail = jwtService.extractUsername(passedToken);


            // what if user is already authenticated
            Boolean isUserAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null;

            if (!isUserAuthenticated && userEmail != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                // validate token
                if (jwtService.isTokenValid(passedToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetails(request)
                    );

                    // update security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        }
        filterChain.doFilter(request, response);
    }
}
