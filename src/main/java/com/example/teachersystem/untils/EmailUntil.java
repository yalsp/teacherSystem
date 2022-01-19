package com.example.teachersystem.untils;

import com.example.teachersystem.dao.TokenRepository;
import com.example.teachersystem.domain.entity.UserTokenEntity;
import com.example.teachersystem.domain.enums.TokenTypeEnum;
import com.example.teachersystem.service.EmailService;
import com.example.teachersystem.service.TokenService;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EmailUntil {
    public static void sendEmail(TokenRepository tokenRepository, EmailService emailService, TokenService tokenService, String email,String contents,String subject){
        try {
            ExpiringMap<String, String> map = ExpiringMap.builder().expiration(5000, TimeUnit.MILLISECONDS)
                    .expirationPolicy(ExpirationPolicy.CREATED)
                    .build();
            String token= UUID.randomUUID().toString();
            map.put("key",token);
            tokenService.actionToken(email);
            UserTokenEntity userTokenEntity=tokenService.addToken(email,map.get("key"),TokenTypeEnum.FOUND);
            String content=String.format(contents,5,token);
            emailService.sendHtmlEmail(email,subject,content);
            //等待5分钟
            Thread.sleep(1000 * 300);
            tokenRepository.delete(userTokenEntity);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
