package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BraceletRepository extends JpaRepository<Band, Long> {
}
