package com.github.budwing.obo.cinema.mapper;

import com.github.budwing.obo.cinema.entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeatMapper {
    List<Seat> selectAvailableSeatByCinemaHall(@Param("cinemaId") Integer cinemaId,
                                               @Param("hallId") Integer hallId);
}
