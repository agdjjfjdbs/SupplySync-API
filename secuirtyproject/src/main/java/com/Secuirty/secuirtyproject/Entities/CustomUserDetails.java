package com.Secuirty.secuirtyproject.Entities;

import jakarta.persistence.*;

@Entity
public class CustomUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Other properties and methods...
}
