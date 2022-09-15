package com.github.budwing.obo.idcenter.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "obo_global_id")
@Data
@ToString
public class GlobalID {
    public static final int DEFAULT_UPPER_LIMIT = 99999999;
    @Id
    private Integer id;
    private String bucket;
    private Integer step;
    private Long value;

    private Long upperLimit;

    public boolean needRestore() {
        return value >= upperLimit - step;
    }

    public void restore() {
        value = 1l;
    }
}
