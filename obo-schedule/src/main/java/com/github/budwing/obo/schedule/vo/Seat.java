package com.github.budwing.obo.schedule.vo;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Data
@ToString
@Slf4j
public class Seat {
    public static Seat of(Map map) {
        Seat seat = new Seat();
        seat.hallFloor = (Integer) map.get("hallFloor");
        seat.seatCol = (Integer) map.get("seatCol");
        seat.seatRow = (Integer) map.get("seatRow");
        log.debug("create seat:{}", seat);

        return seat;
    }
    private Integer hallFloor;
    private Integer seatRow;
    private Integer seatCol;
}
