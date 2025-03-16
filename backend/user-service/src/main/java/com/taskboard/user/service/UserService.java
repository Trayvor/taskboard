package com.taskboard.user.service;

import com.taskboard.user.dto.RoleUpdateRequestDto;
import com.taskboard.user.dto.UserResponseDto;
import com.taskboard.user.dto.UserUpdateRequestDto;

public interface UserService {
    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto, Long id);

    UserResponseDto updateUserRole(RoleUpdateRequestDto roleUpdateRequestDto, Long id);
}
