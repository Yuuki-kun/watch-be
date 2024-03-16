package com.watchbe.watchbedemo.service;

import com.stripe.exception.StripeException;
import com.watchbe.watchbedemo.dto.CreateCheckoutRequest;
import com.watchbe.watchbedemo.model.*;
import com.watchbe.watchbedemo.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public abstract class CheckoutService {
    protected final OrderRepository orderRepository;
    protected final OrderDetailsRepository orderDetailsRepository;
    protected final CustomerRepository customerRepository;
    protected final OrderStatusRepository orderStatusRepository;
    protected final PaymentRepository paymentRepository;
    public CheckoutService(OrderRepository orderRepository,OrderDetailsRepository orderDetailsRepository
            ,CustomerRepository customerRepository,OrderStatusRepository orderStatusRepository,PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.customerRepository = customerRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.paymentRepository = paymentRepository;
    }
    protected Order createOrder(CreateCheckoutRequest checkOutRequest) {
        if (checkOutRequest.getOrderDetailsId() == null || checkOutRequest.getOrderDetailsId().isEmpty()) {
            throw new IllegalArgumentException("order items cannot be empty");
        }
        if (checkOutRequest.getCustomerId() == 0) {
            throw new IllegalArgumentException("Customer id cannot be empty");
        }

        List<OrderDetails> orderDetailsList =
                orderDetailsRepository.findAllById(checkOutRequest.getOrderDetailsId());

        double totalAmount =  orderDetailsList.stream()
                .mapToDouble(orderDetails -> orderDetails.getPrice() * orderDetails.getQuantity())
                .sum();

        Customer customer =
                customerRepository.findById(checkOutRequest.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("Customer not found"));



        Payment payment = Payment.builder()
                .date(new Date())
                .type("Stripe")
                .build();
        paymentRepository.save(payment);

        ShippingAddress shippingAddress =
                customer.getShippingAddresses().stream().filter(ShippingAddress::getIsDefault).findFirst().get();

        Order order =
                Order.builder().customer(customer).payment(payment).address(shippingAddress).orderDate(new Date()).tax(100f).amount(totalAmount).shipping(100f).build();
        order.setOrderDetails(orderDetailsList);

        //find in customer.getShippingAddresses if shipping address is default address then set it to order
        //if not then set the first address to order
//        order.setAddress(customer.getShippingAddresses().stream().filter(ShippingAddress::getIsDefault).findFirst().get());
        orderRepository.save(order);
        return order;
    }
    protected abstract String createPaymentIntent(Order order) throws StripeException;
    public String processPaymentIntent(CreateCheckoutRequest request) throws StripeException {
        Order order = createOrder(request);
        String paymentURL =  createPaymentIntent(order);
        return paymentURL;
    }
}
