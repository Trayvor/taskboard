package com.taskboard.auth.dto;

import java.util.Set;
import com.taskboard.auth.model.Role;

public record UserResponseDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String username,
        Set<Role> roles
) {
}
