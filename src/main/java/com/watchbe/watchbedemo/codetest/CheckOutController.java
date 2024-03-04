package com.watchbe.watchbedemo.codetest;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("api/v1/checkout")
public class CheckOutController {
    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody Request request) throws StripeException {


        SessionCreateParams params = new SessionCreateParams.Builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://your_domain/checkout/success")
                .setCancelUrl("https://your_domain/checkout/cancel")

                .setShippingAddressCollection(SessionCreateParams.ShippingAddressCollection.builder().addAllAllowedCountry(
                        new ArrayList<>(
                                Arrays.asList(SessionCreateParams.ShippingAddressCollection.AllowedCountry.US,
                                        SessionCreateParams.ShippingAddressCollection.AllowedCountry.VN)
                        )).build())
                .setPaymentIntentData(SessionCreateParams.PaymentIntentData.builder()
                        .setCaptureMethod(SessionCreateParams.PaymentIntentData.CaptureMethod.MANUAL)
                        .build())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(request.getAmount() * 100) // assuming the amount is in cents
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(request.getProductName())

                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();

        Session session = Session.create(params);

        return session.getUrl();

    }
}
