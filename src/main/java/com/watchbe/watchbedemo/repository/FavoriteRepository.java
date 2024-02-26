package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    public List<Favorite> findFavoritesByCustomerId(Long customerId);
}
