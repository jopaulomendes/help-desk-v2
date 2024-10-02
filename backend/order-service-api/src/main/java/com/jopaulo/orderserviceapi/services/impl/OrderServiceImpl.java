package com.jopaulo.orderserviceapi.services.impl;

import com.jopaulo.orderserviceapi.mapper.OrderMapper;
import com.jopaulo.orderserviceapi.repositories.OrderRepository;
import com.jopaulo.orderserviceapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import models.requests.CreateOrderRequest;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    @Override
    public void save(CreateOrderRequest request) {
        final var entity = repository.save(mapper.fromRequest(request));
        log.info("Ordem criada: {}", entity);
    }
}
