package com.dev.cinema.model.dto.cinema_hall_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CinemaHallResponseDto {
    private Long cinemaHallId;
    private int capacity;
    private String description;
}
