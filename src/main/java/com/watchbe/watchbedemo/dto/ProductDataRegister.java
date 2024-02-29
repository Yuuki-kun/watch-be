package com.watchbe.watchbedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataRegister {
    private Long id;

    private long quantity;
    //price after discount
    private float price;
    private Long watchId;
}
