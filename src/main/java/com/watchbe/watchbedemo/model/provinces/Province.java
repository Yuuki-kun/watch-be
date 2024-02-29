package com.watchbe.watchbedemo.model.provinces;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "code_name")
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnit administrativeUnit;

    @ManyToOne
    @JoinColumn(name = "administrative_region_id")
    private AdministrativeRegion administrativeRegion;
}
