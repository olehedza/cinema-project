package com.dev.cinema.model.mapper;

import com.dev.cinema.dto.response.OrderResponseDto;
import com.dev.cinema.model.Order;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderResponseDto toDto(Order model) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(model.getId());
        dto.setTickets(
                model.getTickets().stream()
                        .map(ticketMapper::toDto)
                        .collect(Collectors.toList())
        );
        dto.setOrderDate(model.getOrderDate());
        return dto;
    }
}
