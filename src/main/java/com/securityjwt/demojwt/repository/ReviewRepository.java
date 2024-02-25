package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
