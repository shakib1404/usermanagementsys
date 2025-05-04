package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.application.interfaces.UserRepository;
import com.example.usermanagement.domain.Role;
import com.example.usermanagement.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());

        Set<RoleJpaEntity> roleEntities = new HashSet<>();
        for (Role role : user.getRoles()) {
            RoleJpaEntity r = new RoleJpaEntity();
            r.setId(role.getId());
            r.setRoleName(role.getRoleName());
            roleEntities.add(r);
        }
        entity.setRoles(roleEntities);
        return toDomain(userJpaRepository.save(entity));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id).map(this::toDomain);
    }

    private User toDomain(UserJpaEntity entity) {
        User user = new User(entity.getName(), entity.getEmail());
        try {
            var field = User.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(user, entity.getId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to set User ID", e);
        }
        for (RoleJpaEntity r : entity.getRoles()) {
            Role role = new Role(r.getRoleName());
            try {
                var field = Role.class.getDeclaredField("id");
                field.setAccessible(true);
                field.set(role, r.getId());
            } catch (Exception e) {
                throw new RuntimeException("Failed to set Role ID", e);
            }
            user.assignRole(role);
        }
        return user;
    }
}
