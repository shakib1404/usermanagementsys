package com.example.usermanagement.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, UUID> {
}