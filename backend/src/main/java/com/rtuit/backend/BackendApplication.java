package com.rtuit.backend;

import com.rtuit.backend.model.Role;
import com.rtuit.backend.model.User;
import com.rtuit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
public class BackendApplication {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner createAdmin() {
        return args -> {
            User user = User.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);
        };
    }
}
