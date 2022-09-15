package com.github.budwing.obo.trade.service;

import com.github.budwing.obo.trade.dto.OrderDTO;

public interface OrderService {
    String createOrder(OrderDTO order);
    String payForOrder(Integer cinemaId, String orderId, String phone);
    String finishOrder(Integer cinemaId, String orderId, String status);
}
