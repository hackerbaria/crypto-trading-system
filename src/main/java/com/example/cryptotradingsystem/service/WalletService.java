package com.example.cryptotradingsystem.service;


import com.example.cryptotradingsystem.dto.WalletDto;
import com.example.cryptotradingsystem.exception.InsufficientBalanceException;
import com.example.cryptotradingsystem.exception.WalletNotFoundException;
import com.example.cryptotradingsystem.model.Wallet;
import com.example.cryptotradingsystem.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public WalletDto getWalletBalance() {
        List<Wallet> wallets = walletRepository.findAll();
        Map<String, BigDecimal> balances = new HashMap<>();
        for (Wallet wallet : wallets) {
            String symbol = wallet.getCryptoCurrency().getSymbol();
            BigDecimal balance = wallet.getBalance();
            balances.put(symbol, balance);
        }
        return new WalletDto(balances);
    }

    public Wallet getWallet(String symbol) {
        Optional<Wallet> walletOptional = walletRepository.findByCryptoCurrencySymbol(symbol);
        if (walletOptional.isEmpty()) {
            throw new WalletNotFoundException("Wallet not found for symbol " + symbol);
        }
        return walletOptional.get();
    }

    public void debitWallet(Wallet wallet, BigDecimal amount) {
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in wallet");
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
    }

    public void creditWallet(Wallet wallet, BigDecimal amount) {
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
    }
}
