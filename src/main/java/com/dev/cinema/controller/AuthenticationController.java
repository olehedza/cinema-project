package com.dev.cinema.controller;

import com.dev.cinema.dto.request.UserRequestDto;
import com.dev.cinema.error.AuthenticationException;
import com.dev.cinema.security.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto userDto)
            throws AuthenticationException {
        authService.register(userDto.getEmail(), userDto.getPassword());
    }
}
