package com.example.cryptotradingsystem.controller;


import com.example.cryptotradingsystem.dto.WalletDto;
import com.example.cryptotradingsystem.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/balance")
    public ResponseEntity<WalletDto> getWalletBalance() {
        WalletDto walletDto = walletService.getWalletBalance();
        return ResponseEntity.ok(walletDto);
    }
}
