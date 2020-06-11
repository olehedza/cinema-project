package com.dev.cinema.controller;

import com.dev.cinema.dto.request.MovieSessionRequestDto;
import com.dev.cinema.dto.response.MovieSessionResponseDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.mapper.MovieSessionMapper;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
@AllArgsConstructor
public class MovieSessionController {
    private final MovieSessionMapper sessionMapper;
    private final MovieSessionService sessionService;

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto dto) {
        sessionService.add(sessionMapper.toModel(dto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllMovieSessions(@RequestParam Long movieId,
                                                             @RequestParam LocalDate date) {
        List<MovieSession> sessions =
                sessionService.findAvailableSessions(movieId, date);
        return sessions.stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }
}
