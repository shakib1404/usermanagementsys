package com.example.usermanagement.infrastructure.controller;

import com.example.usermanagement.application.UserService;
import com.example.usermanagement.domain.User;
import com.example.usermanagement.infrastructure.dto.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Operations related to user management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public ResponseEntity<UUID> createUser(@Valid @RequestBody CreateUserRequest request) {
        UUID id = userService.createUser(request.getName(), request.getEmail());
        return ResponseEntity.ok(id);
    }

    @Operation(summary = "Assign a role to an existing user")
    @PostMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable UUID userId, @PathVariable UUID roleId) {
        userService.assignRole(userId, roleId);
        return ResponseEntity.ok("Role assigned successfully");
    }

    @Operation(summary = "Fetch a user along with assigned roles")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
