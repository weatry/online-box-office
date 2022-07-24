package com.github.budwing.obo.schedule.repository;

import com.github.budwing.obo.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    List<Schedule> findByCinemaIdAndHallIdAndStartTimeBetween(Integer cinemaId, Integer hallId,
                                                              LocalDateTime start, LocalDateTime end);
    List<Schedule> findByCinemaIdAndStartTimeBetween(Integer cinemaId,
                                                              LocalDateTime start, LocalDateTime end);
    List<Schedule> findByCinemaIdAndMovieIdAndStartTimeGreaterThan(Integer cinemaId, String movieId, LocalDateTime start);

    @Query("SELECT DISTINCT s.movieId FROM Schedule s WHERE s.startTime>?1")
    List<String> findDistinctMovieIdByStartTimeGreaterThan(LocalDateTime start);
}