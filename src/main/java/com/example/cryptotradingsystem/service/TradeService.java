package com.example.cryptotradingsystem.service;

import com.example.cryptotradingsystem.dto.TradeRequestDto;
import com.example.cryptotradingsystem.dto.TradeResponseDto;
import com.example.cryptotradingsystem.dto.WalletDto;

import java.util.List;

public interface TradeService {
    TradeResponseDto buy(TradeRequestDto tradeRequestDto);

    TradeResponseDto sell(TradeRequestDto tradeRequestDto);

    List<TradeResponseDto> getTradeHistory();

    WalletDto getWalletBalance();
}
