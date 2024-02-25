package com.securityjwt.demojwt.model;

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
@Table(name = "w_case")
public class Case {
    @Id
    @GeneratedValue
    private Long id;

    private String material;
    //a ring surrounds
    private String bezel;
    private String waterResistance;
    private String back;
    private float thickness;
    private String shape;
    private float diameter;
    private float lugWidth;
    private String crystal;
    private String crystalDescription;


}
