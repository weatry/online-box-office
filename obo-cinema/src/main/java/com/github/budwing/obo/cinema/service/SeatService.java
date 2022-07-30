package com.github.budwing.obo.cinema.service;

import com.github.budwing.obo.cinema.dto.SeatDto;

import java.util.List;

public interface SeatService {
    List<SeatDto> getAllAvailableSeat(Integer cinemaId, Integer hallId);
}
