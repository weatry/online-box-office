package com.github.budwing.obo.cinema.mapper;

import com.github.budwing.obo.cinema.entity.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CinemaMapper {
    List<Cinema> selectPageable(@Param("start") int start,
                                @Param("size") int size);
    int insertCinema(Cinema cinema);
}
