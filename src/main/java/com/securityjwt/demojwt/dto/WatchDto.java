package com.securityjwt.demojwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WatchDto {
    private Long id;
    private String reference;
    private String name;
    private LocalDate produced;
    private String origin;
    private float weight;
    private String gender;
    private long warranty;
    private boolean limited;
    private String description;
    private long inventoryQuantity;
    private long soldQuantity;
    private float defaultPrices;
    private float stars;

    private BrandDto brand;
    private FamilyDto family;
    private BandDto band;
    private List<DialDto> dials;
    private CaseDto watchCase;
    private MovementDto movement;
    private List<ImageDto> images;
    private List<ReviewDto> reviews;

}
