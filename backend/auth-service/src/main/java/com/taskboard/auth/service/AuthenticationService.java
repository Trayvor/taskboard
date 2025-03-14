package com.taskboard.auth.service;

import com.taskboard.auth.dto.UserLoginRequestDto;
import com.taskboard.auth.dto.UserLoginResponseDto;
import com.taskboard.auth.dto.UserRegistrationRequestDto;
import com.taskboard.auth.dto.UserResponseDto;
import com.taskboard.auth.exception.RegistrationException;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto userLoginRequestDto);
    UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) throws RegistrationException;
}
