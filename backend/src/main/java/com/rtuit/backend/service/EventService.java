package com.rtuit.backend.service;

import com.rtuit.backend.model.Event;
import com.rtuit.backend.model.EventCategory;
import com.rtuit.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService extends CrudService<Event, Integer> {
    private final EventRepository eventRepository;

    public EventService(EventRepository repository) {
        super(repository);
        this.eventRepository = repository;
    }

    public void addCategoryToEvent(Event event, EventCategory category) {
        event.setEventCategory(category);
        eventRepository.save(event);
    }
}
