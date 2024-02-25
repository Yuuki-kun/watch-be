package com.securityjwt.demojwt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
