package com.watchbe.watchbedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long cartId; //=> cart item id also use as order-details id if it's returned to client
    private Long watchId;
    private float price;
    private Long quantity;
}
