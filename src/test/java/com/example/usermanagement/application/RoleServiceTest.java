package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    private RoleRepository roleRepository;
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        roleRepository = mock(RoleRepository.class);
        roleService = new RoleService(roleRepository);
    }

    @Test
    void shouldCreateRoleSuccessfully() {
        String roleName = "USER";
        Role role = new Role(roleName);

        when(roleRepository.save(any(Role.class))).thenReturn(role);

        UUID result = roleService.createRole(roleName);

        assertNotNull(result);
        verify(roleRepository, times(1)).save(any(Role.class));
    }
}
