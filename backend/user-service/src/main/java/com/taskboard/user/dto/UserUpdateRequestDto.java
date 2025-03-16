package com.taskboard.user.dto;

public record UserUpdateRequestDto(
        String username,
        String email,
        String firstName,
        String lastName
) {
}
