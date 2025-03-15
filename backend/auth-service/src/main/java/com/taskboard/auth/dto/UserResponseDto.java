package com.taskboard.auth.dto;

import com.taskboard.auth.model.Role;
import java.util.Set;

public record UserResponseDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String username,
        Set<Role> roles
) {
}
