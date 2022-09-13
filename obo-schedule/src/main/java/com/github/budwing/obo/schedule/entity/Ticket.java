package com.github.budwing.obo.schedule.entity;

import com.github.budwing.obo.schedule.vo.Seat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Ticket {
    public static enum Status {
        ONSALE,
        LOCKED,
        ORDERED,
        PAYED
    }

    public Ticket() {
    }

    public Ticket(Schedule schedule, Seat seat, Double price) {
        this.scheduleId = schedule.getId();
        this.cinemaId = schedule.getCinemaId();
        this.hallId = schedule.getHallId();
        this.movieId = schedule.getMovieId();
        this.seat = seat;
        this.price = price;
        this.status = Status.ONSALE;
    }

    private String id;
    private String scheduleId;
    private Integer cinemaId;
    private Integer hallId;
    private String movieId;
    private Seat seat;
    private Double price;
    private Status status;
    private LocalDateTime payTime;
    private LocalDateTime getTime;
    private String code;
}
