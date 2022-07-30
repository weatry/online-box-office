package com.github.budwing.obo.schedule.controller;

import com.github.budwing.obo.schedule.entity.Schedule;
import com.github.budwing.obo.schedule.repository.ScheduleRepository;
import com.github.budwing.obo.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/obo")
@Slf4j
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleService scheduleService;

    @Operation(summary = "Get schedules of a specific cinema")
    @GetMapping("/schedule/cinema/{cinemaId}")
    public List<Schedule> getCinemaSchedulesBetween(@PathVariable Integer cinemaId,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
        if (start==null) {
            start=LocalDateTime.now();
        }
        if (end==null) {
            end=start.plus(24, ChronoUnit.HOURS);
        }
        log.debug("Find schedules for cinema({}) between {} and {}", cinemaId, start, end);
        return scheduleRepository.findByCinemaIdAndStartTimeBetween(cinemaId, start, end);
    }

    @Operation(summary = "Get schedules of a cinema hall",
            description = "Get schedules of a cinema hall between during a specific period, this is usually used by cinema operator to schedule movies")
    @GetMapping("/schedule/cinema/{cinemaId}/hall/{hallId}")
    public List<Schedule> getCinemaHallSchedulesBetween(@PathVariable Integer cinemaId, @PathVariable Integer hallId,
                                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
        if (start==null) {
            start=LocalDateTime.now();
        }
        if (end==null) {
            end=start.plus(24, ChronoUnit.HOURS);
        }
        log.debug("Find schedules for cinema({})-hall({}) between {} and {}", cinemaId, hallId, start, end);
        return scheduleRepository.findByCinemaIdAndHallIdAndStartTimeBetween(cinemaId, hallId, start, end);
    }



    @Operation(description = "Get all scheduled movies after a specific time. " +
            "This is commonly used by the users side to buy tickets of a scheduled movie.",
               summary = "Get scheduled movies")
    @GetMapping("/schedule/movie")
    public List<String> getScheduledMoviesAfter(@RequestParam(required = false) LocalDateTime start) {
        if (start==null) {
            start=LocalDateTime.now();
        }

        log.debug("Find scheduled movies after {}", start);
        return scheduleRepository.findDistinctMovieIdByStartTimeGreaterThan(start);
    }

    @Operation(summary = "Get a movie's schedules of a cinema",
            description = "Get a movie's schedules of a cinema. " +
            "This is usually used by user to buy tickets")
    @GetMapping("/schedule/movie/{movieId}/cinema/{cinemaId}")
    public List<Schedule> getSchedulesOfMovie(@PathVariable String movieId, @PathVariable Integer cinemaId,
                                            @RequestParam(required = false) LocalDateTime start) {
        if (start==null) {
            start=LocalDateTime.now();
        }

        log.debug("Find scheduled movies after {}", start);
        return scheduleRepository.findByCinemaIdAndMovieIdAndStartTimeGreaterThan(cinemaId, movieId, start);
    }

    @Operation(summary = "Add a schedule")
    @PostMapping("/schedule")
    public void addSchedule(@RequestBody Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Operation(summary = "Start selling tickets of a schedule")
    @PutMapping("/schedule/{scheduleId}/status/SALE")
    public ResponseEntity start(@PathVariable String scheduleId) {
        Schedule schedule = scheduleService.changeStatus(scheduleId, Schedule.Status.SALE);
        if (schedule==null) return ResponseEntity.notFound().build();
        // a canceled schedule can't change status
        if (schedule.getStatus()== Schedule.Status.CANCEL) return ResponseEntity.badRequest().body("schedule is canceled");

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Cancel a schedule")
    @PutMapping("/schedule/{scheduleId}/status/CANCEL")
    public ResponseEntity cancel(@PathVariable String scheduleId) {
        Schedule schedule = scheduleService.changeStatus(scheduleId, Schedule.Status.CANCEL);
        if (schedule==null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }
}
