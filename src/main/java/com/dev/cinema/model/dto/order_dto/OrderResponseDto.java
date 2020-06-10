package com.dev.cinema.model.dto.order_dto;

import com.dev.cinema.model.dto.ticket_dto.TicketResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime showTime;
    private List<TicketResponseDto> tickets;
}
