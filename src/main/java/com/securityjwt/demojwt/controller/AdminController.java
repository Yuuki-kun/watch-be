package com.securityjwt.demojwt.controller;

import com.securityjwt.demojwt.dto.UserDto;
import com.securityjwt.demojwt.repository.AccountRepository;
import com.securityjwt.demojwt.model.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/demo-admin")
@RequiredArgsConstructor
public class AdminController {
    private final AccountRepository accountRepository;
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
