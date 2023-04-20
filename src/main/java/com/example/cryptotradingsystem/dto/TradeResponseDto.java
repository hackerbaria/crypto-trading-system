package com.example.cryptotradingsystem.dto;

import java.math.BigDecimal;

public class TradeResponseDto {
    private Long tradeId;
    private String result;
    private String symbol;
    private String tradeType;
    private BigDecimal price;
    private BigDecimal amount;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TradeResponseDto(String result, String symbol, String tradeType, BigDecimal price, BigDecimal amount) {
        this.result = result;
        this.symbol = symbol;
        this.tradeType = tradeType;
        this.price = price;
        this.amount = amount;
    }

    public TradeResponseDto(String status, TransactionDto transaction) {
        this.result = status;
        this.symbol = transaction.getSymbol();
        this.tradeType = transaction.getTransactionType();
        this.price = transaction.getPrice();
        this.amount = transaction.getAmount();
    }
}
