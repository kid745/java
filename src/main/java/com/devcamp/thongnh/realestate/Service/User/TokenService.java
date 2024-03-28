package com.devcamp.thongnh.realestate.Service.User;

import com.devcamp.thongnh.realestate.Model.User.Token;

public interface TokenService {
    Token saveToken(Token token);

    Token findByToken(String token);
}
