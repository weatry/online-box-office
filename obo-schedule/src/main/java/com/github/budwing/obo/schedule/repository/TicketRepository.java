package com.github.budwing.obo.schedule.repository;

import com.github.budwing.obo.schedule.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}