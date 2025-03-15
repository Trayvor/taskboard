package com.taskboard.auth.mapper;

import com.taskboard.auth.config.MapperConfig;
import com.taskboard.auth.dto.UserRegistrationRequestDto;
import com.taskboard.auth.dto.UserResponseDto;
import com.taskboard.auth.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface UserMapper {
    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);

    UserResponseDto toDto(User user);
}
