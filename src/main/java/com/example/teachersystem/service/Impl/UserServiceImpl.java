package com.example.teachersystem.service.Impl;
import com.example.teachersystem.consts.EmailConst;
import com.example.teachersystem.dao.TokenRepository;
import com.example.teachersystem.dao.UserRepository;
import com.example.teachersystem.domain.dto.RegisterDTO;
import com.example.teachersystem.domain.entity.UserEntity;
import com.example.teachersystem.domain.entity.UserTokenEntity;
import com.example.teachersystem.domain.enums.TokenTypeEnum;
import com.example.teachersystem.exception.BizException;
import com.example.teachersystem.exception.mes.BizMessage;
import com.example.teachersystem.service.EmailService;
import com.example.teachersystem.service.TokenService;
import com.example.teachersystem.service.UserService;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public UserEntity addUser(RegisterDTO registerDTO) {

        if(userRepository.findUserEntityByUsername(registerDTO.getUsername()) != null){
            throw new BizException(BizMessage.USER_EXIST);
        }

        if(userRepository.findUserEntityByEmail(registerDTO.getEmail()) != null){
            throw new BizException(BizMessage.EMAIL_EXISTS);
        }

        if(!tokenService.equalToken(registerDTO.getEmail(),registerDTO.getToken())){
            throw new BizException(BizMessage.TOKEN_ERROR);
        }

        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(registerDTO.getUsername());
        userEntity.setPassword(registerDTO.getPassword());
        userEntity.setTime(new Date());
        userEntity.setGender(registerDTO.getGender());
        userRepository.save(userEntity);

        return null;
    }

    @Override
    public UserEntity login(String username, String password) {
        if(userRepository.findUserEntityByUsernameAndPassword(username,password)==null){
            throw new BizException(BizMessage.USER_PSA_NOT_EXISTS);
        }
        return null;
    }

    @Override
    public void sendRegisterEmail(String email) {

        try {

            ExpiringMap<String, String> map = ExpiringMap.builder().expiration(5000, TimeUnit.MILLISECONDS)
                    .expirationPolicy(ExpirationPolicy.CREATED)
                    .build();
            String token= UUID.randomUUID().toString();
            map.put("key",token);
            tokenService.actionToken(email);
            UserTokenEntity userTokenEntity=tokenService.addToken(email,map.get("key"),TokenTypeEnum.REGISTER);
            String content=String.format(EmailConst.USER_REGISTER_HTML_CONTENT, 5, token);
            emailService.sendHtmlEmail(email, EmailConst.USER_REGISTER_SUBJECT,content);
            //等待5分钟
            Thread.sleep(1000 * 300);
            tokenRepository.delete(userTokenEntity);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void sendFoundEmail(String email) {
        try {
            ExpiringMap<String, String> map = ExpiringMap.builder().expiration(5000, TimeUnit.MILLISECONDS)
                    .expirationPolicy(ExpirationPolicy.CREATED)
                    .build();
            String token= UUID.randomUUID().toString();
            map.put("key",token);
            tokenService.actionToken(email);
            UserTokenEntity userTokenEntity=tokenService.addToken(email,map.get("key"),TokenTypeEnum.FOUND);
            UserEntity userEntity=userRepository.findUserEntityByEmail(email);
            String content=String.format(EmailConst.RESET_PASSWD_HTML_CONTENT, userEntity.getUsername(), token);
            emailService.sendHtmlEmail(email, EmailConst.RESET_PASSWD_SUBJECT,content);
            //等待5分钟
            Thread.sleep(1000 * 300);
            tokenRepository.delete(userTokenEntity);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(String password,String email,String token) {

        if(!tokenService.equalToken(email,token)){
            throw new BizException(BizMessage.TOKEN_ERROR);
        }
        UserEntity userEntity= userRepository.findUserEntityByEmail(email);
        userEntity.setPassword(password);
        userRepository.save(userEntity);
        String content=String.format(EmailConst.RESET_PASSWD_SUCCESS_HTML_CONTENT,userEntity.getUsername(),userEntity.getPassword());
        emailService.sendHtmlEmail(userEntity.getEmail(),EmailConst.RESET_PASSWD_SUCCESS_SUBJECT,content);
    }


}
