package com.rtuit.notify.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventNotification implements Serializable {
    private String name;
    private String description;
    private String address;
    private String startTime;
    private String endTime;
    private String category;
    private Set<String> emails;
}

