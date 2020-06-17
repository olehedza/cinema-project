package com.dev.cinema.dto.request;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieRequestDto {
    @Size(max = 50, message = "Title max size = 50")
    private String title;
    @Size(max = 100, message = "Description max size = 100")
    private String description;
}
