package com.watchbe.watchbedemo.controller.admin;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.watchbe.watchbedemo.dto.OrderDto;
import com.watchbe.watchbedemo.model.Order;
import com.watchbe.watchbedemo.service.admin.impl.OrderManagementServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin-order-mgt")
@RequiredArgsConstructor
public class OrderMgtController {
    private final OrderManagementServiceImpl orderManagementService;

    @PostMapping("/capture/{orderId}")
    public ResponseEntity<List<OrderDto>> capturePayment(@PathVariable Long orderId) throws StripeException {

        List<OrderDto> orderDtos =  orderManagementService.capturePayment(orderId);
        return ResponseEntity.ok(orderDtos);
    }
}
