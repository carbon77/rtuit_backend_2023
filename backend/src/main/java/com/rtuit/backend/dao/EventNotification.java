package com.rtuit.backend.dao;

import com.rtuit.backend.Utility;
import com.rtuit.backend.model.Event;
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

    public static EventNotification fromEvent(Event event, Set<String> emails) {
        EventNotification dao = new EventNotification();
        dao.setName(event.getName());
        dao.setDescription(event.getDescription());
        dao.setAddress(String.format("%s, %s %s",
                event.getAddress().getCity(),
                event.getAddress().getStreet(),
                event.getAddress().getBuildingNumber()
        ));
        dao.setStartTime(Utility.timestampToFormatString(event.getStartTime()));
        dao.setEndTime(Utility.timestampToFormatString(event.getEndTime()));
        dao.setCategory(event.getEventCategory().getName());
        dao.setEmails(emails);

        return dao;
    }
}
