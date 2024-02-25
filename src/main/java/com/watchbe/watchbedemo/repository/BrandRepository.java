package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
