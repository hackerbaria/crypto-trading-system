package com.example.cryptotradingsystem.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "bid_price")
    private BigDecimal bidPrice;

    @Column(name = "ask_price")
    private BigDecimal askPrice;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
