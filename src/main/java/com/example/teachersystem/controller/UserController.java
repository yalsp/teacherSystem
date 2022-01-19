package com.example.teachersystem.controller;

import com.example.teachersystem.domain.dto.RegisterDTO;
import com.example.teachersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Email;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
  
    @PostMapping(value="register")
    public ResponseEntity<Void> sendRegisterEmail(@Email String email){
        userService.sendRegisterEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="register1")
    public ResponseEntity<Void> addUser(RegisterDTO registerDTO){
        userService.addUser(registerDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="login")
    public ResponseEntity<Void> loginUser(String username,String password){
        userService.login(username,password);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="found/sendEmail")
    public ResponseEntity<Void> sendFoundEmail(@Email String email){
        userService.sendFoundEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="found/pass")
    public ResponseEntity<Void> updatePas(@Email String email,String password,String token){
        userService.updatePassword(password,email,token);
        return ResponseEntity.ok().build();
    }

}
