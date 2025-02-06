package com.example.talksync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.repositories")  // Ensure repositories are scanned
@ComponentScan({"com.example", "com.example.controllers", 
                "com.example.services", "com.example.models", 
                "com.example.config", "com.example.filter", 
                "com.example.repositories", "com.example.util"})
@EntityScan(basePackages = "com.example.models") // Ensure JPA entities are scanned
public class TalksyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(TalksyncApplication.class, args);
    }
}
