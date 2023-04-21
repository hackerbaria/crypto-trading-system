package com.example.cryptotradingsystem.service;


import com.example.cryptotradingsystem.dto.WalletDto;
import com.example.cryptotradingsystem.exception.InsufficientBalanceException;
import com.example.cryptotradingsystem.exception.WalletNotFoundException;
import com.example.cryptotradingsystem.model.Wallet;
import com.example.cryptotradingsystem.repository.UserRepository;
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

    private final UserService userService;

    public WalletService(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }


    public Wallet getWallet(String symbol) {
        Optional<Wallet> walletOptional = walletRepository.findByCryptoCurrencySymbol(symbol);
        if (walletOptional.isEmpty()) {
            throw new WalletNotFoundException("Wallet not found for symbol " + symbol);
        }
        return walletOptional.get();
    }

    public void debitWallet(BigDecimal amount) {
        BigDecimal oldBalance = userService.getBalance();
        if (oldBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in wallet");
        }
        BigDecimal newBalance = oldBalance.subtract(amount);
        userService.updateBalance(newBalance);
    }



    public void creditWallet(BigDecimal amount) {
        BigDecimal oldBalance = userService.getBalance();
        BigDecimal newBalance = oldBalance.add(amount);
        userService.updateBalance(newBalance);

    }

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }


}
