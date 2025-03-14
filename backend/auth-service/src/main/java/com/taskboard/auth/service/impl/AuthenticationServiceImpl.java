package com.taskboard.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.taskboard.auth.dto.UserLoginRequestDto;
import com.taskboard.auth.dto.UserLoginResponseDto;
import com.taskboard.auth.dto.UserRegistrationRequestDto;
import com.taskboard.auth.dto.UserResponseDto;
import com.taskboard.auth.exception.RegistrationException;
import com.taskboard.auth.mapper.UserMapper;
import com.taskboard.auth.model.User;
import com.taskboard.auth.repository.UserRepository;
import com.taskboard.auth.security.JwtUtil;
import com.taskboard.auth.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto userLoginRequestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.email(), userLoginRequestDto.password()
                )
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(token);
    }

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) throws RegistrationException {
        if (userRepository.existsByEmail(userRegistrationRequestDto.email())) {
            throw new RegistrationException(
                    "User with email " + userRegistrationRequestDto.email() + " already exists"
            );
        }
        User user = userMapper.toModel(userRegistrationRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
