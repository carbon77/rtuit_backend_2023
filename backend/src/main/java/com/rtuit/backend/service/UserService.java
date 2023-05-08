package com.rtuit.backend.service;

import com.rtuit.backend.model.User;
import com.rtuit.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService extends CrudService<User, Integer> {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Set<String> findAllEmailsBySubscribedCategory(int categoryId) {
        return userRepository.findAllEmailsBySubscribedCategory(categoryId);
    }
}
