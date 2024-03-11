package com.watchbe.watchbedemo.service.admin;

import com.stripe.exception.StripeException;
import com.watchbe.watchbedemo.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderManagementService {
    List<OrderDto> getAllOrders();

    List<OrderDto> capturePayment(Long orderId) throws StripeException;
}
