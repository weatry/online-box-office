package com.github.budwing.obo.movie.repository;

import com.github.budwing.obo.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
