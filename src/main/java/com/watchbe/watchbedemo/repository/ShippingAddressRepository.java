package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
    List<ShippingAddress> findShippingAddressByCustomerId(Long customerId);
}
