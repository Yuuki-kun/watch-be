package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findAllByCustomer_Id(Long id);
}
