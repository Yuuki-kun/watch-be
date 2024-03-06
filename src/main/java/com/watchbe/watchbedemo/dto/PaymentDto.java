package com.watchbe.watchbedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
    private Long id;

    private String paymentIntentId;
    private Date date;
    private String type;
    private String paymentMethod;
}
