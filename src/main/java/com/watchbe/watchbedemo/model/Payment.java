package com.watchbe.watchbedemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    private String paymentIntentId;
    private Date date;
    private String type;
    private String paymentMethod;
}
