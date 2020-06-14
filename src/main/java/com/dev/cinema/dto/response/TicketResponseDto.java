package com.dev.cinema.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketResponseDto {
    private Long ticketId;
    private String movieTitle;
    private String cinemaHallDescription;
    private LocalDateTime showTime;
}
