package com.securityjwt.demojwt.dto;

import com.securityjwt.demojwt.model.Watch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private Long id;

    private long quantity;
    //price after discount
    private float price;
    private WatchDto watch;

}
