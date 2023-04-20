package com.example.cryptotradingsystem.dto;

import java.math.BigDecimal;

public class TransactionDto {
    private Long id;
    private String symbol;
    private String transactionType;
    private BigDecimal price;
    private BigDecimal amount;
    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionDto(String symbol, String transactionType, BigDecimal price, BigDecimal amount) {
        this.symbol = symbol;
        this.transactionType = transactionType;
        this.price = price;
        this.amount = amount;
    }
}
