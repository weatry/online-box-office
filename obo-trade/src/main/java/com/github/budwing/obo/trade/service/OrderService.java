package com.github.budwing.obo.trade.service;

import com.github.budwing.obo.trade.dto.OrderDto;

public interface OrderService {
    String createOrder(OrderDto order);
    String payForOrder(Integer cinemaId, String orderId, String phone);
    String finishOrder(Integer cinemaId, String orderId, String status);
}
