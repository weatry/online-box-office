package com.github.budwing.obo.trade.dto;

import com.github.budwing.obo.trade.entity.Order;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
public class OrderDTO implements Serializable {
    private Integer cinemaId;
    private String phone;
    private List<OrderItemDTO> orderItems;

    public Order toEntity() {
        Order order = new Order();
        order.setCinemaId(cinemaId);
        order.setPhone(phone);
        if (!CollectionUtils.isEmpty(orderItems)) {
            order.setOrderItemList(orderItems.stream().map(orderItemDto -> orderItemDto.toEntity()).collect(Collectors.toList()));
        }
        return order;
    }
}
