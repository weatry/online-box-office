package com.github.budwing.obo.trade.service;

import com.github.budwing.obo.trade.entity.Order;

public interface OrderService {
    String createOrder(Order order);
    String payForOrder(String orderId, String fromAccount, String toAccount);
}
