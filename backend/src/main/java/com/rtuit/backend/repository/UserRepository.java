package com.rtuit.backend.repository;

import com.rtuit.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query(
            "SELECT u.email FROM User u " +
                    "JOIN u.subscriptions sub " +
                    "WHERE sub.eventCategory.id = :categoryId AND sub.emailEnabled"
    )
    Set<String> findAllEmailsBySubscribedCategory(@Param("categoryId") int categoryId);
}
