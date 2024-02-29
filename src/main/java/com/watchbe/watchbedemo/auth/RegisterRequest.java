package com.watchbe.watchbedemo.auth;

import com.watchbe.watchbedemo.dto.OrderDetailsDto;
import com.watchbe.watchbedemo.dto.ProductDataRegister;
import com.watchbe.watchbedemo.model.account.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
    private String gender;
    private List<ProductDataRegister> productDataRegisters;
}
