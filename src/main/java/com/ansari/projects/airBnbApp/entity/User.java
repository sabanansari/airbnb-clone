package com.ansari.projects.airBnbApp.entity;

import com.ansari.projects.airBnbApp.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER) //so that jpa creates another table for us
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
}
