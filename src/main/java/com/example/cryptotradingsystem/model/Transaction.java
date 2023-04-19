package com.example.cryptotradingsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "trading_pair", nullable = false)
    private String tradingPair;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
