package com.watchbe.watchbedemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/webhook")
@RequiredArgsConstructor
public class Webhook {
    @PostMapping("/gevent")
    public void handleWebhook (@RequestBody String payload) throws Exception {
        System.out.println(payload);
    }
}
