package com.example.cryptotradingsystem.service;

import org.springframework.stereotype.Component;

@Component
public class PriceServiceFactory {

    private final BinancePriceService binancePriceService;
    private final HoubiPriceService houbiPriceService;

    public PriceServiceFactory(BinancePriceService binancePriceService, HoubiPriceService houbiPriceService) {
        this.binancePriceService = binancePriceService;
        this.houbiPriceService = houbiPriceService;
    }

    public PriceService getPriceService(String tradingPair) {
        if (tradingPair.equalsIgnoreCase("BTCUSDT")) {
            return binancePriceService;
        } else if (tradingPair.equalsIgnoreCase("ETHUSDT")) {
            return houbiPriceService;
        } else {
            throw new IllegalArgumentException("Trading pair not supported: " + tradingPair);
        }
    }
}
