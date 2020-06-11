package com.dev.cinema.model.mapper;

import com.dev.cinema.dto.request.CinemaHallRequestDto;
import com.dev.cinema.dto.response.CinemaHallResponseDto;
import com.dev.cinema.model.CinemaHall;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {

    public CinemaHallResponseDto toDto(CinemaHall model) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setCinemaHallId(model.getId());
        dto.setCapacity(model.getCapacity());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public CinemaHall toModel(CinemaHallRequestDto dto) {
        CinemaHall model = new CinemaHall();
        model.setCapacity(dto.getCapacity());
        model.setDescription(dto.getDescription());
        return model;
    }
}
