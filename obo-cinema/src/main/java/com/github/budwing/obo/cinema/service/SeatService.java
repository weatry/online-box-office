package com.github.budwing.obo.cinema.service;

import com.github.budwing.obo.cinema.dto.SeatDTO;

import java.util.List;

public interface SeatService {
    List<SeatDTO> getAllAvailableSeat(Integer cinemaId, Integer hallId);
}
