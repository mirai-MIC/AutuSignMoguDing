package com.mogu.mic.service.impl;

import com.mogu.mic.service.MailService;
import com.mogu.mic.util.AutoSignUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.service.Impl
 * @Author: mi
 * @CreateTime: 2023/10/16 19:18
 * @Description:
 * @Version: 1.0
 */

@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        var message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        mailSender.send(message);
        log.info("%s--->%s is success".formatted(from, to));
    }

    @SneakyThrows
    @Override
    public void sendHtmlMail(String to, String subject, String path) {
        var message = new SimpleMailMessage();
        String htmlString = AutoSignUtil.toHtmlString(new File(path));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(htmlString);
        message.setFrom(from);
        mailSender.send(message);
        log.info("%s--->%s is ".formatted(from, to));
    }


}
