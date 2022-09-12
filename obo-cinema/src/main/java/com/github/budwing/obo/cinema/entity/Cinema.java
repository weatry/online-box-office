package com.github.budwing.obo.cinema.entity;

import com.github.budwing.obo.cinema.vo.Address;
import com.github.budwing.obo.cinema.vo.GeoLocation;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class Cinema implements Serializable {
    private Integer id;
    private String name;
    private Address address;
    private GeoLocation location;
    private String telephone;
    private List<Hall> halls;
}
