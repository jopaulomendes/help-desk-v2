package com.jopaulo.authserviceapi.services;

import com.jopaulo.authserviceapi.models.RefreshToken;
import com.jopaulo.authserviceapi.repositories.RefreshTokenRepository;
import com.jopaulo.authserviceapi.security.dtos.UserDetailsDTO;
import com.jopaulo.authserviceapi.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import models.exceptions.RefreshTokenExpired;
import models.exceptions.ResourceNotFoundException;
import models.response.RefreshTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.expiration-sec.refresh-token}")
    private Long refreshToken;

    private final RefreshTokenRepository repository;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public RefreshToken save(final String username){
        return repository.save(
                RefreshToken.builder()
                        .id(UUID.randomUUID().toString())
                        .createdAt(now())
                        .expireAt(now().plusSeconds(refreshToken))
                        .username(username)
                        .build()
        );
    }

    public RefreshTokenResponse refreshToken(final String refreshTokenId){
        final var refreshToken = repository.findById(refreshTokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token não encontrado. Id: " + refreshTokenId));

        if(refreshToken.getExpireAt().isBefore(now())){
            throw new RefreshTokenExpired("Refresh token expired. Id: " + refreshTokenId);
        }

        return new RefreshTokenResponse(
                jwtUtils.generateToken((UserDetailsDTO) userDetailsService.loadUserByUsername(refreshToken.getUsername()))
        );
    }
}
