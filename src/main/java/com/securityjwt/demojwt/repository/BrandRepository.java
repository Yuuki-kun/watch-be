package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.Brand;
import com.securityjwt.demojwt.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
