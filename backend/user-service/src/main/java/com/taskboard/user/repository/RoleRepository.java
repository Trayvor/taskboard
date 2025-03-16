package com.taskboard.user.repository;

import com.taskboard.user.model.Role;
import com.taskboard.user.model.type.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(UserRole role);
}
