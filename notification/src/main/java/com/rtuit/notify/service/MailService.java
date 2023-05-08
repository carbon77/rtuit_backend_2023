package com.rtuit.notify.service;

import com.rtuit.notify.dao.EventNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;    

    public void send(EventNotification eventNotification) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@rtuit.com");
        mailMessage.setSubject(eventNotification.getName());
        mailMessage.setText(String.format("%s\n\n%s", eventNotification.getName(), eventNotification.getDescription()));

        for (String email : eventNotification.getEmails()) {
            mailMessage.setTo(email);
            mailSender.send(mailMessage);
        }
    }
}
