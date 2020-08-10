package com.dev.cinema.controller;

import com.dev.cinema.dto.request.CinemaHallRequestDto;
import com.dev.cinema.dto.response.CinemaHallResponseDto;
import com.dev.cinema.model.mapper.CinemaHallMapper;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
@AllArgsConstructor
public class CinemaHallController {
    private final CinemaHallMapper hallMapper;
    private final CinemaHallService hallService;

    @PostMapping
    public void addCinemaHall(@RequestBody @Valid CinemaHallRequestDto dto) {
        hallService.add(hallMapper.toModel(dto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return hallService.getAll().stream()
                .map(hallMapper::toDto)
                .collect(Collectors.toList());
    }
}
