package com.github.budwing.obo.payment.service;

import com.github.budwing.obo.payment.dto.PayRequest;

public interface PaymentService {
    String payFor(PayRequest payRequest);
    String changeStatus(String paymentId, String status);
}
