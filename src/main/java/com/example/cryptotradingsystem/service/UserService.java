package com.example.cryptotradingsystem.service;


import com.example.cryptotradingsystem.exception.UserNotFoundException;
import com.example.cryptotradingsystem.model.User;
import com.example.cryptotradingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // in this case, we assume only 1 user in db for test purpose
    public BigDecimal getBalance() {
        User user = userRepository.findByUserId(1L);
        return user.getBalance();
    }

    public void updateBalance(BigDecimal balance) {
        User user = userRepository.findByUserId(1L);
        user.setBalance(balance);
        userRepository.save(user);

    }
}
