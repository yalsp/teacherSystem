package com.example.teachersystem.domain.entity;


import com.example.teachersystem.domain.enums.TokenTypeEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_token")
public class UserTokenEntity {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="token")
    private String token;

    @Column(name="email")
    private String email;

    @Column(name="action")
    private Boolean action;

    @Column(name="type")
    private TokenTypeEnum type;
}
