package com.securityjwt.demojwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovementDto {
    private Long id;
    private String name;//ex: Citizen Caliber Eco-Drive F900;
    //Automatic, Quartz, Manual, Mechanical
    private String type;
    private String power;
    private String jewels;
    //millimeter
    private float diameter;
    private String powerReserve;
    //Hz, bph(beat per hour)
    private float frequency;
    private String brand;
    private String display;
    private String date;
    private String chronograph;
    private String hands;
    private String acoustic;
    private String additionalFunctions;
}
