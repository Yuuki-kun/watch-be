package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
