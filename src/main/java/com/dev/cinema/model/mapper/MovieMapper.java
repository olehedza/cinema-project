package com.dev.cinema.model.mapper;

import com.dev.cinema.dto.request.MovieRequestDto;
import com.dev.cinema.dto.response.MovieResponseDto;
import com.dev.cinema.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponseDto toDto(Movie model) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setMovieId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public Movie toModel(MovieRequestDto dto) {
        Movie model = new Movie();
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        return model;
    }
}
