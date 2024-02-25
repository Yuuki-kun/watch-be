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
@Table(name = "movement")
public class Movement {
    @Id
    @GeneratedValue
    private Long id;

    private String name;//ex: Citizen Caliber Eco-Drive F900; ok
    //Automatic, Quartz, Manual, Mechanical
    private String type;  //ok
    private String power;  //
    private long jewels;
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
    private String precision;
}
