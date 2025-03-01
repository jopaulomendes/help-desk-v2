package com.jopaulo.orderserviceapi.services.impl;

import com.jopaulo.orderserviceapi.clients.UserServiceFeignClient;
import com.jopaulo.orderserviceapi.entities.Order;
import com.jopaulo.orderserviceapi.mapper.OrderMapper;
import com.jopaulo.orderserviceapi.repositories.OrderRepository;
import com.jopaulo.orderserviceapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import models.dtos.OrderCreatedMessage;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateOrderRequest;
import models.requests.UpdateOrdeRequest;
import models.response.OrderResponse;
import models.response.UserResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;
import static models.enums.OrderStatusEnum.CLOSED;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final UserServiceFeignClient userServiceFeignClient;
    private final RabbitTemplate rabbitTemplate;

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
        final var requester = validateUserId(request.requesterId());
        final var customer = validateUserId(request.customerId());
        final var entity = repository.save(mapper.fromRequest(request));

        log.info("Ordem criada: {}", entity);

        rabbitTemplate.convertAndSend(
                "helpdesk",
                "rk.orders.create",
                new OrderCreatedMessage(mapper.fromEntity(entity), customer, requester)
        );
    }
    //mtqb afoq uwpi fgbr

    @Override
    public OrderResponse update(Long id, UpdateOrdeRequest request) {
        validateUsers(request);

        Order entity = findById(id);
        entity = mapper.fromRequest(entity, request);

        if (entity.getStatus().equals(CLOSED)){
            entity.setClosedAt(now());
        }
        return mapper.fromEntity(repository.save(entity));
    }

    @Override
    public void deleteById(final Long id) {
        repository.delete(findById(id));
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Order> findAllPaginated(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(
                page,
                linesPerPage,
                org.springframework.data.domain.Sort.Direction.valueOf(direction),
                orderBy
        );
        return repository.findAll(pageRequest);
    }

    UserResponse validateUserId(final String userId){
        return userServiceFeignClient.findById(userId).getBody();
    }

    private void validateUsers(UpdateOrdeRequest request) {
        if (request.requesterId() != null) validateUserId(request.requesterId());
        if (request.customerId() != null) validateUserId(request.customerId());
    }
}
