package com.github.budwing.obo.cinema.repository;

import com.github.budwing.obo.cinema.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByCinema_idAndHall_idAndAvailable(Integer cinemaId, Integer hallId, boolean available);
}
