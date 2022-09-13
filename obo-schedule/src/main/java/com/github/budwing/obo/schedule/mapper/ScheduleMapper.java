package com.github.budwing.obo.schedule.mapper;

import com.github.budwing.obo.schedule.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    Schedule selectById(@Param("id") String id, @Param("cinemaId") Integer cinemaId);
    List<Schedule> selectByCinemaIdAndStartTimeBetween(@Param("cinemaId") Integer cinemaId,
                                                       @Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end);
    List<Schedule> selectByCinemaIdAndMovieIdAndStartTimeGreaterThan(@Param("cinemaId") Integer cinemaId,
                                                                     @Param("movieId") String movieId,
                                                                     @Param("start") LocalDateTime start);
    List<Schedule> selectByCinemaIdAndHallIdAndStartTimeBetween(@Param("cinemaId") Integer cinemaId,
                                                                @Param("hallId") Integer hallId,
                                                                @Param("start") LocalDateTime start,
                                                                @Param("end") LocalDateTime end);
    List<String> selectDistinctMovieIdByStartTimeGreaterThan(LocalDateTime start);
    Integer insertSchedule(Schedule schedule);
    Integer updateScheduleStatus(@Param("id") String id,
                                 @Param("cinemaId") Integer cinemaId,
                                 @Param("status") Schedule.Status status);
}
