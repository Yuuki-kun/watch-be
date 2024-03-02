package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.exception.NotFoundException;
import com.watchbe.watchbedemo.model.Customer;
import com.watchbe.watchbedemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;

    @GetMapping("/name/{email}")
    public ResponseEntity<String> getUserName(@PathVariable("email") String email){
        Customer customer= customerRepository.findCustomerByEmail(email).orElseThrow(()-> new NotFoundException((
                "user with " +
                "email "+email+" not found!")));
        return ResponseEntity.ok(customer.getFirstName() +" "+customer.getLastName());
    }

}
