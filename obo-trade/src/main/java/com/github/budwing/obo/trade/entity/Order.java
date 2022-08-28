package com.github.budwing.obo.trade.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "obo_order")
@Data
@ToString
public class Order {
    public enum Status {
        UNPAID,
        PAYING,
        PAID,
        FAILED,
        CANCELED
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String phone;
    private LocalDateTime createTime;
    private Long totalPrice;
    private LocalDateTime payTime;
    private String paymentId;
    private Status status;
    private LocalDateTime finishedTime;
    @OneToMany
    @JoinColumn(name = "order_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<OrderItem> orderItemList;
}
