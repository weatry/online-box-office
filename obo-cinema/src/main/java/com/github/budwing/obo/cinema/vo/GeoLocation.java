package com.github.budwing.obo.cinema.vo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;

@Data
@Embeddable
@ToString
public class GeoLocation {
    private Double longitude;
    private Double latitude;
}
