package com.github.budwing.obo.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "obo_hall")
@Data
@ToString
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    private String status;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    @JsonIgnore
    private List<Seat> seats;
}
