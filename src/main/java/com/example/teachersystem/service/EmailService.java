package com.example.teachersystem.service;

public interface EmailService {
    /**
     * 发送Html文件
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlEmail(String to, String subject, String content);
}
