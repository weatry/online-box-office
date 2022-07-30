package com.github.budwing.obo.cinema.controller;

import com.github.budwing.obo.cinema.dto.SeatDto;
import com.github.budwing.obo.cinema.entity.Cinema;
import com.github.budwing.obo.cinema.repository.CinemaRepository;
import com.github.budwing.obo.cinema.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/obo")
@Slf4j
public class CinemaController {
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SeatService seatService;

    @GetMapping("/cinema")
    public List<Cinema> getCinemas(Integer page, Integer size) {
        Page<Cinema> cinemas = cinemaRepository.findAll(PageRequest.of(0,20));
        log.debug("Number of cinema:{}", cinemas.getTotalElements());
        return cinemas.stream().collect(Collectors.toList());
    }

    @PostMapping(path = "/cinema", consumes = "application/json")
    public void addCinema(@RequestBody Cinema cinema) {
        log.debug("Receive data of cinema:{}",cinema);
        cinemaRepository.save(cinema);
    }

    @Operation(summary = "Get all available seats of a hall")
    @GetMapping("/cinema/{cinemaId}/hall/{hallId}/seat")
    public List<SeatDto> getAllAvailableSeats(@PathVariable Integer cinemaId, @PathVariable Integer hallId) {
        log.debug("Got cinemaId:{}, hallId:{}", cinemaId, hallId);
        return seatService.getAllAvailableSeat(cinemaId, hallId);
    }
}
