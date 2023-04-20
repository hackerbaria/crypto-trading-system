package com.example.cryptotradingsystem.dto;

import java.math.BigDecimal;

public class PriceDto {
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal bidQty;
    private BigDecimal askPrice;
    private BigDecimal askQty;
    private String sourceExchange;


    public PriceDto() {
    }

    public PriceDto(String symbol, BigDecimal bidPrice, BigDecimal bidQty, BigDecimal askPrice, BigDecimal askQty, String sourceExchange) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
        this.sourceExchange = sourceExchange;
    }



    public PriceDto(String symbol, BigDecimal bidPrice, BigDecimal askPrice, String sourceExchange) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.sourceExchange = sourceExchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
    }

    public BigDecimal getBidQty() {
        return bidQty;
    }

    public void setBidQty(BigDecimal bidQty) {
        this.bidQty = bidQty;
    }

    public BigDecimal getAskQty() {
        return askQty;
    }

    public void setAskQty(BigDecimal askQty) {
        this.askQty = askQty;
    }

    public String getSourceExchange() {
        return sourceExchange;
    }

    public void setSourceExchange(String sourceExchange) {
        this.sourceExchange = sourceExchange;
    }
}
