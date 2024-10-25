package com.jopaulo.orderserviceapi.services;

import com.jopaulo.orderserviceapi.entities.Order;
import models.requests.CreateOrderRequest;
import models.requests.UpdateOrdeRequest;
import models.response.OrderResponse;

import java.util.List;

public interface OrderService {

    Order findById(final Long id);

    void save(CreateOrderRequest request);

    OrderResponse update(final Long id, UpdateOrdeRequest request);

    void deleteById(final Long id);

    List<Order> findAll();
}
