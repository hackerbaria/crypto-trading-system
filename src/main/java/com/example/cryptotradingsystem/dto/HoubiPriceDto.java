package com.example.cryptotradingsystem.dto;

import java.math.BigDecimal;

public class HoubiPriceDto {
    private String symbol;
    private BigDecimal bid;
    private BigDecimal ask;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }
}
