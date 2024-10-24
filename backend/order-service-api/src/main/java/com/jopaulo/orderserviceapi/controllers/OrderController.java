package com.jopaulo.orderserviceapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import models.exceptions.StandardError;
import models.requests.CreateOrderRequest;
import models.requests.CreateUserRequest;
import models.requests.UpdateOrdeRequest;
import models.response.OrderResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OrderController", description = "Controller responsável pelas operações do ordens de serviço")
@RequestMapping("/api/orders")
public interface OrderController {

    @Operation(summary = "Procurar Ordem de Serviço")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ordem de Serviço encontrada"),
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
    ResponseEntity<OrderResponse> findById(
            @NotNull(message = "O código da Ordem de Serviço deve ser informado")
            @Parameter(description = "Ordem Id", example = "1", required = true)
            @PathVariable(name = "id") final Long Id
    );

    @Operation(summary = "Salvar nova Ordem de Serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ordem de Serviço criada"),
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

    @Operation(summary = "Atualziar ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ordem de Serviço atulizada"),
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
    @PutMapping("/{id}")
    ResponseEntity<OrderResponse> update(
            @Parameter(description = "Ordem id", required = true, example = "1")
            @PathVariable(name = "id") Long id,
            @Parameter(description = "Atualizar Ordem de serviço")
            @Valid @RequestBody UpdateOrdeRequest request
    );
}
