package com.watchbe.watchbedemo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private String phoneNumber;

    private LocalDate dob;

    private String gender;
    private String avatarLink;

    private List<ShippingAddressDto> shippingAddresses;
}
