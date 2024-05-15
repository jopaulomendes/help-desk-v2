package com.jopaulo.authserviceapi.controller.impl;

import com.jopaulo.authserviceapi.controller.AuthController;
import com.jopaulo.authserviceapi.security.JwtAuthenticationsImpl;
import com.jopaulo.authserviceapi.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import models.requests.AuthenticateRequest;
import models.response.AuthenticateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final JwtUtils jwtUtils;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Override
    public ResponseEntity<AuthenticateResponse> authenticate(final AuthenticateRequest request) throws Exception {
        return ResponseEntity.ok().body(
                new JwtAuthenticationsImpl(jwtUtils, authenticationConfiguration.getAuthenticationManager())
                        .authenticate(request)
        );
    }
}
