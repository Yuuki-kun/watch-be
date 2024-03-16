package com.watchbe.watchbedemo.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.watchbe.watchbedemo.config.PaypalConfig;
import com.watchbe.watchbedemo.model.Order;
import com.watchbe.watchbedemo.repository.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service

public class PaypalService extends CheckoutService {
    private final PaypalConfig paypalConfig;
    private final PaypalAccessTokenStore paypalAccessTokenStore;
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String getAuth(){
        return Base64.getEncoder().encodeToString((paypalConfig.getClientId()+":"+paypalConfig.getClientSecret()).getBytes());
    }

    public PaypalService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                         CustomerRepository customerRepository, OrderStatusRepository orderStatusRepository,
                         PaymentRepository paymentRepository,
                         PaypalConfig paypalConfig,
                         PaypalAccessTokenStore paypalAccessTokenStore
    ) {
        super(orderRepository, orderDetailsRepository, customerRepository, orderStatusRepository, paymentRepository);
        this.paypalConfig = paypalConfig;
        this.paypalAccessTokenStore = paypalAccessTokenStore;
    }

    @Override
    protected String createPaymentIntent(Order order) {

        return "paypal";
    }

    public void checkOrder(String orderId) throws IOException {
        String accessToken = paypalAccessTokenStore.getAccessToken("access_token");
        if(accessToken == null) {
            System.out.println("token is expired");
            accessToken = generateAccessToken();
        }
        URL url = new URL("https://api-m.sandbox.paypal.com/v2/checkout/orders/"+orderId);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("Authorization", "Bearer " + accessToken);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }

    public String generateAccessToken(){
        String auth = getAuth();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setAcceptLanguageAsLocales(Collections.singletonList(Locale.US));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic "+auth);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                paypalConfig.getBaseURL()+"/v1/oauth2/token",
                HttpMethod.POST,
                request,
                String.class
        );
        JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();

        if (response.getStatusCode() == HttpStatus.OK) {
            LOGGER.log(Level.INFO, "Access token generated successfully");
            String accessToken = jsonResponse.get("access_token").getAsString();
            System.out.println("Access Token: " + accessToken);
                paypalAccessTokenStore.saveAccessToken("access_token",accessToken, jsonResponse.get("expires_in").getAsInt());
            return paypalAccessTokenStore.getAccessToken("access_token");
        } else {
            System.err.println("Failed to get access token. Status code: " + response.getStatusCode());
            return ("Error: " + jsonResponse.get("error"));
        }

    }

}
