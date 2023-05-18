package com.rtuit.backend.controller;

import com.rtuit.backend.dao.SubscriptionResponse;
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
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final EventCategoryService categoryService;
    private final UserService userService;

    @GetMapping("/subscriptions")
    public ResponseEntity<Set<SubscriptionResponse>> findSubscriptions(
            Authentication authentication
    ) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        Set<SubscriptionResponse> responses = subscriptionService.findAllByUser(user)
                .stream()
                .map(SubscriptionResponse::fromModel)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(
            @RequestBody SubscriptionRequestData subscriptionRequestData,
            Authentication authentication
    ) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        EventCategory category = categoryService.findById(subscriptionRequestData.categoryId)
                .orElseThrow(() -> new IllegalArgumentException("There is no such category"));

        Subscription subscription = subscriptionService.findByUserAndCategory(user, category);
        subscription.setEmailEnabled(subscriptionRequestData.emailEnabled);

        subscriptionService.save(subscription);

        return ResponseEntity.ok("Success!");
    }

    private record SubscriptionRequestData(
            int categoryId,
            boolean emailEnabled
    ) {
    }
}
