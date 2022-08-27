package com.github.budwing.obo.cinema.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.budwing.obo.cinema.dto.CinemaDTO;
import com.github.budwing.obo.cinema.entity.Cinema;
import com.github.budwing.obo.cinema.repository.CinemaRepository;
import com.github.budwing.obo.cinema.service.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultCinemaService implements CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    @SentinelResource("obo-cinema.getCinema")
    public List<CinemaDTO> getCinemas(Integer page, Integer size) {
        log.debug("Get cinemas, page:{}, size:{}", page, size);
        if (page == null) page = 1;
        if (size == null) size = 20;
        Page<Cinema> cinemas = cinemaRepository.findAll(PageRequest.of(0, 20));
        log.debug("Number of cinema:{}", cinemas.getTotalElements());
        return cinemas.stream().map(cinema -> CinemaDTO.of(cinema)).collect(Collectors.toList());
    }

    @Override
    public Integer saveCinema(CinemaDTO cinema) {
        log.debug("Save cinema: {}", cinema);
        Cinema entity = cinema.toEntity();
        cinemaRepository.save(entity);
        log.debug("Cinema({}) saved.", cinema.getId());
        return cinema.getId();
    }
}
