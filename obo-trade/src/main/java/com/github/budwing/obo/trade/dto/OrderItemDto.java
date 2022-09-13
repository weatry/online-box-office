package com.github.budwing.obo.trade.dto;

import com.github.budwing.obo.trade.entity.OrderItem;
import com.github.budwing.obo.trade.vo.Ticket;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class OrderItemDto implements Serializable {
    private String id;
    private Integer cinemaId;
    private String ticketId;
    private Integer seatFloor;
    private Integer seatCol;
    private Integer seatRow;
    private Integer price;

    public OrderItem toEntity() {
        OrderItem item = new OrderItem();
        item.setCinemaId(cinemaId);
        item.setPrice(price);

        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);
        ticket.setSeatFloor(seatFloor);
        ticket.setSeatRow(seatRow);
        ticket.setSeatCol(seatCol);
        item.setTicket(ticket);

        return item;
    }
}
