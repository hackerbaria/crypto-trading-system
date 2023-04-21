package com.example.cryptotradingsystem.dto;

import com.example.cryptotradingsystem.model.Trade;

import java.math.BigDecimal;

public class TradeResponseDto {
    private Long tradeId;
    private String result;
    private String symbol;
    private String tradeType;
    private BigDecimal qty;
    private BigDecimal price;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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


    public TradeResponseDto(String result, String symbol, String tradeType, BigDecimal qty, BigDecimal price) {
        this.result = result;
        this.symbol = symbol;
        this.tradeType = tradeType;
        this.qty = qty;
        this.price = price;
    }

    public TradeResponseDto(String status, Trade trade) {
        this.result = status;
        this.symbol = trade.getCryptoCurrency().getSymbol();
        this.tradeType = trade.getType().name();
        this.price = trade.getPrice();
        this.qty = trade.getQty();
        this.tradeId = trade.getId();
    }
}
