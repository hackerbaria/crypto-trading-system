package com.example.cryptotradingsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDto {
    private Long id;
    private String symbol;
    private String transactionType;
    private BigDecimal qty;
    private BigDecimal price;
    private String sourceChange;
    private String result;
    private LocalDateTime timestamp;

    public TransactionDto(String symbol, String transactionType, BigDecimal qty, BigDecimal price, String sourceChange, String result, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.transactionType = transactionType;
        this.qty = qty;
        this.price = price;
        this.sourceChange = sourceChange;
        this.result = result;
        this.timestamp = timestamp;
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

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSourceChange() {
        return sourceChange;
    }

    public void setSourceChange(String sourceChange) {
        this.sourceChange = sourceChange;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
