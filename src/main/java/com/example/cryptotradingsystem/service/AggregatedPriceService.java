package com.example.cryptotradingsystem.service;


import com.example.cryptotradingsystem.model.AggregatedPrice;
import com.example.cryptotradingsystem.repository.AggregatedPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregatedPriceService {

    @Autowired
    private AggregatedPriceRepository aggregatedPriceRepository;

    public List<AggregatedPrice> getLatestPrices() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("lastUpdated").descending());
        return aggregatedPriceRepository.findLatestPrices(pageable);
    }
}
