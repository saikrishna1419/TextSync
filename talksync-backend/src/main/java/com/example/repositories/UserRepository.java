package com.example.repositories;

import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository  // Optional, but good practice
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
