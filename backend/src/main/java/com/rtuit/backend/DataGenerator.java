package com.rtuit.backend;

import com.github.javafaker.Faker;
import com.rtuit.backend.model.Event;
import com.rtuit.backend.model.EventCategory;
import com.rtuit.backend.repository.EventCategoryRepository;
import com.rtuit.backend.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Configuration
public class DataGenerator {
    private final EventRepository eventRepository;

    @Bean
    @Transactional
    public CommandLineRunner generateData() {
        return args -> {
            EventCategory category = new EventCategory();
            category.setName("Sport");

            Event.EventAddress address = Event.EventAddress.builder()
                    .city("New York")
                    .street("5th Avenue")
                    .buildingNumber("6a")
                    .build();
            Event event = Event.builder()
                    .name("Football match")
                    .description("New match")
                    .eventCategory(category)
                    .startTime(new Timestamp(System.currentTimeMillis()))
                    .endTime(new Timestamp(System.currentTimeMillis() + 1000 * 60 * 60))
                    .address(address)
                    .build();

            eventRepository.save(event);
        };
    }
}
