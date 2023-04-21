package com.example.cryptotradingsystem.controller;


import com.example.cryptotradingsystem.dto.WalletDto;
import com.example.cryptotradingsystem.service.UserService;
import com.example.cryptotradingsystem.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final UserService userService;

    @Autowired
    public WalletController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getWalletBalance() {
        BigDecimal balance = userService.getBalance();
        return ResponseEntity.ok(balance);
    }
}
