package com.example.teachersystem.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenTypeEnum {

    REGISTER(1,"注册令牌"),

    FOUND(2,"找回密码令牌");

    private final Integer type;

    private final String description;
}
