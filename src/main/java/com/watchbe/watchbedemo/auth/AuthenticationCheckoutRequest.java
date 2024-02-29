package com.watchbe.watchbedemo.auth;

import com.watchbe.watchbedemo.dto.CartItem;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationCheckoutRequest {
    private String email;
    private String password;
    private List<CartItem> cartItems;
}
