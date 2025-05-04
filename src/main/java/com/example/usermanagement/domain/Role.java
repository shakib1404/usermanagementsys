package com.example.usermanagement.domain;

import java.util.*;

public class Role {
    private UUID id;
    private String roleName;

    public Role(String roleName) {
        this.id = UUID.randomUUID();
        this.roleName = roleName;
    }

    public UUID getId() { return id; }
    public String getRoleName() { return roleName; }
}