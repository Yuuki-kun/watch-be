package com.watchbe.watchbedemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipping_address")
public class ShippingAddress {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String phone;
    private String address;
    private AddressType type;
    private String companyName;
//    private Ward ward;
//    private District district;
//    private City city;
}
