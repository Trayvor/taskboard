package com.taskboard.user.dto;

import com.taskboard.user.model.type.UserRole;
import jakarta.validation.constraints.NotNull;

public record RoleUpdateRequestDto(@NotNull UserRole role) {
}
