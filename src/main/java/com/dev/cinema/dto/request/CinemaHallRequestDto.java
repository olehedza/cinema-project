package com.dev.cinema.dto.request;

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
