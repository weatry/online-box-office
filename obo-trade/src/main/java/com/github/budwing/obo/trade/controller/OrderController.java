package com.github.budwing.obo.trade.controller;

import com.github.budwing.obo.trade.entity.Order;
import com.github.budwing.obo.trade.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/obo")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Create an order",
            description = "Tickets status will be changed to ordered, and an unpaid order will be saved at the same time.")
    @PostMapping(value = "/order", produces = "application/json")
    public ResponseEntity create(@RequestBody Order order) {
        log.debug("Start creating order:{}", order);
        String orderId = null;
        try {
            orderId = orderService.createOrder(order);
        } catch (Throwable e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        log.debug("Order({}) creation is done successfully.", orderId);

        return ResponseEntity.ok(orderId);
    }

    @Operation(summary = "Pay an order",
            description = "Tickets status will be changed to payed, and order will be updated as payed also.")
    @PutMapping(value = "/order/{orderId}/payment", produces = "application/json")
    public ResponseEntity pay(@PathVariable String orderId, @RequestParam String fromAccount, @RequestParam String toAccount) {
        log.debug("Start payment for order: {}", orderId);
        String payId = null;
        try {
            payId = orderService.payForOrder(orderId, fromAccount, toAccount);
            if (payId==null) {
                return ResponseEntity.notFound().build();
            }
        } catch (Throwable e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        log.debug("Order payment({}) is done successfully.", payId);

        return ResponseEntity.ok(payId);
    }
}
