package com.rtuit.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtuit.backend.model.Event;
import com.rtuit.backend.dao.EventNotification;
import com.rtuit.backend.service.EventService;
import com.rtuit.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notify")
public class NotifyController {

    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;
    private final EventService eventService;

    @PostMapping
    public void notifyUser(
            @RequestBody Integer eventId
    ) throws JsonProcessingException {
        Event event = eventService.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("There is no such event"));

        Integer categoryId = event.getEventCategory().getId();
        Set<String> emails = userService.findAllEmailsBySubscribedCategory(categoryId);
        EventNotification dao = EventNotification.fromEvent(event, emails);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dao);

        rabbitTemplate.convertAndSend("notification", json);
    }
}
