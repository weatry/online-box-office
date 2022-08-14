package com.github.budwing.obo.trade.vo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Data
@ToString
public class Ticket {
    private String ticketId;
    private Integer seatFloor;
    private Integer seatCol;
    private Integer seatRow;
}
