package com.devcamp.thongnh.realestate.Respository.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.User.Token;


public interface TokenRepository extends JpaRepository<Token,Long> {
    Token findByToken(String token);
    
}
