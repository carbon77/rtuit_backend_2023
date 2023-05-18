package com.rtuit.backend.dao;

import com.rtuit.backend.model.EventCategory;
import com.rtuit.backend.model.Subscription;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscriptionResponse {
    private int id;
    private boolean emailEnabled;
    private EventCategory eventCategory;
    private int userId;

    public static SubscriptionResponse fromModel(Subscription subscription) {
        SubscriptionResponse response = new SubscriptionResponse();

        response.setId(subscription.getId());
        response.setEmailEnabled(subscription.isEmailEnabled());
        response.setEventCategory(subscription.getEventCategory());
        response.setUserId(subscription.getUser().getId());

        return response;
    }
}
