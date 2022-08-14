package com.github.budwing.obo.trade.entity;

import com.github.budwing.obo.trade.vo.Ticket;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "obo_order_item")
@Data
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Embedded
    private Ticket ticket;
    private Integer price;
}
