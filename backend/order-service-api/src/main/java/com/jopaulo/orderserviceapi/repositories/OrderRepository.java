package com.jopaulo.orderserviceapi.repositories;

import com.jopaulo.orderserviceapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
