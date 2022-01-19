package com.example.teachersystem.service.Impl;

import com.example.teachersystem.dao.TokenRepository;
import com.example.teachersystem.domain.entity.UserTokenEntity;
import com.example.teachersystem.domain.enums.TokenTypeEnum;
import com.example.teachersystem.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public UserTokenEntity addToken(String email, String token, TokenTypeEnum type) {
       UserTokenEntity userTokenEntity=new UserTokenEntity();
       userTokenEntity.setToken(token);
       userTokenEntity.setEmail(email);
       userTokenEntity.setAction(true);
       userTokenEntity.setType(type);
        tokenRepository.save(userTokenEntity);
       return userTokenEntity;
    }

    @Override
    public boolean equalToken(String email, String token) {
        UserTokenEntity userTokenEntity=tokenRepository.findByEmailAndToken(email,token);
        return userTokenEntity.getAction();
    }

    @Override
    public void actionToken(String email) {
        UserTokenEntity userTokenEntity=tokenRepository.findByEmail(email);
        userTokenEntity.setAction(false);
        tokenRepository.save(userTokenEntity);
    }


}
