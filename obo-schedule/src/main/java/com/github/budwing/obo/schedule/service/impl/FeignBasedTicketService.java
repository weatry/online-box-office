package com.github.budwing.obo.schedule.service.impl;

import com.github.budwing.obo.schedule.entity.Ticket;
import com.github.budwing.obo.schedule.mapper.TicketMapper;
import com.github.budwing.obo.schedule.service.TicketService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FeignBasedTicketService implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;

    @Transactional
    @GlobalTransactional
    @Override
    public Ticket changeStatus(Integer cinemaId, String ticketId, Ticket.Status status) {
        log.debug("Transaction xid:{}", RootContext.getXID());
        log.debug("Query ticket for id {}", ticketId);
        Ticket ticket = ticketMapper.selectById(cinemaId, ticketId);
        if (ticket == null) {
            log.debug("Can't find ticket for id {}", ticketId);
            return null;
        }
        if (ticket.getStatus().ordinal() > status.ordinal()) {
            log.debug("ticket({}) status is {}, can't be changed to {}.", ticketId, ticket.getStatus(), status);
            throw new IllegalStateException("Ticket was sold or locked.");
        }

        ticketMapper.updateTicketStatus(cinemaId, ticketId, status);

        return ticket;
    }
}
