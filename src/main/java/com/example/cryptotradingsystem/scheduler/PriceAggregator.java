package com.example.cryptotradingsystem.scheduler;

import com.example.cryptotradingsystem.dto.AggregatedPriceDto;
import com.example.cryptotradingsystem.dto.PriceDto;
import com.example.cryptotradingsystem.model.AggregatedPrice;
import com.example.cryptotradingsystem.repository.AggregatedPriceRepository;
import com.example.cryptotradingsystem.service.BinancePriceService;
import com.example.cryptotradingsystem.service.HoubiPriceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PriceAggregator {

    private final BinancePriceService binancePriceService;
    private final HoubiPriceService houbiPriceService;
    private final AggregatedPriceRepository aggregatedPriceRepository;

    public PriceAggregator(BinancePriceService binancePriceService, HoubiPriceService houbiPriceService, AggregatedPriceRepository aggregatedPriceRepository) {
        this.binancePriceService = binancePriceService;
        this.houbiPriceService = houbiPriceService;
        this.aggregatedPriceRepository = aggregatedPriceRepository;
    }

    @Scheduled(fixedDelay = 10000) // run every 10 seconds
    public void aggregatePrices() {
        // Get the latest prices from Binance and Huobi APIs
        List<PriceDto> binanceBidPrice = binancePriceService.getLatestPrice();
        List<PriceDto> houbiBidPrice = houbiPriceService.getLatestPrice();

        List<PriceDto> mergedList = Stream.concat(binanceBidPrice.stream(), houbiBidPrice.stream())
                .collect(Collectors.toList());

        List<String> tradingPairs = Arrays.asList("ETHUSDT", "BTCUSDT");

        List<AggregatedPrice> aggregatedPrices = tradingPairs.stream()
                .map(tradingPair -> {
                    AggregatedPrice aggregatedPrice = new AggregatedPrice();
                    aggregatedPrice.setTradingPair(tradingPair);

                    Optional<PriceDto> maxBidPrice = mergedList.stream()
                            .filter(price -> price.getSymbol().equalsIgnoreCase(tradingPair))
                            .max(Comparator.comparing(PriceDto::getBidPrice));
                    maxBidPrice.ifPresent(priceDto -> {
                        aggregatedPrice.setBestBidPrice(priceDto.getBidPrice());
                        aggregatedPrice.setBidPriceSourceExchange(priceDto.getSourceExchange());
                    });

                    Optional<PriceDto> minAskPrice = mergedList.stream()
                            .filter(price -> price.getSymbol().equalsIgnoreCase(tradingPair))
                            .min(Comparator.comparing(PriceDto::getAskPrice));
                    minAskPrice.ifPresent(priceDto -> {
                        aggregatedPrice.setBestAskPrice(priceDto.getAskPrice());
                        aggregatedPrice.setAskPriceSourceExchange(priceDto.getSourceExchange());
                    });
                    return aggregatedPrice;
                })
                .collect(Collectors.toList());

        // Save the best prices to the database
        aggregatedPriceRepository.saveAll(aggregatedPrices);
    }




}
