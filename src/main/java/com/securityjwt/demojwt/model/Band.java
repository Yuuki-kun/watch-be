package com.securityjwt.demojwt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue
    private Long id;
    private String reference;
    private String material;
    //type of single ring, NATO, butterfly
    private String type;
    //loai chot khoa
    private String clasp;
    private float width;
    private String color;

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BraceletSize> braceletSizes = new ArrayList<>();

    public void setBraceletSizes(List<BraceletSize> braceletSizes) {
        this.braceletSizes = braceletSizes;
        for (BraceletSize i: braceletSizes) {
            i.setBand(this);
        }
    }}
