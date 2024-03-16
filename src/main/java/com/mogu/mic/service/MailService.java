package com.mogu.mic.service;

import java.io.IOException;

public interface MailService {


    /**
     * 发送简单邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject, String content);

    // 发送html邮件
    void sendHtmlMail(String to, String subject, String path) throws IOException;
}
