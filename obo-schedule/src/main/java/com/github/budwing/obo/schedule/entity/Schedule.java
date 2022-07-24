package com.github.budwing.obo.schedule.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "obo_schedule")
@Data
@ToString
public class Schedule {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Integer cinemaId;
    private Integer hallId;
    private String movieId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ScheduleStatus status;

    public void startSelling() {
        status=ScheduleStatus.INIT;
    }
}

enum ScheduleStatus {
    INIT,
    SALE,
    CANCEL
}
