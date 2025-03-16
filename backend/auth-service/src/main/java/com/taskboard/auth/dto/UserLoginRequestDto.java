package com.taskboard.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @Email String email,
        String username,
        @NotBlank @Size(min = 8, max = 20) String password
) {
}
