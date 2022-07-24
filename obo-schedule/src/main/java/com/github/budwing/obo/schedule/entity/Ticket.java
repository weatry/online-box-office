package com.github.budwing.obo.schedule.entity;

import com.github.budwing.obo.schedule.vo.Seat;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "obo_ticket")
@Data
@ToString
public class Ticket {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    private Schedule schedule;
    @Embedded
    private Seat seat;
    private Double price;
    private LocalDateTime payTime;
    private LocalDateTime getTime;
    private String code;
}
