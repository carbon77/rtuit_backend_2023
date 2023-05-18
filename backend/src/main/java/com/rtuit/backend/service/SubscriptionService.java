package com.rtuit.backend.service;

import com.rtuit.backend.model.EventCategory;
import com.rtuit.backend.model.Subscription;
import com.rtuit.backend.model.User;
import com.rtuit.backend.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SubscriptionService extends CrudService<Subscription, Integer> {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository repository) {
        super(repository);
        this.subscriptionRepository = repository;
    }

    public Subscription findByUserAndCategory(User user, EventCategory category) {
        return subscriptionRepository.findByUserAndEventCategory(
                user, category
        ).orElse(Subscription.builder()
                .user(user)
                .eventCategory(category)
                .build()
        );
    }

    public Set<Subscription> findAllByUser(User user) {
        return subscriptionRepository.findAllByUser(user);
    }
}
