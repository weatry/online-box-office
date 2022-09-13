package com.github.budwing.obo.schedule.mapper;

import com.github.budwing.obo.schedule.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TicketMapper {
    Ticket selectById(@Param("cinemaId") Integer cinemaId, @Param("id") String id);
    Integer insertTicket(Ticket ticket);
    Integer updateTicketStatus(@Param("cinemaId") Integer cinemaId,
                               @Param("id") String id,
                               @Param("status") Ticket.Status status);
    Integer deleteByScheduleId(@Param("cinemaId") Integer cinemaId,
                               @Param("scheduleId") String scheduleId);
}
