package com.github.budwing.obo.cinema.service.impl;

import com.github.budwing.obo.cinema.dto.SeatDTO;
import com.github.budwing.obo.cinema.entity.Seat;
import com.github.budwing.obo.cinema.repository.SeatRepository;
import com.github.budwing.obo.cinema.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultSeatService implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<SeatDTO> getAllAvailableSeat(Integer cinemaId, Integer hallId) {
        List<Seat> seatList = seatRepository.findByCinema_idAndHall_idAndAvailable(cinemaId, hallId, true);
        return seatList.stream().map(seat -> SeatDTO.of(seat))
                .collect(Collectors.toList());
    }
}
