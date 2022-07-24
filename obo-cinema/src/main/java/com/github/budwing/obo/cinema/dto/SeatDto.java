package com.github.budwing.obo.cinema.dto;

import com.github.budwing.obo.cinema.entity.Seat;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeatDto implements Serializable {
    public static SeatDto of(Seat seat) {
        return new SeatDto(seat.getHallFloor(), seat.getSeatRow(), seat.getSeatCol());
    }
    private final Integer hallFloor;
    private final Integer seatRow;
    private final Integer seatCol;
}
