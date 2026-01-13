package com.projetoPessoal.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_user")
public class SystemUser {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column(name = "created_at")
        private LocalDateTime createdAt = LocalDateTime.now();
    }



