package com.github.budwing.obo.cinema.service;

import com.github.budwing.obo.cinema.dto.CinemaDTO;

import java.util.List;

public interface CinemaService {
    List<CinemaDTO> getCinemas(Integer page, Integer size);
    Integer saveCinema(CinemaDTO cinema);
}
