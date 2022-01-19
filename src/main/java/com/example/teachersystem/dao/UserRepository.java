package com.example.teachersystem.dao;

import com.example.teachersystem.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Long>{

    UserEntity findUserEntityByUsernameAndPassword(String username,String password);

    UserEntity findUserEntityByUsername(String username);

    UserEntity findUserEntityByEmail(String email);



}
