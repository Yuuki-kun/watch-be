package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BraceletRepository extends JpaRepository<Band, Long> {
}
