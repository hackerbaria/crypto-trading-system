package com.example.cryptotradingsystem.controller;

import com.example.cryptotradingsystem.dto.AggregatedPriceDto;
import com.example.cryptotradingsystem.dto.TradeRequestDto;
import com.example.cryptotradingsystem.dto.TradeResponseDto;
import com.example.cryptotradingsystem.model.AggregatedPrice;
import com.example.cryptotradingsystem.repository.AggregatedPriceRepository;
import com.example.cryptotradingsystem.service.PriceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price")
public class PriceController {

     //PriceService priceService;

    private final AggregatedPriceRepository aggregatedPriceRepository;

    public PriceController(AggregatedPriceRepository aggregatedPriceRepository) {
        this.aggregatedPriceRepository = aggregatedPriceRepository;
    }

    // 2. API to retrieve the latest best aggregated price.

    @GetMapping("/latest")
    public ResponseEntity<List<AggregatedPrice>> getLatestPrice() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        List<AggregatedPrice> aggregatedPriceDto = aggregatedPriceRepository.findLatestPrices(pageRequest);
        return new ResponseEntity<>(aggregatedPriceDto, HttpStatus.OK);
    }

}
