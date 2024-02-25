package com.securityjwt.demojwt.repository;

import com.securityjwt.demojwt.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
