package com.taskboard.user.controller;

import com.taskboard.user.dto.RoleUpdateRequestDto;
import com.taskboard.user.dto.UserResponseDto;
import com.taskboard.user.dto.UserUpdateRequestDto;
import com.taskboard.user.model.User;
import com.taskboard.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User manager", description = "Endpoint for user management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get user info", description = "Returns detail user info ")
    public UserResponseDto getMe(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.getUserById(user.getId());
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Update user info")
    public UserResponseDto updateMe(
            Authentication authentication,
            @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        User user = (User) authentication.getPrincipal();
        return userService.updateUser(userUpdateRequestDto, user.getId());
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Change user's role")
    public UserResponseDto updateRoleById(
            @RequestBody RoleUpdateRequestDto roleUpdateRequestDto,
            @PathVariable Long id
    ) {
        return userService.updateUserRole(roleUpdateRequestDto, id);
    }
}
