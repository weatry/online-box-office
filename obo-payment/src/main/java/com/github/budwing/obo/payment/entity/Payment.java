package com.github.budwing.obo.payment.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "obo_payment")
@Data
public class Payment {
    public static enum Provider {
        ALIPAY,
        WECHAT,
        PAYPAL
    }

    public static enum Status {
        ONGOING,
        SUCCESSFUL,
        FAILED
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Integer cinemaId;
    private String phone;
    private Long payAmount;
    private String fromAccount;
    private String toAccount;
    private Provider provider;
    private String payId;
    private String orderId;
    private LocalDateTime payTime;
    private LocalDateTime finishedTime;
    private Status status;
}
