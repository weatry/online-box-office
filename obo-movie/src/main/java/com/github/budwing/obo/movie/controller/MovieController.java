package com.github.budwing.obo.movie.controller;

import com.github.budwing.obo.movie.entity.Movie;
import com.github.budwing.obo.movie.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/obo")
@Slf4j
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movie")
    public Iterable<Movie> getMovies() {
        Iterable<Movie> movies = movieRepository.findAll();
        return movies;
    }
    @PostMapping("/movie")
    public void addMovie(@RequestBody Movie movie) {
        Movie m = movieRepository.save(movie);
        log.debug("Add a new movie:{}", m.getId());
    }
}
