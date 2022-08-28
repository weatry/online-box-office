package com.github.budwing.obo.payment.controller;

import com.github.budwing.obo.payment.dto.PayRequest;
import com.github.budwing.obo.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/obo")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Create a payment")
    @ResponseBody
    @PostMapping(path = "/payment", produces = "application/json")
    public String create(@RequestBody PayRequest request) {
        log.debug("Create payment: {}", request);
        String paymentId = paymentService.payFor(request);
        log.debug("Finish payment: {}", paymentId);
        return paymentId;
    }

    @Operation(summary = "Change a payment status")
    @PutMapping(path = "/payment/{paymentId}/status/{status}")
    public ResponseEntity create(@PathVariable String paymentId, @PathVariable String status) {
        log.debug("Payment({}) result: {}", paymentId, status);
        String result = paymentService.changeStatus(paymentId, status);
        if (result==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
