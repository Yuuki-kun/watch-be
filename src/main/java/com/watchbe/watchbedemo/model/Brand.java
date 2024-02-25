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
@Table(name = "brand")
public class Brand{
    @Id
    @GeneratedValue
    private Long id;

    private String brandName;

    @OneToMany(mappedBy = "brand")
//    @JsonManagedReference
    @JsonBackReference  //=> when to json -> this List won't serialize
    private List<Watch> watches;
}
