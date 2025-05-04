package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.domain.Role;
import com.example.usermanagement.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        userService = new UserService(userRepository, roleRepository);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        String name = "Alice";
        String email = "alice@example.com";

        UUID generatedId = UUID.randomUUID();
        User user = new User(name, email);

        when(userRepository.save(any(User.class))).thenReturn(user);

        UUID result = userService.createUser(name, email);

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldAssignRoleToUser() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();

        User user = new User("Bob", "bob@example.com");
        Role role = new Role("ADMIN");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        when(userRepository.save(user)).thenReturn(user);

        assertDoesNotThrow(() -> userService.assignRole(userId, roleId));
        verify(userRepository).save(user);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            userService.assignRole(userId, roleId);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        User user = new User("Charlie", "charlie@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            userService.assignRole(userId, roleId);
        });

        assertEquals("Role not found", exception.getMessage());
    }
}