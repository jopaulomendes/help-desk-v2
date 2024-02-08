package com.jopaulo.authserviceapi.controller.impl;

import com.jopaulo.authserviceapi.controller.AuthController;
import models.requests.AuthenticateRequest;
import models.response.AuthenticateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    @Override
    public ResponseEntity<AuthenticateResponse> authenticate(AuthenticateRequest request) {
        return ResponseEntity.ok(AuthenticateResponse.builder()
                        .type("Bearer")
                        .token("token")
                .build());
    }
}
