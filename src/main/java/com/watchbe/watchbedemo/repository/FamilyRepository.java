package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
