package com.dev.cinema.model.mapper;

import com.dev.cinema.dto.response.UserResponseDto;
import com.dev.cinema.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toDto(User model) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(model.getId());
        dto.setEmail(model.getEmail());
        return dto;
    }
}
