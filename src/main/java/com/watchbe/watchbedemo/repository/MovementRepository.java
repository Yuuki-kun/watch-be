package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {

}
