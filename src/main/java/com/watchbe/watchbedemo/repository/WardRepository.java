package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.provinces.District;
import com.watchbe.watchbedemo.model.provinces.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, String> {
    List<Ward> findAllByDistrictCode(String districtCodde);
}
