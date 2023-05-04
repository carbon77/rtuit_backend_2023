package com.rtuit.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Integer id;

    private String name;

    private String description;

    @Embedded
    private EventAddress address;

    private Timestamp startTime;

    private Timestamp endTime;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private EventCategory eventCategory;

    @Data
    @Embeddable
    private static class EventAddress {
        private String city;
        private String street;
        private String buildingNumber;
    }
}
