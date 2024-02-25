package com.watchbe.watchbedemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "family")
public class Family {
    @Id
    @GeneratedValue
    private Long id;

    private String familyName;

    @OneToMany(mappedBy = "family")
//    @JsonManagedReference
    @JsonBackReference
    private List<Watch> watches;
}
