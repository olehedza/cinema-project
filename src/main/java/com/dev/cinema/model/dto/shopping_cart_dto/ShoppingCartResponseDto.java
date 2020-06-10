package com.dev.cinema.model.dto.shopping_cart_dto;

import com.dev.cinema.model.dto.ticket_dto.TicketResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartResponseDto {
    private List<TicketResponseDto> tickets;
}
