package com.ms.email.services;

import com.ms.email.models.EmailModel;
import com.ms.email.repositorys.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.ms.email.models.EmailModel.StatusEmailEnum.*;

@Service
@RequiredArgsConstructor
public class EmailServices {

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Transactional
    public void sendEmail(EmailModel emailModel) {
        try {
            emailModel.setEmailFrom(emailFrom);
            emailModel.setSendEmailTime(LocalDateTime.now());

            var simpleMessage = new SimpleMailMessage();
            simpleMessage.setTo(emailModel.getEmailTo());
            simpleMessage.setSubject(emailModel.getSubject());
            simpleMessage.setText(emailModel.getText());

            mailSender.send(simpleMessage);
            emailModel.setStatusEmail(SENT);
        } catch (Exception e) {
            emailModel.setStatusEmail(ERROR);
        } finally {
            emailRepository.save(emailModel);
        }

    }
}
