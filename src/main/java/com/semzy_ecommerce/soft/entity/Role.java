package com.semzy_ecommerce.soft.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;


    public enum ERole {
        ROLE_USER,
        ROLE_MODERATOR,
        ROLE_ADMIN
    }

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
