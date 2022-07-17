package com.github.budwing.obo.trade.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "obo_order")
@Data
@ToString
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String phone;
    private Date createTime;
    private Date payTime;
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItemList;
}
