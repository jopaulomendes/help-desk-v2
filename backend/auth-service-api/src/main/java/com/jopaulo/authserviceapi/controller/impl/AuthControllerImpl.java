package com.jopaulo.authserviceapi.controller.impl;

import com.jopaulo.authserviceapi.controller.AuthController;
import com.jopaulo.authserviceapi.security.JwtAuthenticationsImpl;
import com.jopaulo.authserviceapi.services.RefreshTokenService;
import com.jopaulo.authserviceapi.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import models.requests.AuthenticateRequest;
import models.requests.RefreshTokenRequest;
import models.response.AuthenticateResponse;
import models.response.RefreshTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final JwtUtils jwtUtils;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<AuthenticateResponse> authenticate(final AuthenticateRequest request) throws Exception {
        return ResponseEntity.ok().body(
                new JwtAuthenticationsImpl(jwtUtils, authenticationConfiguration.getAuthenticationManager())
                        .authenticate(request)
                        .withRefreshToken(refreshTokenService.save(request.email()).getId())
        );
    }

    @Override
    public ResponseEntity<RefreshTokenResponse> refreshToken(RefreshTokenRequest request) {
        return ResponseEntity.ok().body(refreshTokenService.refreshToken(request.refreshToken())
        );
    }
}
