package com.example.IPRWCbackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "\"account\"")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;

    public Account(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Account() {

    }
}
