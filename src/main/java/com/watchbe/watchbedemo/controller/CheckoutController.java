package com.watchbe.watchbedemo.controller;

import com.stripe.exception.StripeException;
import com.watchbe.watchbedemo.dto.CreateCheckoutRequest;
import com.watchbe.watchbedemo.service.PaypalService;
import com.watchbe.watchbedemo.service.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final StripeService stripeCheckoutService;
    private final PaypalService paypalService;
//    public CheckoutController(@Qualifier("stripeCheckoutServiceImpl") CheckoutService stripeCheckoutService) {
//        this.stripeCheckoutService = stripeCheckoutService;
//    }

    @PostMapping("/create-payment-session/{method}")
        public String createPaymentIntent(@RequestBody CreateCheckoutRequest checkOutRequest,
                                          @PathVariable("method") String method) throws StripeException {
        System.out.println("ck request  = "+checkOutRequest);
        System.out.println("ck method = "+method);
        String paymentUrl;
        if(method.equals("stripe"))
            paymentUrl = stripeCheckoutService.processPaymentIntent(checkOutRequest);
        else if (method.equals("paypal")) {
            paymentUrl = paypalService.processPaymentIntent(checkOutRequest);
        }else{
            throw new RuntimeException("Invalid payment method");
        }
        return paymentUrl;
    }


}
