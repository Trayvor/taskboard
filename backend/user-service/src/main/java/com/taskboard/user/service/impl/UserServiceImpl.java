package com.taskboard.user.service.impl;

import com.taskboard.user.dto.RoleUpdateRequestDto;
import com.taskboard.user.dto.UserResponseDto;
import com.taskboard.user.dto.UserUpdateRequestDto;
import com.taskboard.user.mapper.UserMapper;
import com.taskboard.user.model.Role;
import com.taskboard.user.model.User;
import com.taskboard.user.repository.RoleRepository;
import com.taskboard.user.repository.UserRepository;
import com.taskboard.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
        userMapper.updateUser(userUpdateRequestDto, user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUserRole(RoleUpdateRequestDto roleUpdateRequestDto, Long id) {
        Role role = roleRepository.findByRoleName(roleUpdateRequestDto.role()).orElseThrow(
                () -> new EntityNotFoundException(
                        "Role " + roleUpdateRequestDto.role() + " not found"
                )
        );
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );

        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
