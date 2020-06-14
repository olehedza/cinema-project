package com.dev.cinema.controller;

import com.dev.cinema.dto.request.MovieRequestDto;
import com.dev.cinema.dto.response.MovieResponseDto;
import com.dev.cinema.model.mapper.MovieMapper;
import com.dev.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {
    private final MovieMapper movieMapper;
    private final MovieService movieService;

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto dto) {
        movieService.add(movieMapper.toModel(dto));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }
}
