package com.rtuit.backend.controller;

import com.rtuit.backend.model.Subscription;
import com.rtuit.backend.service.SubscriptionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController extends CrudController<Subscription, Integer> {
    public SubscriptionController(SubscriptionService service) {
        super(service);
    }
}
