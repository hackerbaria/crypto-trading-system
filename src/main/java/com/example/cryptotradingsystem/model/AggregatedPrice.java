package com.example.cryptotradingsystem.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "aggregated_price")
public class AggregatedPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trading_pair", nullable = false)
    private String tradingPair;

    @Column(name = "best_bid_price", nullable = false)
    private BigDecimal bestBidPrice;

    @Column(name = "bid_qty", nullable = false)
    private BigDecimal bidQty;

    @Column(name = "best_ask_price", nullable = false)
    private BigDecimal bestAskPrice;

    @Column(name = "ask_qty", nullable = false)
    private BigDecimal askQty;

    @Column(name = "bid_price_source_exchange", nullable = false)
    private String bidPriceSourceExchange;

    @Column(name = "ask_price_source_exchange", nullable = false)
    private String askPriceSourceExchange;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @PrePersist
    public void setLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }

    public AggregatedPrice(BigDecimal bestBidPrice, BigDecimal bestAskPrice) {
        this.bestBidPrice = bestBidPrice;
        this.bestAskPrice = bestAskPrice;
        this.lastUpdated = LocalDateTime.now();
    }

    public AggregatedPrice() {
    }

    public AggregatedPrice(String tradingPair, BigDecimal bestBidPrice, BigDecimal bidQty, BigDecimal bestAskPrice, BigDecimal askQty, String bidPriceSourceExchange, String askPriceSourceExchange) {
        this.tradingPair = tradingPair;
        this.bestBidPrice = bestBidPrice;
        this.bestAskPrice = bestAskPrice;
        this.bidPriceSourceExchange = bidPriceSourceExchange;
        this.askPriceSourceExchange = askPriceSourceExchange;
        this.bidQty = bidQty;
        this.askQty = askQty;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getTradingPair() {
        return tradingPair;
    }

    public void setTradingPair(String tradingPair) {
        this.tradingPair = tradingPair;
    }

    public BigDecimal getBestBidPrice() {
        return bestBidPrice;
    }

    public void setBestBidPrice(BigDecimal bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }

    public BigDecimal getBestAskPrice() {
        return bestAskPrice;
    }

    public void setBestAskPrice(BigDecimal bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }

    public String getBidPriceSourceExchange() {
        return bidPriceSourceExchange;
    }

    public void setBidPriceSourceExchange(String bidPriceSourceExchange) {
        this.bidPriceSourceExchange = bidPriceSourceExchange;
    }

    public String getAskPriceSourceExchange() {
        return askPriceSourceExchange;
    }

    public void setAskPriceSourceExchange(String askPriceSourceExchange) {
        this.askPriceSourceExchange = askPriceSourceExchange;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public BigDecimal getBidQty() {
        return bidQty;
    }

    public void setBidQty(BigDecimal bidQty) {
        this.bidQty = bidQty;
    }

    public BigDecimal getAskQty() {
        return askQty;
    }

    public void setAskQty(BigDecimal askQty) {
        this.askQty = askQty;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
