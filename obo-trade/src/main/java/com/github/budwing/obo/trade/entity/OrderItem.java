package com.github.budwing.obo.trade.entity;

import com.github.budwing.obo.trade.vo.Ticket;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderItem {
    private String id;
    private String orderId;
    private Integer cinemaId;
    private Ticket ticket;
    private Integer price;
}
