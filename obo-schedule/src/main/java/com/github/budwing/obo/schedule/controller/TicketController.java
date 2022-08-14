package com.github.budwing.obo.schedule.controller;

import com.github.budwing.obo.schedule.entity.Ticket;
import com.github.budwing.obo.schedule.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/obo")
@Slf4j
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Operation(summary = "Lock a ticket for ordering",
            description = "Change the ticket status to locked and wait 10min for the users to finish ordering. " +
                    "If the user can't finish the ordering in 10min, the ticket will be released automatically.")
    @PutMapping("/ticket/{ticketId}/status/locked")
    public ResponseEntity lockTicket(@PathVariable String ticketId) {
        try {
            Ticket ticket = ticketService.changeStatus(ticketId, Ticket.Status.LOCKED);;
            if (ticket==null) return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            ResponseEntity.badRequest().body("ticket was sold or locked");
        }


        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Order a ticket",
            description = "Change the ticket status to ordered, an ordered ticket can't be locked or ordered again. " +
                    "But if the order which the ticket belongs to isn't payed in a specific time(15min normally), " +
                    "the ticket will be released automatically once the order is expired or cancelled.")
    @PutMapping("/ticket/{ticketId}/status/ordered")
    public ResponseEntity orderTicket(@PathVariable String ticketId) {
        try {
            Ticket ticket = ticketService.changeStatus(ticketId, Ticket.Status.ORDERED);;
            if (ticket==null) return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            ResponseEntity.badRequest().body("ticket was sold");
        }

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Pay a ticket",
            description = "Change the ticket status to payed, an payed ticket can't be locked or ordered again. ")
    @PutMapping("/ticket/{ticketId}/status/payed")
    public ResponseEntity payTicket(@PathVariable String ticketId) {
        try {
            Ticket ticket = ticketService.changeStatus(ticketId, Ticket.Status.PAYED);;
            if (ticket==null) return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            ResponseEntity.badRequest().body("ticket was payed");
        }

        return ResponseEntity.ok().build();
    }
}
