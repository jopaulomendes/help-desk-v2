package com.jopaulo.orderserviceapi.controllers.impl;

import com.jopaulo.orderserviceapi.controllers.OrderController;
import com.jopaulo.orderserviceapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateOrderRequest;
import models.requests.UpdateOrdeRequest;
import models.response.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService service;

    @Override
    public ResponseEntity<Void> save(CreateOrderRequest request) {
        service.save(request);
        return ResponseEntity.status(CREATED).build();
    }

    @Override
    public ResponseEntity<OrderResponse> update(final Long id, UpdateOrdeRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }
}
