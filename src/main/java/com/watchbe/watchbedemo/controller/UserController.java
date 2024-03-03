package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.dto.CustomerDto;
import com.watchbe.watchbedemo.dto.ShippingAddressDto;
import com.watchbe.watchbedemo.exception.NotFoundException;
import com.watchbe.watchbedemo.model.Customer;
import com.watchbe.watchbedemo.model.ShippingAddress;
import com.watchbe.watchbedemo.repository.CustomerRepository;
import com.watchbe.watchbedemo.repository.ShippingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final ShippingAddressRepository shippingAddressRepository;
    @GetMapping("/name/{email}")
    public ResponseEntity<Object> getUserName(@PathVariable("email") String email){
        Customer customer= customerRepository.findCustomerByEmail(email).orElseThrow(()-> new NotFoundException((
                "user with " +
                "email "+email+" not found!")));
        //using a Map to return  name and avatar link of the customer instead of creating a new dto
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("fullName", customer.getFirstName() + " " + customer.getLastName());
        userInfo.put("avatar", customer.getAvatarLink());
        //change return to new ResponseEntity
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/cus-info/{cid}")
    //get mapping customer dto by email
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable("cid") Long cid){
        Customer customer= customerRepository.findCustomerByAccountId(cid);
        List<ShippingAddress> shippingAddresses = shippingAddressRepository.findShippingAddressByCustomerId(customer.getId());
        //map shipping address to shipping address dto
        List<ShippingAddressDto> shippingAddressDtos = shippingAddresses.stream().map(shippingAddress -> ShippingAddressDto.builder()
                .id(shippingAddress.getId())
                .name(shippingAddress.getName())
                .phone(shippingAddress.getPhone())
                .address(shippingAddress.getAddress())
                .type(shippingAddress.getType())
                .companyName(shippingAddress.getCompanyName())
                .ward(shippingAddress.getWard())
                .district(shippingAddress.getDistrict())
                .city(shippingAddress.getCity())
                .build()).collect(Collectors.toList());


        return ResponseEntity.ok(CustomerDto.builder()
                .id(customer.getId())
                        .avatarLink(customer.getAvatarLink())
                        .gender(customer.getGender())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .shippingAddresses(shippingAddressDtos)
                .phoneNumber(customer.getPhoneNumber()).build());
    }
}
