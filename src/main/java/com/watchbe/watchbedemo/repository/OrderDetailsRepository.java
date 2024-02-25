package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
