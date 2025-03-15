package com.taskboard.auth.repository;

import com.taskboard.auth.model.Role;
import com.taskboard.auth.model.type.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(UserRole role);
}
