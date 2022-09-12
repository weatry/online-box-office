package com.github.budwing.obo.cinema.dto;

import com.github.budwing.obo.cinema.entity.Seat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SeatDTO implements Serializable {
    public static SeatDTO of(Seat seat) {
        return new SeatDTO(seat.getHallFloor(), seat.getSeatRow(), seat.getSeatCol());
    }
    private final Integer hallFloor;
    private final Integer seatRow;
    private final Integer seatCol;
}
