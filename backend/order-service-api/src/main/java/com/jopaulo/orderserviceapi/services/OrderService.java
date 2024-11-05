package com.jopaulo.orderserviceapi.services;

import com.jopaulo.orderserviceapi.entities.Order;
import models.requests.CreateOrderRequest;
import models.requests.UpdateOrdeRequest;
import models.response.OrderResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Order findById(final Long id);

    void save(CreateOrderRequest request);

    OrderResponse update(final Long id, UpdateOrdeRequest request);

    void deleteById(final Long id);

    List<Order> findAll();

    Page<Order> findAllPaginated(Integer page, Integer linesPerPage, String direction, String orderBy);
}
