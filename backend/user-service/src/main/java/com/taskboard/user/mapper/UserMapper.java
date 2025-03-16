package com.taskboard.user.mapper;

import com.taskboard.user.config.MapperConfig;
import com.taskboard.user.dto.UserResponseDto;
import com.taskboard.user.dto.UserUpdateRequestDto;
import com.taskboard.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    void updateUser(UserUpdateRequestDto userUpdateRequestDto, @MappingTarget User user);
}
