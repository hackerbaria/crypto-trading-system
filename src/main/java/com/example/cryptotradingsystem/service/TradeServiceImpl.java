package com.example.cryptotradingsystem.service;

import com.example.cryptotradingsystem.dto.TradeRequestDto;
import com.example.cryptotradingsystem.dto.TradeResponseDto;
import com.example.cryptotradingsystem.dto.TransactionDto;
import com.example.cryptotradingsystem.dto.WalletDto;
import com.example.cryptotradingsystem.exception.InsufficientBalanceException;
import com.example.cryptotradingsystem.model.Trade;
import com.example.cryptotradingsystem.model.TradeType;
import com.example.cryptotradingsystem.model.Wallet;
import com.example.cryptotradingsystem.repository.TradeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;
    private final WalletService walletService;
    private final PriceServiceFactory priceServiceFactory;

    public TradeServiceImpl(TradeRepository tradeRepository, WalletService walletService, PriceServiceFactory priceServiceFactory) {
        this.tradeRepository = tradeRepository;
        this.walletService = walletService;
        this.priceServiceFactory = priceServiceFactory;
    }

    @Override
    public TradeResponseDto buy(TradeRequestDto tradeRequestDto) {
        //BigDecimal latestPrice = priceServiceFactory.getPriceService(tradeRequestDto.getTradingPair().getSymbol()).getLatestPrice().;
        BigDecimal latestPrice = null;
        BigDecimal quantity = tradeRequestDto.getAmount().divide(latestPrice, RoundingMode.HALF_UP);
        Wallet wallet = walletService.getWallet(tradeRequestDto.getTradingPair().getSymbol());
        if (wallet.getBalance().compareTo(quantity) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for buy order");
        }
        BigDecimal amount = latestPrice.multiply(quantity);
        TransactionDto transactionDto = new TransactionDto(tradeRequestDto.getTradingPair().getSymbol(), TradeType.BUY.name(), latestPrice, amount);
        Trade trade = new Trade();
        trade.setType(TradeType.BUY);
        // TODO set more properties

        tradeRepository.save(trade);
        walletService.debitWallet(wallet, quantity);
        return new TradeResponseDto("success", transactionDto);
    }

    @Override
    public TradeResponseDto sell(TradeRequestDto tradeRequestDto) {
        //BigDecimal latestPrice = priceServiceFactory.getPriceService(tradeRequestDto.getTradingPair().getSymbol()).getLatestPrice().getPrice();
        BigDecimal latestPrice = null;
        Wallet wallet = walletService.getWallet(tradeRequestDto.getTradingPair().getSymbol());
        if (wallet.getBalance().compareTo(tradeRequestDto.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for sell order");
        }
        BigDecimal amount = latestPrice.multiply(tradeRequestDto.getAmount());
        TransactionDto transactionDto = new TransactionDto(tradeRequestDto.getTradingPair().getSymbol(), TradeType.SELL.name(), latestPrice, amount);
        Trade trade = new Trade();
        trade.setType(TradeType.SELL);
        // TODO set more properties

        tradeRepository.save(trade);
        walletService.creditWallet(wallet, tradeRequestDto.getAmount());
        return new TradeResponseDto("success", transactionDto);
    }

    @Override
    public List<TradeResponseDto> getTradeHistory() {
        List<Trade> trades = tradeRepository.findAll();
        List<TransactionDto> transactions = trades.stream()
                .map(trade -> new TransactionDto(
                        trade.getCryptoCurrency().getSymbol(),
                        trade.getType().name(),
                        trade.getPrice(),
                        trade.getAmount()
                ))
                .collect(Collectors.toList());
        List<TradeResponseDto> tradeResponseDtos = new ArrayList<>();
        for (TransactionDto transaction : transactions) {
            tradeResponseDtos.add(new TradeResponseDto("success", transaction));
        }
        return tradeResponseDtos;
    }

    @Override
    public WalletDto getWalletBalance() {
        return walletService.getWalletBalance();
    }
}
