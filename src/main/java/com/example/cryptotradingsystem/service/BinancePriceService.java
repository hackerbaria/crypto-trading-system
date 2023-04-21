package com.example.cryptotradingsystem.service;

import com.example.cryptotradingsystem.dto.BinanceDto;
import com.example.cryptotradingsystem.dto.PriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("binancePriceService")
public class BinancePriceService implements PriceService {

    private final ExternalPriceService externalPriceService;

    public BinancePriceService(ExternalPriceService externalPriceService) {
        this.externalPriceService = externalPriceService;
    }

    @Override
    public List<PriceDto> getLatestPrice() {
        String url = "https://api.binance.com/api/v3/ticker/bookTicker";
        ResponseEntity<BinanceDto[]> response = externalPriceService.getPriceData(url, BinanceDto[].class);
        List<BinanceDto> filteredList = Arrays.stream(response.getBody())
                .filter(dto -> dto.getSymbol().equalsIgnoreCase("ETHUSDT") || dto.getSymbol().equalsIgnoreCase("BTCUSDT"))
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            throw new RuntimeException("No ETHUSDT or BTCUSDT found in the BinanceDto list");
        }

        List<PriceDto> priceDtoList = filteredList.stream()
                .map(binanceDto -> new PriceDto(
                        binanceDto.getSymbol(),
                        binanceDto.getBidPrice(),
                        binanceDto.getBidQty(),
                        binanceDto.getAskPrice(),
                        binanceDto.getAskQty(),
                        "Binance"))
                .collect(Collectors.toList());

        return priceDtoList;
    }
}