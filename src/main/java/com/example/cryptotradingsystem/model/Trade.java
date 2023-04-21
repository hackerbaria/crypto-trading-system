package com.example.cryptotradingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_currency_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TradeType type;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "qty", nullable = false)
    private BigDecimal qty;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "source_exchange", nullable = false)
    private String sourceExchange;


    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }

    public Trade() {}

    public Trade(TradeType type, BigDecimal price, BigDecimal qty, String result, String sourceExchange) {
        this.type = type;
        this.price = price;
        this.qty = qty;
        this.result = result;
        this.sourceExchange = sourceExchange;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public TradeType getType() {
        return type;
    }

    public void setType(TradeType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSourceExchange() {
        return sourceExchange;
    }

    public void setSourceExchange(String sourceExchange) {
        this.sourceExchange = sourceExchange;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }


}
