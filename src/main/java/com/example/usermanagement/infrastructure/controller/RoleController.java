package com.example.usermanagement.infrastructure.controller;

import com.example.usermanagement.application.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.Map;

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
    public ResponseEntity<UUID> createRole(@RequestBody Map<String, String> body) {
        String roleName = body.get("roleName");
        UUID id = roleService.createRole(roleName);
        return ResponseEntity.ok(id);
    }
}
