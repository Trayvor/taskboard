package com.taskboard.user.dto;

import com.taskboard.user.model.Role;
import java.util.Set;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        Set<Role> roles
) {
}
