package com.example.cryptotradingsystem.service;

import com.example.cryptotradingsystem.dto.HoubiPriceDto;
import com.example.cryptotradingsystem.dto.HoubiPriceResponseDto;
import com.example.cryptotradingsystem.dto.PriceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("houbiPriceService")
public class HoubiPriceService implements PriceService {

    private final ExternalPriceService externalPriceService;

    public HoubiPriceService(ExternalPriceService externalPriceService) {
        this.externalPriceService = externalPriceService;
    }

    @Override
    public List<PriceDto> getLatestPrice() {
        String url = "https://api.huobi.pro/market/tickers";
        ResponseEntity<HoubiPriceResponseDto> response = externalPriceService.getPriceData(url, HoubiPriceResponseDto.class);
        List<HoubiPriceDto> houbiPriceDtos = response.getBody().getData();

        List<HoubiPriceDto> filteredList = houbiPriceDtos.stream()
                .filter(dto -> dto.getSymbol().equalsIgnoreCase("ETHUSDT") || dto.getSymbol().equalsIgnoreCase("BTCUSDT"))
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            throw new RuntimeException("No ETHUSDT or BTCUSDT found in the huobi list");
        }

        List<PriceDto> priceDtoList = filteredList.stream()
                .map(binanceDto -> new PriceDto(
                        binanceDto.getSymbol(),
                        binanceDto.getBid(),
                        binanceDto.getBidSize(),
                        binanceDto.getAsk(),
                        binanceDto.getAskSize(),
                        "Huobi"))
                .collect(Collectors.toList());

        return priceDtoList;
    }
}
