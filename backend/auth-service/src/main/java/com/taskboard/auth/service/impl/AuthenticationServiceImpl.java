package com.taskboard.auth.service.impl;

import com.taskboard.auth.dto.UserLoginRequestDto;
import com.taskboard.auth.dto.UserLoginResponseDto;
import com.taskboard.auth.dto.UserRegistrationRequestDto;
import com.taskboard.auth.dto.UserResponseDto;
import com.taskboard.auth.exception.RegistrationException;
import com.taskboard.auth.mapper.UserMapper;
import com.taskboard.auth.model.Role;
import com.taskboard.auth.model.User;
import com.taskboard.auth.model.type.UserRole;
import com.taskboard.auth.repository.RoleRepository;
import com.taskboard.auth.repository.UserRepository;
import com.taskboard.auth.security.JwtUtil;
import com.taskboard.auth.service.AuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto userLoginRequestDto) {
        if (userLoginRequestDto.username() != null) {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginRequestDto.username(), userLoginRequestDto.password()
                    )
            );
            String token = jwtUtil.generateToken(authentication.getName());
            return new UserLoginResponseDto(token);
        }
        if (userLoginRequestDto.email() != null) {
            User user = userRepository.findByEmail(userLoginRequestDto.email()).orElseThrow(
                    () -> new EntityNotFoundException(
                            "User with email " + userLoginRequestDto.email() + " not found"
                    )
            );
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(), userLoginRequestDto.password()
                    )
            );
            String token = jwtUtil.generateToken(authentication.getName());
            return new UserLoginResponseDto(token);
        }
        throw new AuthenticationServiceException("Invalid credentials");
    }

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(userRegistrationRequestDto.email())) {
            throw new RegistrationException(
                    "User with email " + userRegistrationRequestDto.email() + " already exists"
            );
        }
        User user = userMapper.toModel(userRegistrationRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = user.getRoles();
        roles.add(roleRepository.findByRoleName(UserRole.ROLE_USER).orElseThrow(
                () -> new EntityNotFoundException("Role " + UserRole.ROLE_USER + " not found")
        ));
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
