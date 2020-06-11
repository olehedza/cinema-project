package com.dev.cinema.model.mapper;

import com.dev.cinema.dto.request.MovieSessionRequestDto;
import com.dev.cinema.dto.response.MovieSessionResponseDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieSessionMapper {
    private final MovieService movieService;
    private CinemaHallService cinemaHallService;

    public MovieSessionResponseDto toDto(MovieSession model) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setMovieId(model.getMovie().getId());
        dto.setCinemaHallId(model.getCinemaHall().getId());
        dto.setMovieSessionId(model.getId());
        dto.setMovieTitle(model.getMovie().getTitle());
        dto.setShowTime(model.getShowTime());
        return dto;
    }

    public MovieSession toModel(MovieSessionRequestDto dto) {
        MovieSession model = new MovieSession();
        Movie movie = movieService.findById(dto.getMovieId());
        CinemaHall cinemaHall = cinemaHallService.findById(dto.getCinemaHallId());
        model.setMovie(movie);
        model.setCinemaHall(cinemaHall);
        model.setShowTime(dto.getShowTime());
        return model;
    }
}
