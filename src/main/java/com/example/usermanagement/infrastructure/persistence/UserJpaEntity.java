package com.example.usermanagement.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    private UUID id;

    private String name;

    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleJpaEntity> roles = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<RoleJpaEntity> getRoles() {
        return roles;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<RoleJpaEntity> roles) {
        this.roles = roles;
    }
// Getters and setters
}