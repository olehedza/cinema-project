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
    @Positive(message = "Capacity field should only take positive values")
    private int capacity;
    @NotEmpty(message = "Description field shouldn't be blank")
    private String description;
}
