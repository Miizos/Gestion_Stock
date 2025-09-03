package com.microservices.backend.Entites.Users;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Roles role;

}
