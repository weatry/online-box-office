package com.github.budwing.obo.cinema.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "obo_seat")
@Data
@ToString
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer hallFloor;
    private Integer seatRow;
    private Integer seatCol;
    private String type;
    private Boolean available;
}
