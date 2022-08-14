package com.github.budwing.obo.trade.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime createTime;
    private Long totalPrice;
    private LocalDateTime payTime;
    private String paymentId;
    @OneToMany
    @JoinColumn(name = "order_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> orderItemList;
}
