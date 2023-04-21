package com.example.cryptotradingsystem.service;

import com.example.cryptotradingsystem.dto.TradeRequestDto;
import com.example.cryptotradingsystem.dto.TradeResponseDto;
import com.example.cryptotradingsystem.dto.TransactionDto;
import com.example.cryptotradingsystem.exception.InsufficientBalanceException;
import com.example.cryptotradingsystem.model.*;
import com.example.cryptotradingsystem.repository.CryptoCurrencyRepository;
import com.example.cryptotradingsystem.repository.TradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;
    private final WalletService walletService;
    private final AggregatedPriceService aggregatedPriceService;
    private final UserService userService;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;


    public TradeServiceImpl(TradeRepository tradeRepository, WalletService walletService, AggregatedPriceService aggregatedPriceService,
                            UserService userService, CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.tradeRepository = tradeRepository;
        this.walletService = walletService;
        this.aggregatedPriceService = aggregatedPriceService;
        this.userService = userService;
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }

    @Override
    public TradeResponseDto trade(TradeRequestDto tradeRequestDto) {
        if(TradeType.BUY.name().equalsIgnoreCase(tradeRequestDto.getTradeType())) {
            return buy(tradeRequestDto);
        } else {
            return sell(tradeRequestDto);
        }
    }


    @Transactional
    public TradeResponseDto buy(TradeRequestDto tradeRequestDto) {
        List<AggregatedPrice> aggregatedPrices = aggregatedPriceService.getLatestPrices();
        Optional<AggregatedPrice> buyAggregatedPrice = aggregatedPrices.stream()
                .filter(ap -> ap.getTradingPair().equals(tradeRequestDto.getSymbol()))
                .findFirst();
        if(buyAggregatedPrice.isPresent()) {
            BigDecimal balance = userService.getBalance();
            AggregatedPrice aggregatedPrice = buyAggregatedPrice.get();

            // main activity
            BigDecimal latestPrice = aggregatedPrice.getBestAskPrice();

            BigDecimal amount = latestPrice.multiply(tradeRequestDto.getQuantity());

            // check wallet and qty available from market.
            if(balance.compareTo(BigDecimal.ZERO) < 0 || balance.compareTo(amount) < 0) {
                throw new InsufficientBalanceException("Insufficient balance for buy order");
            }

            walletService.debitWallet(amount);

            // save Trade
            Trade trade = new Trade();
            CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBySymbol(tradeRequestDto.getSymbol()).get();
            trade.setCryptoCurrency(cryptoCurrency);
            trade.setType(TradeType.BUY);
            trade.setQty(tradeRequestDto.getQuantity());
            trade.setPrice(latestPrice);
            trade.setSourceExchange(aggregatedPrice.getAskPriceSourceExchange());
            trade.setResult("success");
            tradeRepository.save(trade);

            Wallet wallet = walletService.getWallet(tradeRequestDto.getSymbol());
            wallet.setQty(wallet.getQty().add(tradeRequestDto.getQuantity()));
            walletService.save(wallet);

            return new TradeResponseDto("success", trade);



        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public TradeResponseDto sell(TradeRequestDto tradeRequestDto) {
        List<AggregatedPrice> aggregatedPrices = aggregatedPriceService.getLatestPrices();
        Optional<AggregatedPrice> sellAggregatedPrice = aggregatedPrices.stream()
                .filter(ap -> ap.getTradingPair().equals(tradeRequestDto.getSymbol()))
                .findFirst();
        if(sellAggregatedPrice.isPresent()) {
            BigDecimal balance = userService.getBalance();
            AggregatedPrice aggregatedPrice = sellAggregatedPrice.get();

            // main activity
            BigDecimal latestPrice = aggregatedPrice.getBestBidPrice();

            BigDecimal amount = latestPrice.multiply(tradeRequestDto.getQuantity());

            Wallet wallet = walletService.getWallet(tradeRequestDto.getSymbol());



            // check wallet and qty available from market.
            if(wallet.getQty().compareTo(tradeRequestDto.getQuantity()) < 0) {
                throw new InsufficientBalanceException("Insufficient qty for sell order, check wallet again");
            }

            walletService.creditWallet(amount);

            // save Trade
            Trade trade = new Trade();
            CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBySymbol(tradeRequestDto.getSymbol()).get();
            trade.setCryptoCurrency(cryptoCurrency);
            trade.setType(TradeType.SELL);
            trade.setQty(tradeRequestDto.getQuantity());
            trade.setPrice(latestPrice);
            trade.setSourceExchange(aggregatedPrice.getAskPriceSourceExchange());
            trade.setResult("success");
            tradeRepository.save(trade);

             wallet.setQty(wallet.getQty().subtract(tradeRequestDto.getQuantity()));
            walletService.save(wallet);

            return new TradeResponseDto("success", trade);



        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<TransactionDto> getTradeHistory() {
        List<Trade> trades = tradeRepository.findAll();
        List<TransactionDto> transactions = trades.stream()
                .map(trade -> new TransactionDto(
                        trade.getCryptoCurrency().getSymbol(),
                        trade.getType().name(),
                        trade.getQty(),
                        trade.getPrice(),
                        trade.getSourceExchange(),
                        trade.getResult(),
                        trade.getTimestamp()
                ))
                .collect(Collectors.toList());
        return transactions;
    }


}
