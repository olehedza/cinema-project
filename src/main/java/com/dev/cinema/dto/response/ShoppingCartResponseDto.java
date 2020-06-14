package com.dev.cinema.dto.response;

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
