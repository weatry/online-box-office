package com.github.budwing.obo.schedule.service;

import com.github.budwing.obo.schedule.entity.Ticket;

public interface TicketService {
    Ticket changeStatus(String ticketId, Ticket.Status status);
}
