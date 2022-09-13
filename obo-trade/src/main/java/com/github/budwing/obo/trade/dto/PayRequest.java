package com.github.budwing.obo.trade.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PayRequest {
    public PayRequest() {
    }

    public PayRequest(String orderId, Integer cinemaId, String phone, Long payAmount) {
        this.orderId = orderId;
        this.cinemaId = cinemaId;
        this.phone = phone;
        this.payAmount = payAmount;
    }

    private String orderId;
    private Integer cinemaId;
    private String phone;
    private Long payAmount;
}
