package com.jwt.jwtdemo.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt-demo.security.jwt")
public class JwtProperty {

    private String secretKey;
    private Integer tokenExpirationInMinutes;

}
