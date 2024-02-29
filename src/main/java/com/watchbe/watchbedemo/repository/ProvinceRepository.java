package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.provinces.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, String> {
}
