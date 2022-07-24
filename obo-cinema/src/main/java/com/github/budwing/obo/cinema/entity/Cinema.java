package com.github.budwing.obo.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.budwing.obo.cinema.vo.Address;
import com.github.budwing.obo.cinema.vo.GeoLocation;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "obo_cinema")
@Data
@ToString
public class Cinema implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Embedded
    private Address address;
    @Embedded
    private GeoLocation location;
    private String telephone;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    @JsonIgnore
    private List<Hall> halls;
}
