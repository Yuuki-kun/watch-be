package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
}
