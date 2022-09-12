package com.github.budwing.obo.cinema.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {
    public Address() {
    }

    public Address(String province, String city, String street) {
        this.province = province;
        this.city = city;
        this.street = street;
    }

    private String province;
    private String city;
    private String street;
}
