package com.example.cryptotradingsystem.service;

import com.example.cryptotradingsystem.dto.*;
import com.example.cryptotradingsystem.model.TradeType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                        binanceDto.getAsk(),
                        "Huobi"))
                .collect(Collectors.toList());

        return priceDtoList;
    }

    @Override
    public TradeResponseDto tradeBasedOnLatestPrice(TradeRequestDto tradeRequestDto) {
        String url = "https://api.huobi.pro/market/tickers";
        ResponseEntity<HoubiPriceResponseDto> response = externalPriceService.getPriceData(url, HoubiPriceResponseDto.class);
        HoubiPriceDto bestPrice = getBestPrice(response.getBody().getData(), "btcusdt");
        BigDecimal quantity = tradeRequestDto.getAmount();
        BigDecimal price = bestPrice.getBid();
        BigDecimal amount = quantity.multiply(price);
        return new TradeResponseDto("success", "btcusdt", TradeType.BUY.name(),price, amount );
    }


    private HoubiPriceDto getBestPrice(List<HoubiPriceDto> prices, String symbol) {
        return prices.stream()
                .filter(price -> price.getSymbol().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No prices found for symbol: " + symbol));
    }
}
