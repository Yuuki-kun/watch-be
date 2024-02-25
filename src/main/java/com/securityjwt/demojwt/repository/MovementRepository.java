package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {

}
