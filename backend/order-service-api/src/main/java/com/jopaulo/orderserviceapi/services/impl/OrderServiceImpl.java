package com.jopaulo.orderserviceapi.services.impl;

import com.jopaulo.orderserviceapi.entities.Order;
import com.jopaulo.orderserviceapi.mapper.OrderMapper;
import com.jopaulo.orderserviceapi.repositories.OrderRepository;
import com.jopaulo.orderserviceapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import models.enums.OrderStatusEnum;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateOrderRequest;
import models.requests.UpdateOrdeRequest;
import models.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static models.enums.OrderStatusEnum.CLOSED;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    @Override
    public Order findById(final Long id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Objeto não encontrado. Id: " + id + ", Tipo: " + Order.class.getSimpleName()
                ));
    }

    @Override
    public void save(CreateOrderRequest request) {
        final var entity = repository.save(mapper.fromRequest(request));
        log.info("Ordem criada: {}", entity);
    }

    @Override
    public OrderResponse update(Long id, UpdateOrdeRequest request) {
        Order entity = findById(id);
        entity = mapper.fromRequest(entity, request);

        if (entity.getStatus().equals(CLOSED)){
            entity.setClosedAt(now());
        }
        return mapper.fromEntity(repository.save(entity));
    }
}
