package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
