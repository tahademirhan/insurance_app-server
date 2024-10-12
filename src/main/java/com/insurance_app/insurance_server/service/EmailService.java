package com.insurance_app.insurance_server.service;

import com.insurance_app.insurance_server.constant.ConfigConst;
import com.insurance_app.insurance_server.core.exception.BusinessException;
import com.insurance_app.insurance_server.core.exception.ExceptionCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final ConfigurationService configurationService;
    private final JavaMailSender mailSender;

    public void sendEmail(String to, String content)  {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("App Mailing" + " <" + configurationService.getByKey(ConfigConst.emailFrom, String.class) + ">");
            helper.setTo(to);
            helper.setSubject(configurationService.getByKey(ConfigConst.emailAuthenticationSubject, String.class));
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new BusinessException(ExceptionCodes.EMAIL001);
        }
    }
}
