package com.jwt.jwtdemo.auth.service;

import com.jwt.jwtdemo.error.codes.ErrorCodes;
import com.jwt.jwtdemo.error.exception.impl.TokenExpiredException;
import com.jwt.jwtdemo.property.JwtProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //    private static final String SECRET_KEY = "423F4528482B4D6251655368566D597133743677397A24432646294A404E635266556A576E5A7234753778214125442A472D4B6150645367566B597032733576";
    private static String SECRET_KEY = null;

    private final JwtProperty jwtProperty;
    private LocalDateTime issuedDate;

    public JwtService(JwtProperty jwtProperty) {
        this.jwtProperty = jwtProperty;
        SECRET_KEY = jwtProperty.getSecretKey();
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        Integer tokenExpiryInMinutes = jwtProperty.getTokenExpirationInMinutes();
        LocalDateTime issuedAt = LocalDateTime.now();
        LocalDateTime expiresAt = issuedAt.plus(tokenExpiryInMinutes, ChronoUnit.MINUTES);
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuedAt(Date.from(issuedAt.atOffset(ZoneOffset.UTC).toInstant()))
                .setExpiration(Date.from(expiresAt.atOffset(ZoneOffset.UTC).toInstant()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Claims extractAllClaims(String token) {
        try {
            Claims claims =  Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException expiredJwtException){
            throw new TokenExpiredException(ErrorCodes.TOKEN_EXPIRED, "Token is expired");
        }

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

//        boolean tokenExpired = isTokenExpired(token);

//        boolean isUsernameValid = username.equals(userDetails.getUsername());
//        return tokenExpired &&
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        getExpirationDate(token);
        return extractClaim(token, Claims::getExpiration).before(new Date(System.currentTimeMillis()));
    }

    public Date getExpirationDate(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date;
    }

    // extract specific claim
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims allClaims = extractAllClaims(token);

        return claimsResolver.apply(allClaims);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
