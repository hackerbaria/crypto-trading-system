package com.example.cryptotradingsystem.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_currency_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    @Column(nullable = false)
    private BigDecimal balance;

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
