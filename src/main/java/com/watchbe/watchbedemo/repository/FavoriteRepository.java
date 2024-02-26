package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
     List<Favorite> findFavoritesByCustomerId(Long customerId);
     @Transactional
    void deleteFavoriteByCustomerIdAndWatchId(Long customerId, Long watchId);
//    int deleteFavoriteById(Long id);
}
