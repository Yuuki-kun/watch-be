package com.watchbe.watchbedemo.service;

import com.stripe.exception.StripeException;
import com.watchbe.watchbedemo.dto.CreateCheckoutRequest;
import com.watchbe.watchbedemo.model.Order;

public interface StripeCheckoutService {
    //create order and return order entity
    Order createOrder(CreateCheckoutRequest checkOutRequest);
    //create check out session and return payment address page
    String createPaymentSession(Order order) throws StripeException;
}
