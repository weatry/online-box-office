package com.github.budwing.obo.cinema.dto;

import com.github.budwing.obo.cinema.entity.Cinema;
import com.github.budwing.obo.cinema.vo.Address;
import com.github.budwing.obo.cinema.vo.GeoLocation;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CinemaDTO implements Serializable {
    public static CinemaDTO of(Cinema cinema) {
        CinemaDTO dto = new CinemaDTO();
        dto.id = cinema.getId();
        dto.name = cinema.getName();
        dto.address = cinema.getAddress();
        dto.location = cinema.getLocation();
        dto.telephone = cinema.getTelephone();
        return dto;
    }
    private Integer id;
    private String name;
    private Address address;
    private GeoLocation location;
    private String telephone;

    public Cinema toEntity() {
        Cinema cinema = new Cinema();
        cinema.setId(id);
        cinema.setName(name);
        cinema.setAddress(address);
        cinema.setTelephone(telephone);
        cinema.setLocation(location);
        return cinema;
    }
}
