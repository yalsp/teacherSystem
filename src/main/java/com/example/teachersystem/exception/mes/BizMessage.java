package com.example.teachersystem.exception.mes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BizMessage  implements BaseMessage {
    USER_EXIST(5000,"用户已注册"),
    EMAIL_EXISTS(5001,"邮箱已注册"),
    USER_PSA_NOT_EXISTS(5001,"用户名或者密码错误"),
    TOKEN_ERROR(5002,"验证码错误");

    private final Integer code;

    private final String message;
}
