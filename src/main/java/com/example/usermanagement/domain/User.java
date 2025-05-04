package com.example.usermanagement.domain;

import java.util.*;

public class User {
    private UUID id;
    private String name;
    private String email;
    private Set<Role> roles = new HashSet<>();

    public User(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Set<Role> getRoles() { return roles; }
    public void assignRole(Role role) { this.roles.add(role); }
}