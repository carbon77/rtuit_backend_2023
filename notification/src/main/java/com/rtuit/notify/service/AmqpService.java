package com.rtuit.notify.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtuit.notify.dao.EventNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AmqpService {
    private final MailService mailService;

    @RabbitListener(queues = "notification")
    public void notificationListener(String notificationJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        EventNotification eventNotification = objectMapper.readValue(notificationJson, EventNotification.class);
        mailService.send(eventNotification);
    }
}
