package com.dev.cinema.model.dto.cinema_hall_dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CinemaHallRequestDto {
    private int capacity;
    private String description;
}
