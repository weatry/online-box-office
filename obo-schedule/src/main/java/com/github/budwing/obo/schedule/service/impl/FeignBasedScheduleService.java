package com.github.budwing.obo.schedule.service.impl;

import com.github.budwing.obo.schedule.entity.Schedule;
import com.github.budwing.obo.schedule.entity.Ticket;
import com.github.budwing.obo.schedule.mapper.ScheduleMapper;
import com.github.budwing.obo.schedule.mapper.TicketMapper;
import com.github.budwing.obo.schedule.sal.CinemaClient;
import com.github.budwing.obo.schedule.service.ScheduleService;
import com.github.budwing.obo.schedule.vo.Seat;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FeignBasedScheduleService implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private CinemaClient cinemaClient;

    @Override
    public List<Schedule> findByCinemaIdAndStartTimeBetween(Integer cinemaId, LocalDateTime start, LocalDateTime end) {
        if (start==null) {
            start=LocalDateTime.now();
        }
        if (end==null) {
            end=start.plus(24, ChronoUnit.HOURS);
        }

        return scheduleMapper.selectByCinemaIdAndStartTimeBetween(cinemaId, start, end);
    }

    @Override
    public List<Schedule> findByCinemaIdAndHallIdAndStartTimeBetween(Integer cinemaId, Integer hallId, LocalDateTime start, LocalDateTime end) {
        if (start==null) {
            start=LocalDateTime.now();
        }
        if (end==null) {
            end=start.plus(24, ChronoUnit.HOURS);
        }
        return scheduleMapper.selectByCinemaIdAndHallIdAndStartTimeBetween(cinemaId, hallId, start, end);
    }

    @Override
    public List<String> findDistinctMovieIdByStartTimeGreaterThan(LocalDateTime start) {
        if (start==null) {
            start=LocalDateTime.now();
        }

        return scheduleMapper.selectDistinctMovieIdByStartTimeGreaterThan(start);
    }

    @Override
    public List<Schedule> findByCinemaIdAndMovieIdAndStartTimeGreaterThan(Integer cinemaId, String movieId, LocalDateTime start) {
        if (start==null) {
            start=LocalDateTime.now();
        }

        return scheduleMapper.selectByCinemaIdAndMovieIdAndStartTimeGreaterThan(cinemaId, movieId, start);
    }

    @Override
    @Transactional
    public Schedule save(Schedule schedule) {
        schedule.setId(UUID.randomUUID().toString());
        scheduleMapper.insertSchedule(schedule);
        return schedule;
    }

    @Override
    @Transactional
    @GlobalTransactional
    public Integer changeStatus(Integer cinemaId, String scheduleId, Schedule.Status status) {
        log.debug("Got tx id: {}", RootContext.getXID());
        Schedule schedule = scheduleMapper.selectById(scheduleId, cinemaId);
        if (schedule == null) {
            log.debug("schedule is null");
            return null;
        }

        Schedule.Status preStatus = schedule.getStatus();
        if(preStatus.ordinal()>=status.ordinal()) {
            log.info("schedule status is {}, which can be changed to {}", preStatus, status);
            return 0;
        }

        Integer result = 0;
        switch (status) {
            case SALE:
                List<Ticket> tickets = ticketsOf(schedule);
                tickets.stream().forEach(ticket -> ticketMapper.insertTicket(ticket));
                result = tickets.size();
                break;
            case CANCEL:
                result = ticketMapper.deleteByScheduleId(cinemaId, scheduleId);
        }

        schedule.setStatus(status);
        scheduleMapper.updateScheduleStatus(scheduleId, cinemaId, status);

        return result;
    }

    public List<Ticket> ticketsOf(Schedule schedule) {
        List<Seat> seats = cinemaClient.getSeatOf(schedule.getCinemaId(), schedule.getHallId());
        log.debug("got seats:{}", seats);
        return seats.stream()
                .map(seat -> new Ticket(schedule, seat, 40.00))
                .collect(Collectors.toList());
    }
}
