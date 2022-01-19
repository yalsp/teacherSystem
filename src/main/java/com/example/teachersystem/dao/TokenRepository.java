package com.example.teachersystem.dao;

import com.example.teachersystem.domain.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<UserTokenEntity,Long> {

    UserTokenEntity findByEmail(String email);

    UserTokenEntity findByEmailAndToken(String email,String token);

}
