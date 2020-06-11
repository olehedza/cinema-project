package com.dev.cinema.controller;

import com.dev.cinema.dto.request.OrderRequestDto;
import com.dev.cinema.dto.response.OrderResponseDto;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.mapper.OrderMapper;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final ShoppingCartService cartService;
    private final UserService userService;

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestDto dto) {
        User user = userService.findById(dto.getUserId());
        ShoppingCart cart = cartService.getByUser(user);
        orderService.completeOrder(cart.getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersByUser(@RequestParam Long userId) {
        User user = userService.findById(userId);
        return orderService.getOrderHistory(user).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
