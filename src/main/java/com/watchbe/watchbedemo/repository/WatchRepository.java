package com.watchbe.watchbedemo.repository;

import com.watchbe.watchbedemo.model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WatchRepository extends JpaRepository<Watch, Long> {

    @Query("""
          select w from Watch w inner join Brand b on w.brand.id = b.id where b.id = :brandId
           """)
    List<Watch> findAllWatchesByBrand(Long brandId);

    Optional<Watch> findWatchByReference(String reference);
}
