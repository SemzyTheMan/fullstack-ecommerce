package com.semzy_ecommerce.soft.dtos.requests;

import com.semzy_ecommerce.soft.entity.Role;

public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private Role.ERole role;

    public RegisterRequest(String name, String email, String password, Role.ERole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role.ERole getRole() {
        return role;
    }

    public void setRole(Role.ERole role) {
        this.role = role;
    }
}
