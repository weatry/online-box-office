package com.github.budwing.obo.cinema.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.budwing.obo.cinema.dto.CinemaDTO;
import com.github.budwing.obo.cinema.entity.Cinema;
import com.github.budwing.obo.cinema.mapper.CinemaMapper;
import com.github.budwing.obo.cinema.service.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultCinemaService implements CinemaService {
    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    @SentinelResource("obo-cinema.getCinema")
    public List<CinemaDTO> getCinemas(Integer page, Integer size) {
        log.debug("Get cinemas, page:{}, size:{}", page, size);
        if (page == null) page = 1;
        if (size == null) size = 20;
        List<Cinema> cinemas = cinemaMapper.selectPageable((page-1)*size, size);
        log.debug("Number of cinema:{}", cinemas.size());
        return cinemas.stream().map(cinema -> CinemaDTO.of(cinema)).collect(Collectors.toList());
    }

    @Override
    public Integer saveCinema(CinemaDTO cinema) {
        log.debug("Save cinema: {}", cinema);
        Cinema entity = cinema.toEntity();
        Integer count = cinemaMapper.insertCinema(entity);
        log.debug("Cinema({}) saved.", entity.getId());
        return count;
    }
}
