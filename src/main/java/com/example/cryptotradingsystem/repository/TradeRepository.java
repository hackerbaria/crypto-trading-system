package com.example.cryptotradingsystem.repository;

import com.example.cryptotradingsystem.model.Trade;
import com.example.cryptotradingsystem.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findAllByOrderByIdDesc();

}
