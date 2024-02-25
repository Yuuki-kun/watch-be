package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
