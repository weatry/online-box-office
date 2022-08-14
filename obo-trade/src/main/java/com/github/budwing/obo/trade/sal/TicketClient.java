package com.github.budwing.obo.trade.sal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("obo-schedule")
public interface TicketClient {
    @PutMapping("/obo/ticket/{ticketId}/status/ordered")
    void orderTicket(@PathVariable("ticketId") String ticketId);

    @PutMapping("/obo/ticket/{ticketId}/status/payed")
    void payTicket(@PathVariable("ticketId") String ticketId);
}
