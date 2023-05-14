package com.rtuit.backend.controller;

import com.rtuit.backend.model.Event;
import com.rtuit.backend.model.EventCategory;
import com.rtuit.backend.service.EventCategoryService;
import com.rtuit.backend.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/events")
public class EventController extends CrudController<Event, Integer> {
    private final EventCategoryService eventCategoryService;
    private final EventService eventService;

    public EventController(EventService service, EventCategoryService eventCategoryService) {
        super(service);
        this.eventCategoryService = eventCategoryService;
        this.eventService = service;
    }

    @PostMapping("/{eventId}/category")
    public void addCategoryToEvent(
            @PathVariable Integer eventId,
            @RequestBody Integer categoryId
    ) {
        EventCategory category = eventCategoryService.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "There is no category with id=" + categoryId
                ));

        Event event = eventService.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "There is no event with id=" + eventId
                ));

        eventService.addCategoryToEvent(event, category);
    }
}
