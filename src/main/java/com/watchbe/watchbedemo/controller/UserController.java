package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.dto.CustomerDto;
import com.watchbe.watchbedemo.dto.OrderDto;
import com.watchbe.watchbedemo.dto.ShippingAddressDto;
import com.watchbe.watchbedemo.exception.NotFoundException;
import com.watchbe.watchbedemo.mapper.OrderMapperImpl;
import com.watchbe.watchbedemo.model.Customer;
import com.watchbe.watchbedemo.model.Order;
import com.watchbe.watchbedemo.model.ShippingAddress;
import com.watchbe.watchbedemo.repository.CustomerRepository;
import com.watchbe.watchbedemo.repository.OrderRepository;
import com.watchbe.watchbedemo.repository.ShippingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final OrderRepository orderRepository;
    private final OrderMapperImpl orderMapper;
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
    //get mapping customer dto by id
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("cid") Long cid){
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
                .isDefault(shippingAddress.getIsDefault())
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

    @GetMapping("/cus-address/{cid}")
    public ResponseEntity<List<ShippingAddressDto>> getCustomerAddress(@PathVariable("cid") Long cid){
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
                .isDefault(shippingAddress.getIsDefault())
                .build()).collect(Collectors.toList());
        return ResponseEntity.ok(shippingAddressDtos);
    }

    @PutMapping("/cus-address/default/{cid}/{aid}")
    public ResponseEntity<List<ShippingAddressDto>> updateDefaultAddress(@PathVariable("cid") Long cid, @PathVariable("aid") Long aid){
        Customer customer= customerRepository.findCustomerByAccountId(cid);
        List<ShippingAddress> shippingAddresses = shippingAddressRepository.findShippingAddressByCustomerId(customer.getId());
        //set default address
        shippingAddresses.forEach(shippingAddress -> {
            if(shippingAddress.getId().equals(aid)){
                shippingAddress.setIsDefault(true);
            }else{
                shippingAddress.setIsDefault(false);
            }
            shippingAddressRepository.save(shippingAddress);
        });
//        map shipping address to shipping address dto
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
                .isDefault(shippingAddress.getIsDefault())
                .build()).collect(Collectors.toList());
        return ResponseEntity.ok(shippingAddressDtos);
    }

    @GetMapping("/cus-orders/{cid}")
    public ResponseEntity<List<OrderDto>> getCustomerOrders(@PathVariable("cid") Long cid){
        List<Order> order = orderRepository.findAllByCustomer_Id(cid);

        return ResponseEntity.ok(order.stream().map(orderMapper::mapTo).collect(Collectors.toList()));
    }
}
