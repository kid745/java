package com.devcamp.thongnh.realestate.Service.User.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.User.Token;
import com.devcamp.thongnh.realestate.Respository.User.TokenRepository;
import com.devcamp.thongnh.realestate.Service.User.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

}
