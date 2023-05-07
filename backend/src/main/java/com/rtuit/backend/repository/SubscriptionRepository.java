package com.rtuit.backend.repository;

import com.rtuit.backend.model.EventCategory;
import com.rtuit.backend.model.Subscription;
import com.rtuit.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Optional<Subscription> findByUserAndEventCategory(User user, EventCategory eventCategory);
}