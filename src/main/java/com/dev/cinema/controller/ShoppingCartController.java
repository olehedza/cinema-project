package com.dev.cinema.controller;

import com.dev.cinema.dto.response.ShoppingCartResponseDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.mapper.ShoppingCartMapper;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
@AllArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartMapper cartMapper;
    private final ShoppingCartService cartService;
    private final UserService userService;
    private final MovieSessionService sessionService;

    @PostMapping("/add-movie-session")
    public void addMovieSession(@RequestParam Long userId,
                                @RequestParam Long sessionId) {
        User user = userService.findById(userId);
        MovieSession movieSession = sessionService.findById(sessionId);
        cartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication auth) {
        String email = auth.getName();
        User user = userService.findByEmail(email);
        ShoppingCart cart = cartService.getByUser(user);
        return cartMapper.toDto(cart);
    }
}
