package com.example.usermanagement.infrastructure.controller;

import com.example.usermanagement.application.RoleService;
import com.example.usermanagement.infrastructure.dto.CreateRoleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/roles")
@Tag(name = "Role Controller", description = "Operations related to role management")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Create a new role")
    @PostMapping
    public ResponseEntity<UUID> createRole(@Valid @RequestBody CreateRoleRequest request) {
        UUID id = roleService.createRole(request.getRoleName());
        return ResponseEntity.ok(id);
    }
}
