package com.example.cryptotradingsystem.dto;

import java.math.BigDecimal;

public class TradeRequestDto {

    private String symbol;
    private String tradeType;
    private BigDecimal quantity;


    public TradeRequestDto(String symbol, String tradeType, BigDecimal quantity) {
        this.symbol = symbol;
        this.tradeType = tradeType;
        this.quantity = quantity;
    }

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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
