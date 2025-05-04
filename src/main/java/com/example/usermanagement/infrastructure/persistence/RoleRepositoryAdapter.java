package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.application.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryAdapter(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Role save(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        return toDomain(roleJpaRepository.save(entity));
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleJpaRepository.findById(id).map(this::toDomain);
    }

    private Role toDomain(RoleJpaEntity entity) {
        Role role = new Role(entity.getRoleName());
        try {
            var field = Role.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(role, entity.getId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to set Role ID", e);
        }
        return role;
    }
}