package com.insurance_app.insurance_server.core.config;

import com.insurance_app.insurance_server.constant.ConfigConst;
import com.insurance_app.insurance_server.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

    private final ConfigurationService configurationService;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.yandex.com");
        mailSender.setPort(587);
        mailSender.setUsername(configurationService.getByKey(ConfigConst.emailFrom, String.class));
        mailSender.setPassword(configurationService.getByKey(ConfigConst.emailFromPassword, String.class));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
