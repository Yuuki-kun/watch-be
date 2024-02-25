package com.watchbe.watchbedemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bracelet_size")
//rolex thuong ban day deo co dinh
public class BraceletSize {
    @Id
    @GeneratedValue
    private Long id;
    //inches
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bracelet_id")
    @JsonBackReference
    private Band band;
}
