package com.github.budwing.obo.schedule.service;

import com.github.budwing.obo.schedule.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    Integer changeStatus(Integer cinemaId, String scheduleId, Schedule.Status status);
    List<Schedule> findByCinemaIdAndStartTimeBetween(Integer cinemaId, LocalDateTime start, LocalDateTime end);
    List<Schedule> findByCinemaIdAndHallIdAndStartTimeBetween(Integer cinemaId, Integer hallId, LocalDateTime start, LocalDateTime end);
    List<String> findDistinctMovieIdByStartTimeGreaterThan(LocalDateTime start);
    List<Schedule> findByCinemaIdAndMovieIdAndStartTimeGreaterThan(Integer cinemaId, String movieId, LocalDateTime start);
    Schedule save(Schedule schedule);
}
