package com.talksync.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")  // Ensures table name is "users"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;  // This will be stored as a hashed value
}
