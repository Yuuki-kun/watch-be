package com.watchbe.watchbedemo.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String email;

    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("refresh_token")
    private String refresh_token;

    private List<Integer> roles;
    private String message;

    private long cartId;
    private long userId;



}
