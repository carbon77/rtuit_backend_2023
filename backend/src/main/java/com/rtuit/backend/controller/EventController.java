package com.rtuit.backend.controller;

import com.rtuit.backend.model.Event;
import com.rtuit.backend.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController extends CrudController<Event, Integer> {
    public EventController(EventService service) {
        super(service);
    }
}
