package com.github.budwing.obo.cinema.dto;

import com.github.budwing.obo.cinema.entity.Seat;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeatDTO implements Serializable {
    public static SeatDTO of(Seat seat) {
        return new SeatDTO(seat.getHallFloor(), seat.getSeatRow(), seat.getSeatCol());
    }
    private final Integer hallFloor;
    private final Integer seatRow;
    private final Integer seatCol;
}
