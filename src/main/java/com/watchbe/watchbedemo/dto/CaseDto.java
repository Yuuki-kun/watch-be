package com.watchbe.watchbedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaseDto {
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
