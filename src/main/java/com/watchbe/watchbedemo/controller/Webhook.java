package com.watchbe.watchbedemo.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.watchbe.watchbedemo.model.Order;
import com.watchbe.watchbedemo.model.OrderStatus;
import com.watchbe.watchbedemo.model.Status;
import com.watchbe.watchbedemo.repository.OrderRepository;
import com.watchbe.watchbedemo.repository.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/webhook")
@RequiredArgsConstructor
public class Webhook {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    @PostMapping("/stripe")
    public void handleWebhook (@RequestBody String payload, @RequestHeader("Stripe-Signature" ) String sigHeader) throws Exception {
        System.out.println("payload = "+payload);
        Event event;
        String endpointSecret = "whsec_4cde4aba2cfdb173b1f18b8ad66dc02cd9a094fe4aaa535bf68a3bae9f12da10";
        try {
            event = com.stripe.net.Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            // Xử lý khi xác thực không thành công
            System.out.println(e);
            return;
        }
        switch (event.getType()) {
            case "payment_intent.created":
                // Xử lý khi có sự kiện payment_intent.created
//                String payloadJson = payload;
//                JsonObject jsonObject = JsonParser.parseString(payloadJson).getAsJsonObject();
//                JsonObject dataobject = jsonObject.getAsJsonObject("data");
//                String csid = dataobject.getAsJsonObject("object").get("id").getAsString();

                break;
            case "checkout.session.completed":
                // Xử lý khi có sự kiện checkout.session.completed
                String payloadJson = payload;
                JsonObject jsonObject = JsonParser.parseString(payloadJson).getAsJsonObject();
                JsonObject dataobject = jsonObject.getAsJsonObject("data");
                String csid = dataobject.getAsJsonObject("object").get("id").getAsString();
                System.out.println("csid = "+csid);
                Order order = orderRepository.findByStripePaymentId(csid);
                OrderStatus orderStatus = OrderStatus.builder().status(Status.UNCAPTURED).orders(new ArrayList<>()).build();
                orderStatusRepository.save(orderStatus);
                order.setOrderStatus(orderStatus);

                Session session = Session.retrieve(csid);
                order.setStripePaymentIntentId(session.getPaymentIntent());

                orderRepository.save(order);
                break;
        }

    }
}
