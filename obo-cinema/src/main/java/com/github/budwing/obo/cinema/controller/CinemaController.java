package com.github.budwing.obo.cinema.controller;

import com.github.budwing.obo.cinema.dto.CinemaDTO;
import com.github.budwing.obo.cinema.dto.SeatDTO;
import com.github.budwing.obo.cinema.service.CinemaService;
import com.github.budwing.obo.cinema.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obo")
@Slf4j
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private SeatService seatService;

    @GetMapping("/cinema")
    public List<CinemaDTO> getCinemas(Integer page, Integer size) {
        return cinemaService.getCinemas(page, size);
    }

    @PostMapping(path = "/cinema", consumes = "application/json")
    @ResponseBody
    public Integer addCinema(@RequestBody CinemaDTO cinema) {
        return cinemaService.saveCinema(cinema);
    }

    @Operation(summary = "Get all available seats of a hall")
    @GetMapping("/cinema/{cinemaId}/hall/{hallId}/seat")
    public List<SeatDTO> getAllAvailableSeats(@PathVariable Integer cinemaId, @PathVariable Integer hallId) {
        log.debug("Got cinemaId:{}, hallId:{}", cinemaId, hallId);
        return seatService.getAllAvailableSeat(cinemaId, hallId);
    }
}
