package com.github.budwing.obo.trade.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PayRequest {
    public PayRequest() {
    }

    public PayRequest(Long payAmount, String fromAccount, String toAccount, String orderId) {
        this.payAmount = payAmount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.orderId = orderId;
    }

    private Long payAmount;
    private String fromAccount;
    private String toAccount;
    private String orderId;
}
