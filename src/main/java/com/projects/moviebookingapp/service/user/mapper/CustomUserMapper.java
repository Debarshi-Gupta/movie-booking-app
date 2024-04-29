package com.projects.moviebookingapp.service.user.mapper;

import com.projects.moviebookingapp.model.dto.UserDto;
import com.projects.moviebookingapp.model.entity.User;

public class CustomUserMapper {

    public static User mapToUser(UserDto userDto)
    {

        return User.builder()
                .username(userDto.getUsername())
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .build();
    }

    public static UserDto mapToUserDto(User user)
    {
        return UserDto.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }
}
