package com.dev.cinema.controller;

import com.dev.cinema.dto.response.UserResponseDto;
import com.dev.cinema.model.User;
import com.dev.cinema.model.mapper.UserMapper;
import com.dev.cinema.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(Authentication auth) {
        User user = userService.findByEmail(auth.getName());
        return userMapper.toDto(user);
    }
}
