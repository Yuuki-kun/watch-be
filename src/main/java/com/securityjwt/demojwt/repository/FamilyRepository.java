package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
