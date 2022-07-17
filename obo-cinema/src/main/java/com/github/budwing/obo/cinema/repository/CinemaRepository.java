package com.github.budwing.obo.cinema.repository;

import com.github.budwing.obo.cinema.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
