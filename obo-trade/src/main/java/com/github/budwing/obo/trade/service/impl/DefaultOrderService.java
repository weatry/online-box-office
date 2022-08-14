package com.github.budwing.obo.trade.service.impl;

import com.github.budwing.obo.trade.dto.PayRequest;
import com.github.budwing.obo.trade.entity.Order;
import com.github.budwing.obo.trade.entity.OrderItem;
import com.github.budwing.obo.trade.repository.OrderRepository;
import com.github.budwing.obo.trade.sal.PaymentClient;
import com.github.budwing.obo.trade.sal.TicketClient;
import com.github.budwing.obo.trade.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class DefaultOrderService implements OrderService {
    @Autowired
    private TicketClient ticketClient;
    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    private OrderRepository orderRepository;

    Random random = new Random(99);

    @GlobalTransactional
    @Override
    public String createOrder(Order order) {
        log.debug("Global transaction xid: {}", RootContext.getXID());
        Long totalPrice = 0l;
        for (OrderItem item: order.getOrderItemList()) {
            ticketClient.orderTicket(item.getTicket().getTicketId());
            totalPrice += item.getPrice();
        }

        order.setCreateTime(LocalDateTime.now());
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        // The following code is used to mock fail to test global transaction
        /*if (random.nextBoolean()) {
            throw new RuntimeException("mock fail");
        }*/

        return order.getId();
    }

    @GlobalTransactional
    @Override
    public String payForOrder(String orderId, String fromAccount, String toAccount) {
        log.debug("Global transaction xid: {}", RootContext.getXID());
        Optional<Order> optional = orderRepository.findById(orderId);
        if (!optional.isPresent()) {
            log.debug("Order({}) doesn't exist.", orderId);
            return null;
        }
        Order order = optional.get();
        String paymentId = paymentClient.pay(new PayRequest(order.getTotalPrice(), fromAccount, toAccount, orderId));
        order.setPayTime(LocalDateTime.now());
        order.setPaymentId(paymentId);
        order.getOrderItemList().stream().forEach(orderItem -> ticketClient.payTicket(orderItem.getTicket().getTicketId()));
        orderRepository.save(order);

        // The following code is used to mock fail to test global transaction
        /*if (random.nextBoolean()) {
            throw new RuntimeException("mock fail");
        }*/

        return paymentId;
    }
}
