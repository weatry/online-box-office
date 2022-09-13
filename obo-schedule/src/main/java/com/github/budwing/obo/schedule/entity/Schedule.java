package com.github.budwing.obo.schedule.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Schedule {
    public static enum Status {
        INIT,
        SALE,
        CANCEL
    }
    private String id;
    private Integer cinemaId;
    private Integer hallId;
    private String movieId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private Status status;
}
