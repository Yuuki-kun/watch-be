package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
