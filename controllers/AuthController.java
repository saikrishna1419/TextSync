package com.talksync.controllers;

import com.talksync.models.User;
import com.talksync.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        User user = authService.registerUser(request.get("name"), request.get("email"), request.get("password"));
        return ResponseEntity.ok(user);
    }

   @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    if (request.get("email") == null || request.get("password") == null) {
        return ResponseEntity.badRequest().body(Map.of("error", "Email and password are required"));
    }

    Optional<User> user = authService.loginUser(request.get("email"), request.get("password"));
    return user.<ResponseEntity<?>>map(ResponseEntity::ok) // Explicitly cast the type
               .orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
}
}
