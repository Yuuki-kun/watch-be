package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.dto.OrderDto;
import com.watchbe.watchbedemo.dto.UserDto;
import com.watchbe.watchbedemo.repository.AccountRepository;
import com.watchbe.watchbedemo.model.account.Account;
import com.watchbe.watchbedemo.service.admin.impl.OrderManagementServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AccountRepository accountRepository;
    private final OrderManagementServiceImpl orderManagementService;
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderManagementService.getAllOrders());
    }


    @GetMapping
    public String get(){
        return "GET-ADMIN Controler";
    }

    @PostMapping
    public String post(){
        return "POST-ADMIN Controler";
    }

    @PutMapping
    public String put(){
        return "PUT-ADMIN Controler";
    }
    @DeleteMapping
    public String delete(){
        return "DELETE-ADMIN Controler";
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<Account> users = accountRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto u = UserDto.builder().email(user.getEmail()).build();
            userDtos.add(u);
        });
        return ResponseEntity.ok(userDtos);
    }

}
