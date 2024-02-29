package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.provinces.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, String> {
    List<District> findAllByProvinceCode(String provinceCode);
}
