package com.jopaulo.orderserviceapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import models.exceptions.StandardError;
import models.requests.CreateOrderRequest;
import models.requests.CreateUserRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "OrderController", description = "Controller responsável pelas operações do ordens de serviço")
@RequestMapping("/api/orders")
public interface OrderController {

    @Operation(summary = "Salvar nova ordem ser serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ordem criada"),
            @ApiResponse(responseCode = "400", description = "O servidor não pode ou não processará a solicitação devido a algo que é percebido como um erro do cliente",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Servidor não consegue encontrar o recurso solicitado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)
                    ))
    })
    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateOrderRequest request);
}
