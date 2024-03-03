package com.watchbe.watchbedemo.dto;

import com.watchbe.watchbedemo.model.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressDto {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private AddressType type;
    private String companyName;
    private String ward;
    private String district;
    private String city;
}
