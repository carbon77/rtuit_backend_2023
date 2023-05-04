package com.rtuit.backend.service;

import com.rtuit.backend.model.Event;
import com.rtuit.backend.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService extends CrudService<Event, Integer> {
    public EventService(EventRepository repository) {
        super(repository);
    }
}
