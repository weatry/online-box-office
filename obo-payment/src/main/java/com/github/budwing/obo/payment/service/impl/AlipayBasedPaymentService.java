package com.github.budwing.obo.payment.service.impl;

import com.github.budwing.obo.payment.dto.PayRequest;
import com.github.budwing.obo.payment.entity.Payment;
import com.github.budwing.obo.payment.repository.PaymentRepository;
import com.github.budwing.obo.payment.sal.AlipayClient;
import com.github.budwing.obo.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
public class AlipayBasedPaymentService implements PaymentService {
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    @Override
    public String payFor(PayRequest payRequest) {
        // Invoke Alipay service
        String alipayId = alipayClient.pay(payRequest);
        Payment payment = payRequest.toPayment();
        payment.setProvider(Payment.Provider.ALIPAY);
        payment.setPayId(alipayId);
        payment.setPayTime(LocalDateTime.now());
        paymentRepository.save(payment);
        return payment.getId();
    }
}
