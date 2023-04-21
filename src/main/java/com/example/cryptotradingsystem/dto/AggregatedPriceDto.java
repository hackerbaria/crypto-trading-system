package com.example.cryptotradingsystem.dto;

import java.math.BigDecimal;

public class AggregatedPriceDto {

    private String symbol;
    private BigDecimal maxBidPrice;
    private BigDecimal minAskPrice;
    private String maxBidPriceSource;
    private String minAskPriceSource;

    public AggregatedPriceDto() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getMaxBidPrice() {
        return maxBidPrice;
    }

    public void setMaxBidPrice(BigDecimal maxBidPrice) {
        this.maxBidPrice = maxBidPrice;
    }

    public BigDecimal getMinAskPrice() {
        return minAskPrice;
    }

    public void setMinAskPrice(BigDecimal minAskPrice) {
        this.minAskPrice = minAskPrice;
    }

    public String getMaxBidPriceSource() {
        return maxBidPriceSource;
    }

    public void setMaxBidPriceSource(String maxBidPriceSource) {
        this.maxBidPriceSource = maxBidPriceSource;
    }

    public String getMinAskPriceSource() {
        return minAskPriceSource;
    }

    public void setMinAskPriceSource(String minAskPriceSource) {
        this.minAskPriceSource = minAskPriceSource;
    }
}
