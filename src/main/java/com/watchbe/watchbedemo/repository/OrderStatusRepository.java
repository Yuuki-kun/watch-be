package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>{
}
