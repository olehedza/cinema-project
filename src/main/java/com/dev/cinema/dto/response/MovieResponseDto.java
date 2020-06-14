package com.dev.cinema.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieResponseDto {
    private Long movieId;
    private String title;
    private String description;
}
