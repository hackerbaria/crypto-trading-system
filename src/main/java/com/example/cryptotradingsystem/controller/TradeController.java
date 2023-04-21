package com.example.cryptotradingsystem.controller;


import com.example.cryptotradingsystem.dto.TradeRequestDto;
import com.example.cryptotradingsystem.dto.TradeResponseDto;
import com.example.cryptotradingsystem.dto.TransactionDto;
import com.example.cryptotradingsystem.service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }



    // Create an api which allows users to trade based on the latest best aggregated
    //price.
    @PostMapping("/execute")
    public ResponseEntity<TradeResponseDto> trade(@RequestBody TradeRequestDto tradeRequestDto) {
        TradeResponseDto tradeResponseDto = tradeService.trade(tradeRequestDto);
        return new ResponseEntity<>(tradeResponseDto, HttpStatus.OK);
    }

    // Create an api to retrieve the user trading history

    @GetMapping("/history")
    public ResponseEntity<List<TransactionDto>> getTradeHistory() {
        List<TransactionDto> tradeResponseDtoList = tradeService.getTradeHistory();
        return new ResponseEntity<>(tradeResponseDtoList, HttpStatus.OK);
    }
}
