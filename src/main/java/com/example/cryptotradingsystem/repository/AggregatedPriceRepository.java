package com.example.cryptotradingsystem.repository;

import com.example.cryptotradingsystem.model.AggregatedPrice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AggregatedPriceRepository extends JpaRepository<AggregatedPrice, Long> {

    @Query("SELECT ap FROM AggregatedPrice ap ORDER BY ap.lastUpdated DESC")
    List<AggregatedPrice> findLatestPrices(Pageable pageable);
}
