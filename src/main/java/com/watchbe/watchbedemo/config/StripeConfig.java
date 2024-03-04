package com.watchbe.watchbedemo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig  {
    @Value("${stripe.secret-key}")
    private String secretKey;


    @PostConstruct
    public void initSecretKey() {
        com.stripe.Stripe.apiKey = secretKey;
    }
}
