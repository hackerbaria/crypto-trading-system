package com.example.cryptotradingsystem.service;

import com.example.cryptotradingsystem.dto.PriceDto;
import com.example.cryptotradingsystem.dto.TradeRequestDto;
import com.example.cryptotradingsystem.dto.TradeResponseDto;

import java.util.List;

public interface PriceService {
    List<PriceDto> getLatestPrice();
    TradeResponseDto tradeBasedOnLatestPrice(TradeRequestDto tradeRequestDto);
}
