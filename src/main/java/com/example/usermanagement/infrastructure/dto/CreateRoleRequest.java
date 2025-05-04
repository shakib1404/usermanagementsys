package com.example.usermanagement.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateRoleRequest {

    @NotBlank(message = "Role name must not be blank")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
