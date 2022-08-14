package com.github.budwing.obo.schedule.service.impl;

import com.github.budwing.obo.schedule.entity.Schedule;
import com.github.budwing.obo.schedule.entity.Ticket;
import com.github.budwing.obo.schedule.repository.ScheduleRepository;
import com.github.budwing.obo.schedule.repository.TicketRepository;
import com.github.budwing.obo.schedule.sal.CinemaClient;
import com.github.budwing.obo.schedule.service.ScheduleService;
import com.github.budwing.obo.schedule.vo.Seat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FeignBasedScheduleService implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CinemaClient cinemaClient;

    @Override
    @Transactional
    public Schedule changeStatus(String scheduleId, Schedule.Status status) {
        Optional<Schedule> optional = scheduleRepository.findById(scheduleId);
        if (!optional.isPresent()) {
            log.debug("schedule is null");
            return null;
        }

        Schedule schedule = optional.get();
        Schedule.Status preStatus = schedule.getStatus();
        if(preStatus.ordinal()>=status.ordinal()) {
            log.info("schedule status is {}, which can be changed to {}", preStatus, status);
            return schedule;
        }

        switch (status) {
            case SALE:
                List<Ticket> tickets = ticketsOf(schedule);
                ticketRepository.saveAll(tickets);
                break;
            case CANCEL:
                ticketRepository.deleteBySchedule_id(scheduleId);
        }

        schedule.setStatus(status);
        scheduleRepository.save(schedule);

        return schedule;
    }

    public List<Ticket> ticketsOf(Schedule schedule) {
        List<Seat> seats = cinemaClient.getSeatOf(schedule.getCinemaId(), schedule.getHallId());
        log.debug("got seats:{}", seats);
        return seats.stream()
                .map(seat -> new Ticket(schedule, seat, 40.00))
                .collect(Collectors.toList());
    }
}
