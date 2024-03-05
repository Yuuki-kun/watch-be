package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
