package com.example.cryptotradingsystem.controller;


import com.example.cryptotradingsystem.dto.TradeRequestDto;
import com.example.cryptotradingsystem.dto.TradeResponseDto;
import com.example.cryptotradingsystem.dto.WalletDto;
import com.example.cryptotradingsystem.model.Trade;
import com.example.cryptotradingsystem.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }



    @PostMapping("/buy")
    public ResponseEntity<TradeResponseDto> buy(@RequestBody TradeRequestDto tradeRequestDto) {
        TradeResponseDto tradeResponseDto = tradeService.buy(tradeRequestDto);
        return new ResponseEntity<>(tradeResponseDto, HttpStatus.OK);
    }

    @PostMapping("/sell")
    public ResponseEntity<TradeResponseDto> sell(@RequestBody TradeRequestDto tradeRequestDto) {
        TradeResponseDto tradeResponseDto = tradeService.sell(tradeRequestDto);
        return new ResponseEntity<>(tradeResponseDto, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TradeResponseDto>> getTradeHistory() {
        List<TradeResponseDto> tradeResponseDtoList = tradeService.getTradeHistory();
        return new ResponseEntity<>(tradeResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<WalletDto> getWalletBalance() {
        WalletDto walletDto = tradeService.getWalletBalance();
        return new ResponseEntity<>(walletDto, HttpStatus.OK);
    }
}
