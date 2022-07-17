package com.github.budwing.obo.movie.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Entity
@Table(name = "obo_movie")
@Data
@ToString
public class Movie {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private Duration duration;
    private String format;
    @Column(columnDefinition = "TEXT")
    private String introduction;
    private Date releaseDate;
}
