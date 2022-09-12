package com.github.budwing.obo.cinema.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Hall {
    private Integer id;
    private String name;
    private String type;
    private String status;
    private List<Seat> seats;
}
