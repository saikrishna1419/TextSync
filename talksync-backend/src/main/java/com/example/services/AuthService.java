package com.example.services;

import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();  // Ensure proper encoding
    }

    public User registerUser(String name, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, email, name, encodedPassword);
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String email, String password) {
    Optional<User> user = userRepository.findByEmail(email);

    if (user.isPresent()) {
        System.out.println("Stored Password (Encoded): " + user.get().getPassword());
        System.out.println("Entered Password: " + password);

        if (passwordEncoder.matches(password, user.get().getPassword())) {
            System.out.println("Password Matched!");
            return user;
        } else {
            System.out.println("Password Mismatch!");
        }
    } else {
        System.out.println("User Not Found!");
    }
    return Optional.empty();
}

}
