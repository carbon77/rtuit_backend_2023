package com.rtuit.backend.service;

import com.rtuit.backend.model.Subscription;
import com.rtuit.backend.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService extends CrudService<Subscription, Integer> {
    public SubscriptionService(SubscriptionRepository repository) {
        super(repository);
    }
}
