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
        mailMessage.setText(generateBody(eventNotification));

        for (String email : eventNotification.getEmails()) {
            mailMessage.setTo(email);
            mailSender.send(mailMessage);
        }
    }

    public String generateBody(EventNotification notification) {
        String body = "";

        body += notification.getName() + "\n\n";
        body += notification.getDescription() + "\n\n";
        body += String.format("Start time: %s\nEnd time: %s", notification.getStartTime(), notification.getEndTime());

        return body;
    }
}
