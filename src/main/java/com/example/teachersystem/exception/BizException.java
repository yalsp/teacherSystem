package com.example.teachersystem.exception;

import com.example.teachersystem.exception.mes.BizMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizException extends RuntimeException{
    private String mes;

    public BizException(BizMessage bizMessage) {
        super(bizMessage.getMessage());
        this.mes = bizMessage.getMessage();
    }
}
