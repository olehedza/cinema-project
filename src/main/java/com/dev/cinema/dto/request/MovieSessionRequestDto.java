package com.dev.cinema.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieSessionRequestDto {
    @NotNull(message = "movieId cannot be null")
    private Long movieId;
    @NotNull(message = "cinemaHallId cannot be null")
    private Long cinemaHallId;
    @FutureOrPresent(message = "The time must be present or future")
    private LocalDateTime showTime;
}
