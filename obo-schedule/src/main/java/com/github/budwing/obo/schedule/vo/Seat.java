package com.github.budwing.obo.schedule.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {
    private Integer floor;
    private Integer seatRow;
    private Integer seatCol;
}
