package com.example.cryptotradingsystem.dto;

import com.example.cryptotradingsystem.model.CryptoCurrency;

import java.math.BigDecimal;

public class TradeRequestDto {

    private CryptoCurrency tradingPair;

    private String symbol;
    private String tradeType;
    private BigDecimal price;
    private BigDecimal amount;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CryptoCurrency getTradingPair() {
        return tradingPair;
    }

    public void setTradingPair(CryptoCurrency tradingPair) {
        this.tradingPair = tradingPair;
    }
}
