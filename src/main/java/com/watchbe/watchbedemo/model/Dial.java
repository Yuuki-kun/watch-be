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
@Table(name = "dial")
public class Dial {
    @Id
    @GeneratedValue
    private Long id;

    private String color;
    private String indexes;
    private String hands;
    private String type;
    private String subDials;
    private String luminescence;
    private String gemSetting;
    private String img;

    @ManyToOne
    @JoinColumn(name = "watch_id")
    @JsonBackReference
    private Watch watch;
}
