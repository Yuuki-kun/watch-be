package com.watchbe.watchbedemo.controller;

import com.stripe.exception.StripeException;
import com.watchbe.watchbedemo.dto.CreateCheckoutRequest;
import com.watchbe.watchbedemo.model.Order;
import com.watchbe.watchbedemo.service.StripeCheckoutServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("api/v1/checkout")
@RequiredArgsConstructor
public class StripeCheckoutController {
    private final StripeCheckoutServiceImpl stripeCheckoutService;
    @PostMapping("/create-payment-session")
        public ResponseEntity<String> createPaymentIntent(@RequestBody CreateCheckoutRequest checkOutRequest) throws StripeException {
        Order order = stripeCheckoutService.createOrder(checkOutRequest);
        String paymentUrl = stripeCheckoutService.createPaymentSession(order);
        return ResponseEntity.ok(paymentUrl);
    }

}
