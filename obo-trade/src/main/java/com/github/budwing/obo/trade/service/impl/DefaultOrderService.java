package com.github.budwing.obo.trade.service.impl;

import com.github.budwing.obo.trade.dto.OrderDto;
import com.github.budwing.obo.trade.dto.OrderItemDto;
import com.github.budwing.obo.trade.dto.PayRequest;
import com.github.budwing.obo.trade.entity.Order;
import com.github.budwing.obo.trade.mapper.OrderMapper;
import com.github.budwing.obo.trade.sal.PaymentClient;
import com.github.budwing.obo.trade.sal.TicketClient;
import com.github.budwing.obo.trade.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class DefaultOrderService implements OrderService {
    @Autowired
    private TicketClient ticketClient;
    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    private OrderMapper orderMapper;
    private Random random = new Random(99);

    @GlobalTransactional
    @Override
    public String createOrder(OrderDto orderDto) {
        log.debug("Global transaction xid: {}", RootContext.getXID());
        Long totalPrice = 0l;
        for (OrderItemDto item : orderDto.getOrderItems()) {
            ticketClient.orderTicket(orderDto.getCinemaId(), item.getTicketId());
            totalPrice += item.getPrice();
        }

        Order order = orderDto.toEntity();

        order.setId(UUID.randomUUID().toString());
        order.setCreateTime(LocalDateTime.now());
        order.setStatus(Order.Status.UNPAID);
        order.setTotalPrice(totalPrice);
        orderMapper.insertOrder(order);
        order.getOrderItemList().stream().forEach(orderItem -> {
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setCinemaId(orderDto.getCinemaId());
            orderItem.setOrderId(order.getId());
            orderMapper.insertOrderItem(orderItem);
        });

        // The following code is used to mock fail to test global transaction
        if (random.nextBoolean()) {
            throw new RuntimeException("mock fail");
        }

        return order.getId();
    }

    @GlobalTransactional
    @Override
    public String payForOrder(Integer cinemaId, String orderId, String phone) {
        log.debug("Global transaction xid: {}", RootContext.getXID());
        Order order = orderMapper.selectById(orderId, cinemaId);
        if (order == null) {
            log.debug("Order({}) doesn't exist.", orderId);
            return null;
        }
        String paymentId = paymentClient.pay(new PayRequest(orderId, cinemaId, phone, order.getTotalPrice()));

        order.setPayTime(LocalDateTime.now());
        order.setPaymentId(paymentId);
        order.setStatus(Order.Status.PAYING);
        orderMapper.updateStatus(order.getId(), order.getCinemaId(), order.getStatus());

        // The following code is used to mock fail to test global transaction
        if (random.nextBoolean()) {
            throw new RuntimeException("mock fail");
        }

        return paymentId;
    }

    @GlobalTransactional
    @Override
    public String finishOrder(Integer cinemaId, String orderId, String statusString) {
        if (!statusString.equalsIgnoreCase(Order.Status.PAID.name()) &&
                !statusString.equalsIgnoreCase(Order.Status.FAILED.name())) {
            throw new IllegalStateException("Only PAID or FAILED is allowed.");
        }
        log.debug("Global transaction xid: {}", RootContext.getXID());
        Order order = orderMapper.selectById(orderId, cinemaId);
        order.setOrderItemList(orderMapper.seletOrderItemsByOrderId(orderId, cinemaId));
        log.debug("items: {}", order.getOrderItemList());
        Order.Status status = Order.Status.valueOf(statusString.toUpperCase());
        if (status == Order.Status.PAID) {
            order.getOrderItemList()
                    .stream()
                    .forEach(orderItem -> ticketClient.payTicket(cinemaId, orderItem.getTicket().getTicketId()));
            order.setFinishedTime(LocalDateTime.now());
        }
        order.setStatus(status);
        orderMapper.updateStatus(order.getId(), order.getCinemaId(), order.getStatus());

        return orderId;
    }
}
