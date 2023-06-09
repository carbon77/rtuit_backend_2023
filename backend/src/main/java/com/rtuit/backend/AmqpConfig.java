package com.rtuit.backend;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Bean
    public Queue mailNotificationQueue() {
        return new Queue("notification", false);
    }
}
