package com.rtuit.backend.service;

import com.rtuit.backend.model.EventCategory;
import com.rtuit.backend.repository.EventCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class EventCategoryService extends CrudService<EventCategory, Integer> {
    public EventCategoryService(EventCategoryRepository repository) {
        super(repository);
    }
}
