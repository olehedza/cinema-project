package com.dev.cinema.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CinemaHallRequestDto {
    @Positive
    private int capacity;
    @NotEmpty
    private String description;
}
