package com.example.usermanagement.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "roles")
public class RoleJpaEntity {
    @Id
    private UUID id;

    private String roleName;

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}