package com.watchbe.watchbedemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BandDto {
    private Long id;

    private String material;
    //type of single ring, NATO, butterfly
    private String type;
    private String color;
    //loai chot khoa
    private String clasp;
    private String reference;
    private float width;
    private List<BraceletSizeDto> braceletSizes;
}
