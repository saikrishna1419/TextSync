package com.example.controllers;

import com.example.models.User;
import com.example.services.AuthService;
import com.example.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        User user = authService.registerUser(request.get("name"), request.get("email"), request.get("password"));
        // Optionally, generate a token upon registration as well
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("user", user, "token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<User> userOptional = authService.loginUser(request.get("email"), request.get("password"));
        if (userOptional.isPresent()) {
            // Generate token
            String token = jwtUtil.generateToken(userOptional.get().getEmail());
            return ResponseEntity.ok(Map.of("user", userOptional.get(), "token", token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
