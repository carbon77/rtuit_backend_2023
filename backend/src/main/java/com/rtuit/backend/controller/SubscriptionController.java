package com.rtuit.backend.controller;

import com.rtuit.backend.model.EventCategory;
import com.rtuit.backend.model.Subscription;
import com.rtuit.backend.model.User;
import com.rtuit.backend.service.EventCategoryService;
import com.rtuit.backend.service.SubscriptionService;
import com.rtuit.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final EventCategoryService categoryService;
    private final UserService userService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(
            @RequestBody SubcribeRequestData subcribeRequestData,
            Authentication authentication
    ) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        EventCategory category = categoryService.findById(subcribeRequestData.categoryId)
                .orElseThrow(() -> new IllegalArgumentException("There is no such category"));

        Subscription subscription = subscriptionService.findByUserAndCategory(user, category);
        subscription.setEmailEnabled(subcribeRequestData.emailEnabled);

        subscriptionService.save(subscription);

        return ResponseEntity.ok("Success!");
    }

    private record SubcribeRequestData(
            int categoryId,
            boolean emailEnabled
    ) {
    }
}
