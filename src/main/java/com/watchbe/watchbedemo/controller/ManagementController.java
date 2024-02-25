package com.watchbe.watchbedemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-management")
public class ManagementController {
    @GetMapping
    public String get(){
        return "GET-MANAGEMENT Controler";
    }

    @PostMapping
    public String post(){
        return "POST-MANAGEMENT Controler";
    }

    @PutMapping
    public String put(){
        return "PUT-MANAGEMENT Controler";
    }
    @DeleteMapping
    public String delete(){
        return "DELETE-MANAGEMENT Controler";
    }

}
