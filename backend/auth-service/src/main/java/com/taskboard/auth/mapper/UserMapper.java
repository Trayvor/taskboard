package com.taskboard.auth.mapper;

import org.mapstruct.Mapper;
import com.taskboard.auth.config.MapperConfig;
import com.taskboard.auth.dto.UserRegistrationRequestDto;
import com.taskboard.auth.dto.UserResponseDto;
import com.taskboard.auth.model.User;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface UserMapper {
    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);

    UserResponseDto toDto(User user);
}
