package com.dev.cinema.dto.request;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieRequestDto {
    @Size(max = 50)
    private String title;
    @Size(max = 60)
    private String description;
}
