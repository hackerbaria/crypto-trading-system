package com.example.cryptotradingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_currency_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TradeType type;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "timestamp", nullable = false)
    private Long timestamp;

    public Trade() {}

    public Trade(User user, CryptoCurrency cryptoCurrency, TradeType type, BigDecimal price, BigDecimal amount, Long timestamp) {
        this.user = user;
        this.cryptoCurrency = cryptoCurrency;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
