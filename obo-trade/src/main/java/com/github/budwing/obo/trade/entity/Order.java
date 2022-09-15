package com.github.budwing.obo.trade.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class Order {
    public enum Status {
        UNPAID,
        PAYING,
        PAID,
        FAILED,
        CANCELED
    }
    private String id;
    private Integer cinemaId;
    private String phone;
    private LocalDateTime createTime;
    private Long totalPrice;
    private LocalDateTime payTime;
    private String paymentId;
    private Status status;
    private LocalDateTime finishedTime;
    private String pickupCode;
    private LocalDateTime pickupTime;
    private List<OrderItem> orderItemList;
}
