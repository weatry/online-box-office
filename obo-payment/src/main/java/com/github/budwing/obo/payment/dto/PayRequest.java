package com.github.budwing.obo.payment.dto;

import com.github.budwing.obo.payment.entity.Payment;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PayRequest {
    private Long payAmount;
    private String fromAccount;
    private String toAccount;
    private String orderId;

    public Payment toPayment() {
        Payment payment = new Payment();
        payment.setFromAccount(fromAccount);
        payment.setToAccount(toAccount);
        payment.setOrderId(orderId);
        payment.setPayAmount(payAmount);

        return payment;
    }
}
