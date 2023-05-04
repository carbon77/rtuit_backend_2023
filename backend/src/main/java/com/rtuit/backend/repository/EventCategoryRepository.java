package com.rtuit.backend.repository;

import com.rtuit.backend.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Integer> {
}