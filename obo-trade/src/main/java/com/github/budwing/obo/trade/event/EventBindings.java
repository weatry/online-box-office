package com.github.budwing.obo.trade.event;

import com.github.budwing.obo.trade.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class EventBindings {
    @Autowired
    private OrderService orderService;

    @Bean
    public Consumer<String> payment() {
        return result -> {
            log.debug("received a message:{}", result);
            String[] results = result.split(":");
            Integer cinemaId = Integer.valueOf(results[0]);
            String orderId = results[1];
            String status = results[2];
            if (status.equals("SUCCESSFUL")) {
                status = "PAID";
            } else {
                status = "FAILED";
            }
            try {
                log.info("Update order({}) to status {}", orderId, status);
                orderService.finishOrder(cinemaId, orderId, status);
            } catch (Throwable e) {
                log.error("Got exception: {}", e);
            }
        };
    }
}
