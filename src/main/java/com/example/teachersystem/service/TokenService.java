package com.example.teachersystem.service;

import com.example.teachersystem.domain.entity.UserTokenEntity;
import com.example.teachersystem.domain.enums.TokenTypeEnum;

public interface TokenService {
    /**
     * 获取token，添加到数据库中
     * @param email
     * @param token
     * @return
     */
    UserTokenEntity addToken(String email, String token, TokenTypeEnum type);

    /**
     * 检查token是否有效
     * @param email
     * @param token
     * @return
     */
    boolean equalToken(String email,String token);

    /**
     * 使token无效
     * @param email
     */
    void actionToken(String email);

}
