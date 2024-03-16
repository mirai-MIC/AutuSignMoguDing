package com.mogu.mic;

import com.mogu.mic.mapper.SignMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.security.Security;
import java.text.MessageFormat;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class AutuSignMoguDingApplication {

    @Resource
    private SignMapper signMapper;

    public static void main(String[] args) {
        SpringApplication.run(AutuSignMoguDingApplication.class, args);
    }

    @Async
    @PostConstruct
    public void updateSecurityProvider() {
        log.info("升级JDK加解密库...");
        Security.addProvider(new BouncyCastleProvider());
        Integer count = signMapper.selectCount();
        log.info(MessageFormat.format("加载用户-> {0}条", count));
    }
}
