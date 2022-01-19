package com.example.teachersystem.service.Impl;

import com.example.teachersystem.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     *
     * @param to
     * @param subject
     * @param content
     * 发送Html文件
     */
    @Override
    public void sendHtmlEmail(String to, String subject, String content) {
        MimeMessage message=mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
        }catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(message);
        log.info("邮件发送成功: from[{}], to[{}], subject[{}], content[{}]", from, to, subject, content);
    }
}
