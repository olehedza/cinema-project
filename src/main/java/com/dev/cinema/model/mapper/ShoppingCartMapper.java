package com.dev.cinema.model.mapper;

import com.dev.cinema.dto.response.ShoppingCartResponseDto;
import com.dev.cinema.model.ShoppingCart;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartResponseDto toDto(ShoppingCart model) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setTickets(
                model.getTickets().stream()
                        .map(ticketMapper::toDto)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}
