package com.github.budwing.obo.schedule.service.impl;

import lombok.extern.slf4j.Slf4j;

//@Service
@Slf4j
public class RestTemplateBasedScheduleService { //implements ScheduleService {
/*    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private RestTemplate restTemplate;

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
                ticketRepository.deleteByScheduleId(scheduleId);
        }

        schedule.setStatus(status);
        scheduleRepository.save(schedule);

        return schedule;
    }

    public List<Ticket> ticketsOf(Schedule schedule) {
        String url = String.format("http://obo-cinema/obo/cinema/%d/hall/%d/seat",
                schedule.getCinemaId(), schedule.getHallId());
        log.debug("send request to {} to query seats", url);
        List<Map> seats = restTemplate.getForObject(url, List.class);
        log.debug("got seats:{}", seats);
        return seats.stream()
                .map(m -> new Ticket(schedule, Seat.of(m), 40.00))
                .collect(Collectors.toList());
    }*/
}
