package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
