package com.example.cryptotradingsystem.repository;

import com.example.cryptotradingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    <Optional> User findByUserId(Long userId);

    <Optional> User findByName(String name);

}