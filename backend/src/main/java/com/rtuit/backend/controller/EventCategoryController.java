package com.rtuit.backend.controller;

import com.rtuit.backend.model.EventCategory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eventCategories")
public class EventCategoryController extends CrudController<EventCategory, Integer> {
    public EventCategoryController(com.rtuit.backend.service.EventCategoryService service) {
        super(service);
    }
}
