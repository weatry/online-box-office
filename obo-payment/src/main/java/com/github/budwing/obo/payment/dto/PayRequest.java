package com.github.budwing.obo.payment.dto;

import com.github.budwing.obo.payment.entity.Payment;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PayRequest {
    private String orderId;
    private Integer cinemaId;
    private String phone;
    private Long payAmount;

    public Payment toPayment() {
        Payment payment = new Payment();
        payment.setFromAccount(phone);
        payment.setOrderId(orderId);
        payment.setPayAmount(payAmount);
        payment.setCinemaId(cinemaId);

        return payment;
    }
}
