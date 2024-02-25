package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Dial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DialRepository extends JpaRepository<Dial, Long> {
}
