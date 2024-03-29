package com.github.budwing.obo.payment.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.budwing.obo.payment.dto.PayRequest;
import com.github.budwing.obo.payment.entity.Payment;
import com.github.budwing.obo.payment.repository.PaymentRepository;
import com.github.budwing.obo.payment.sal.AlipayClient;
import com.github.budwing.obo.payment.service.PaymentService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class AlipayBasedPaymentService implements PaymentService {
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StreamBridge bridge;

    @GlobalTransactional
    @SentinelResource("obo-payment.pay")
    @Override
    public String payFor(PayRequest payRequest) {
        log.debug("Transaction xid: {}", RootContext.getXID());
        // Invoke Alipay service
        String alipayId = alipayClient.pay(payRequest);
        Payment payment = payRequest.toPayment();
        payment.setProvider(Payment.Provider.ALIPAY);
        payment.setPayId(alipayId);
        payment.setStatus(Payment.Status.ONGOING);
        payment.setPayTime(LocalDateTime.now());
        paymentRepository.save(payment);
        return payment.getId();
    }

    @Transactional
    @Override
    public String changeStatus(String paymentId, String status) {
        if (!status.equalsIgnoreCase(Payment.Status.SUCCESSFUL.name()) &&
                !status.equalsIgnoreCase(Payment.Status.FAILED.name())) {
            throw new IllegalStateException("Status can only be SUCCESSFUL or FAILED, instead of "+status);
        }
        Optional<Payment> optional = paymentRepository.findById(paymentId);
        if (!optional.isPresent()) {
            return null;
        }
        Payment payment = optional.get();
        payment.setStatus(Payment.Status.valueOf(status.toUpperCase()));
        payment.setFinishedTime(LocalDateTime.now());

        bridge.send("payment-result", payment.getCinemaId()+":"+payment.getOrderId()+":"+status.toUpperCase());

        return payment.getId();
    }
}
