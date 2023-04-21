package com.example.cryptotradingsystem.repository;

import com.example.cryptotradingsystem.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    Optional<CryptoCurrency> findBySymbol(String symbol);

}
