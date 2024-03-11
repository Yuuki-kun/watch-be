package com.watchbe.watchbedemo.service.admin.impl;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.watchbe.watchbedemo.dto.OrderDto;
import com.watchbe.watchbedemo.dto.OrderStatusDto;
import com.watchbe.watchbedemo.mapper.OrderMapperImpl;
import com.watchbe.watchbedemo.model.Order;
import com.watchbe.watchbedemo.model.OrderStatus;
import com.watchbe.watchbedemo.model.Status;
import com.watchbe.watchbedemo.repository.OrderRepository;
import com.watchbe.watchbedemo.repository.OrderStatusRepository;
import com.watchbe.watchbedemo.service.admin.OrderManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceImpl implements OrderManagementService {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderMapperImpl orderMapper;
    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(
                orderMapper::mapTo
        ).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> capturePayment(Long orderId) throws StripeException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        String stripePaymentId = order.getStripePaymentId();
        PaymentIntent paymentIntent = PaymentIntent.retrieve(stripePaymentId);
        paymentIntent.capture();

        // Retrieve the existing OrderStatus or create a new one if it doesn't exist
        OrderStatus orderStatus = order.getOrderStatus();
        if (orderStatus == null) {
            orderStatus = OrderStatus.builder().status(Status.CAPTURED).orders(new ArrayList<>()).build();
        } else {
            // If orderStatus exists, update its status to CAPTURED
            orderStatus.setStatus(Status.CAPTURED);
        }
        orderStatusRepository.save(orderStatus); // Save or update the OrderStatus

        order.setOrderStatus(orderStatus); // Associate the Order with the OrderStatus
        orderRepository.save(order); // Save the Order

        return getAllOrders();
    }
}
