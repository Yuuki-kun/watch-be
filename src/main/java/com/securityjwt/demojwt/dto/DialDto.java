package com.securityjwt.demojwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DialDto {
    private Long id;
    private String color;
    private String indexes;
    private String hands;
    private String type;

    private String subDials;
    private String luminescence;
    private String gemSetting;
    private String img;
}
