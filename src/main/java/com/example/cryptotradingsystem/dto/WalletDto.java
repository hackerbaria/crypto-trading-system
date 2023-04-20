package com.example.cryptotradingsystem.dto;

import com.example.cryptotradingsystem.model.CryptoCurrency;

import java.math.BigDecimal;
import java.util.Map;

public class WalletDto {
    private Map<String, BigDecimal> balances;

    public WalletDto() {
    }

    public WalletDto(Map<String, BigDecimal> balances) {
        this.balances = balances;
    }

    public Map<String, BigDecimal> getBalances() {
        return balances;
    }

    public void setBalances(Map<String, BigDecimal> balances) {
        this.balances = balances;
    }
}
