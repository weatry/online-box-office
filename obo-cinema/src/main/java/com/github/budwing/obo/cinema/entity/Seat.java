package com.github.budwing.obo.cinema.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Seat {
    private Long id;
    private Integer cinemaId;
    private Integer hall;
    private Integer hallFloor;
    private Integer seatRow;
    private Integer seatCol;
    private Integer coordinateX;
    private Integer coordinateY;
    private String type;
    private Boolean available;
}
