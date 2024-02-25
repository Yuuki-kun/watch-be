package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
}
