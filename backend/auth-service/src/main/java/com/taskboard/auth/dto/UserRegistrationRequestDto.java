package com.taskboard.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.taskboard.auth.annotation.FieldMatch;

@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords must match")
public record UserRegistrationRequestDto(
        @NotBlank @Size(min = 8, max = 20) String password,
        @NotBlank @Size(min = 8, max = 20) String repeatPassword,
        @NotBlank @Email String email,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String username
) {
}
