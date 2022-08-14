package com.github.budwing.obo.schedule.service.impl;

import com.github.budwing.obo.schedule.entity.Ticket;
import com.github.budwing.obo.schedule.repository.TicketRepository;
import com.github.budwing.obo.schedule.service.TicketService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class DefaultTicketService implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    @Override
    public Ticket changeStatus(String ticketId, Ticket.Status status) {
        log.debug("Transaction xid:{}", RootContext.getXID());
        log.debug("Query ticket for id {}", ticketId);
        Optional<Ticket> optional = ticketRepository.findById(ticketId);
        if (!optional.isPresent()) {
            log.debug("Can't find ticket for id {}", ticketId);
            return null;
        }

        Ticket ticket = optional.get();
        if (ticket.getStatus().ordinal() > status.ordinal()) {
            log.debug("ticket({}) status is {}, can't be changed to {}.", ticketId, ticket.getStatus(), status);
            throw new IllegalStateException("Ticket was sold or locked.");
        }

        ticket.setStatus(status);
        ticketRepository.save(ticket);

        return ticket;
    }
}
