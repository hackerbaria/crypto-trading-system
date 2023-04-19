package com.example.cryptotradingsystem.repository;

import com.example.cryptotradingsystem.model.AggregatedPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AggregatedPriceRepository extends JpaRepository<AggregatedPrice, Long> {
    List<AggregatedPrice> findTopByTradingPairOrderByTimestampDesc(String tradingPair);
}
