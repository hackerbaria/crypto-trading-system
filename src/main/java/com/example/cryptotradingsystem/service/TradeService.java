package com.example.cryptotradingsystem.service;

import com.example.cryptotradingsystem.dto.TradeRequestDto;
import com.example.cryptotradingsystem.dto.TradeResponseDto;
import com.example.cryptotradingsystem.dto.TransactionDto;

import java.util.List;

public interface TradeService {

    TradeResponseDto trade(TradeRequestDto tradeRequestDto);

    List<TransactionDto> getTradeHistory();
}
