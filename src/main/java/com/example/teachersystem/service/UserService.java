package com.example.teachersystem.service;

import com.example.teachersystem.domain.dto.RegisterDTO;
import com.example.teachersystem.domain.entity.UserEntity;

public interface UserService {

    /**
     * 添加用户信息
     * @param registerDTO
     * @return
     */
    UserEntity addUser(RegisterDTO registerDTO);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    UserEntity login(String username,String password);

    /**
     * 发送注册Html邮箱,获取令牌
     * @param email
     */
    void sendRegisterEmail(String email);

    /**
     * 发送找回密码Html邮箱，获取令牌
     * @param email
     */
    void sendFoundEmail(String email);

    /**
     * 修改密码
     * @param password
     * @param email
     * @param token
     */
    void updatePassword(String password,String email,String token);

}
