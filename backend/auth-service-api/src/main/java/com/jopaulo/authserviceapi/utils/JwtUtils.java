package com.jopaulo.authserviceapi.utils;

import com.jopaulo.authserviceapi.security.dtos.UserDetailsDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expitarion;

    public String generateToken(final UserDetailsDTO detailsDTO){
        return Jwts.builder()
                .claim("id" ,detailsDTO.getId())
                .claim("name" ,detailsDTO.getName())
                .claim("authorities" ,detailsDTO.getAuthorities())
                .setSubject(detailsDTO.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + expitarion))
                .compact();
    }
}
