package com.github.budwing.obo.trade.mapper;

import com.github.budwing.obo.trade.entity.Order;
import com.github.budwing.obo.trade.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    int insertOrderItem(OrderItem item);
    int updateStatus(@Param("orderId") String orderId,
                     @Param("cinemaId") Integer cinemaId,
                     @Param("status") Order.Status status);
    Order selectById(@Param("id") String id,
                     @Param("cinemaId") Integer cinemaId);

    List<OrderItem> seletOrderItemsByOrderId(@Param("orderId") String id,
                                             @Param("cinemaId") Integer cinemaId);
}
