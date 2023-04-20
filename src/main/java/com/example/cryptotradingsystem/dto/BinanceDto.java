package com.example.cryptotradingsystem.dto;

import java.math.BigDecimal;

public class BinanceDto {
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal bidQty;
    private BigDecimal askPrice;
    private BigDecimal askQty;

    public BinanceDto() {
    }

    public BinanceDto(String symbol, BigDecimal bidPrice, BigDecimal askPrice) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
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
}
