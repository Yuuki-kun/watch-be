package com.watchbe.watchbedemo.controller;

import com.watchbe.watchbedemo.model.provinces.District;
import com.watchbe.watchbedemo.model.provinces.Province;
import com.watchbe.watchbedemo.repository.DistrictRepository;
import com.watchbe.watchbedemo.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/provinces")
@RequiredArgsConstructor
public class ProvincesController {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    @GetMapping
    public List<String> getAlls(){
        return provinceRepository.findAll().stream().map(province -> province.getName()).collect(Collectors.toList());
    }
    @GetMapping("/dis")
    public List<String> findDistrictByProvinceName(@RequestParam("pcode")String pcode){
        return districtRepository.findAllByProvinceCode(pcode).stream().map(district -> district.getName()).collect(Collectors.toList());
    }
}
