package com.jopaulo.authserviceapi.security.dtos;

import com.jopaulo.authserviceapi.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import models.requests.AuthenticateRequest;
import models.response.AuthenticateResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticationsImpl {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticateResponse authenticate(final AuthenticateRequest request){
        try {
            log.info("Usuário informado: " + request.email());
            final var authResult = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
            return buildAuthenticateResponse((UserDetailsDTO) authResult.getPrincipal());
        } catch (BadCredentialsException ex) {
            log.error(ex.getMessage());
            log.error("Erro ao autenticar usuário: ", request.email());
            throw new BadCredentialsException("E-mail ou senha inválido");
        }
    }

    protected AuthenticateResponse buildAuthenticateResponse(final UserDetailsDTO detailsDTO){
        log.info("Usuário autenticado com sucesso: " + detailsDTO.getUsername());
        final var token = jwtUtils.generateToken(detailsDTO);
        return AuthenticateResponse.builder()
                .type("JWT")
                .token("Bearer " + token)
                .build();
    }
}
