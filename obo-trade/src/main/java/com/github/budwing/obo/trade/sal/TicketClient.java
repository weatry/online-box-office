package com.github.budwing.obo.trade.sal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "obo-schedule", fallbackFactory = TicketClientFallbackFactory.class)
public interface TicketClient {
    @PutMapping("/obo/ticket/{cinemaId}/{ticketId}/status/ordered")
    void orderTicket(@PathVariable("cinemaId") Integer cinemaId, @PathVariable("ticketId") String ticketId);

    @PutMapping("/obo/ticket/{cinemaId}/{ticketId}/status/payed")
    void payTicket(@PathVariable("cinemaId") Integer cinemaId, @PathVariable("ticketId") String ticketId);
}

@Component
@Slf4j
class TicketClientFallbackFactory implements FallbackFactory<TicketClient> {
    @Override
    public TicketClient create(Throwable cause) {
        log.error("Ticket client fallback for exception:{}", cause.getClass());
        log.error("Ticket client exception message:{}", cause.getMessage());
        return new TicketClient() {
            @Override
            public void orderTicket(Integer cinemaId, String ticketId) {
                throw new RuntimeException(cause);
            }

            @Override
            public void payTicket(Integer cinemaId, String ticketId) {
                throw new RuntimeException(cause);
            }
        };
    }
}
